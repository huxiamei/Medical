/**
 * 病例后台管理js
 */
var basePath = "";
var path = "";
var currPage = 1;
var TotalPage = 1;
var id=0;
$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    //初始化页面
    totalPagePath=basePath+'/MedicalCase/manage/totalPage';
    getPage(totalPagePath);

	//点击修改
	$(document).on('click','#caseUpdate',function()
	{
		id = $(this).parent().parent().find("#caseId").text();
		$("#MedicalCaseLayer").css("display","block");
		path=basePath+"/MedicalCase/manage/update/"+id;
		update();	
	});
	
	//点击查询
	$(document).on('click','#searchCase',function()
	{
		var searchCaseId = $("#searchCaseId").val();
		var searchPatientId=$("#searchPatientId").val();
		
		if(searchCaseId!="" || searchPatientId!="")
	    {
			path=basePath+"/MedicalCase/manage/search";
			search(searchCaseId,searchPatientId,path);	
		}
		else
		{	
			alert("查询条件不能为空");
		}
		
	});
	
	//提交修改表单
	$("#MedicalCaseSubmits").on('click',function(){
		$.ajax({
	        type:"post",
	        cache: false,
	        dataType:"json",
	        data:$("#caseDetail").serialize(), //新建一个对象填充
	        url: path,
	        success: function (data) {        
	        	//弹出框显示
	        	alert(data.message);
	        	ResetTotalPage();
	        },
	        error:function(data){
	        	alert("error"+data.message);
	        }
		 });
	});	
	
	//删除按钮
	$(document).on('click','#caseDelete',function()
	{	
		var id = $(this).parent().parent().find("#caseId").text();
	    if(confirm("确定要删除吗？"))
    	{
	    	deleteMedicalCase(id);
    	}
	});
	
	//关闭弹窗按钮
	$(".popupCloseBtn").on('click',function()
	{
		reset();
	});
});

function search(searchCaseId,searchPatientId,path){
	$.ajax({
        type:"post",
        cache: false,
        dataType:"json",
        data:{'searchCaseId':searchCaseId,'searchPatientId':searchPatientId}, //新建一个对象填充
        url: path,
        success: function(date)         
        {       	
        	 alert(data.message);
        	 var searchPages=data.medicalCase.size/2;
        	 $(".CasePagenation").twbsPagination({ 
        		 currentPage:currPage,
                 onPageClick: function (evt, page) {
               	 currPage = page;             	            
               	for(var i=0;i<date.medicalCaseList.length;i++)
	           	{
	           		 var temp = date.medicalCaseList[i];
	           		 var obj = 
	           		 {        				 
	           			 case_id : temp.id,
	           			 case_user_name : temp.patient.user.user_name,
	           			 case_des : temp.description,
	           			 case_doctor:temp.patient.doctor.user.user_name,
	           		 };
	           		 medicalCaseList.push(obj);
	           	}           	 
	           	 $("#AllCase").html("");
	           	 $("#CaseInfoTemplate").tmpl(medicalCaseList).appendTo("#AllCase");
                }, 
                totalPages: searchPages,
            });  
	    },
        error:function(data){
        	alert("error"+data.message);
        }
	 });
}

//得到页面总数
function getPage(path)
{
	getList(1);
	$.ajax({
        type:"get",
        cache: false,
        url: path,   
        dataType:"json",
        success: function (data) {  
        	TotalPage = data.page;    
        	
        	$(".CasePagenation").twbsPagination({ 
        		 currentPage:currPage,
                 onPageClick: function (evt, page) {
               	 currPage = page;             	            
               	 getList(page);
                }, 
                totalPages: TotalPage,
            });  
        },
	});	
}

//根据后台传递来的数据填充前台
function fillForm(medicalCase,birthdate)
{	
	var gender=medicalCase.patient.user.gender;
	var blood=medicalCase.patient.blood;
	var hospital_id=medicalCase.patient.doctor.hospitalDepartment.hospital.id;
	var dep_id=medicalCase.patient.doctor.hospitalDepartment.department.id;
	var doctor_id=medicalCase.patient.doctor.user.id;
	$("#doctor_id").each(function(){  
	    if($(this).val() == doctor_id){  
	        $(this).prop( "checked", true );  
	    }  
	});  
	$(".gender").each(function(){  
	    if($(this).val() == gender){  
	        $(this).prop( "checked", true );  
	    }  
	});  
	$("#hospital_id").each(function(){  
	    if($(this).val() == hospital_id){  
	        $(this).prop( "checked", true );  
	    }  
	});
	$("#dep_id").each(function(){  
	    if($(this).val() == dep_id){  
	        $(this).prop( "checked", true );  
	    }  
	});
	
	//要修改的表单
	$("#id").val(medicalCase.id);
	$("#patient_id").val(medicalCase.patient.user.id);
	$("#user_name").val(medicalCase.patient.user.user_name);
	$("#idCard").val(medicalCase.patient.user.idCard);
	$("#tel").val(medicalCase.patient.user.tel);
	$("#email").val(medicalCase.patient.user.email);
	$("#birthdate").val(birthdate);
	$("#blood").val(blood);
	$("#family_sick").val(medicalCase.family_sick);
	$("#before_sick").val(medicalCase.before_sick);
	$("#description").val(medicalCase.description);
	$("#file_path").val(medicalCase.file_path);
}

//删除函数
function deleteMedicalCase(id)
{
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+"/MedicalCase/manage/delete/"+id,
        success: function (data) {  
        	alert("删除成功");
        	ResetTotalPage();
        },
	});		
}



//重置页面数量
function ResetTotalPage()
{ 
	$(".CasePagenation").twbsPagination('destroy');
	
	getPage(totalPagePath);	            
}


function reset()
{
	$("#id").val(0);  
	$("#user_name").val(''); 
	$("#user_password").val(''); 
	$("#idCard").val(''); 
	$("#tel").val(''); 
	$("#email").val('');
	$("#birthdate").val('');
	
}

//修改表单内容填充
function update()
{
	//修改提交页面时的地址	
	$.ajax({
        type:"get",
        cache: false,
        url: path,   
        dataType:"json",
        success: function (data) {     
        	fillForm(data.updateMedicalCase,data.patbirthdate);

        },
	  error:function(data)
	  {		 
		  alert("error"+data.message);
	   }
	 });
}

//分页数据查询
function getList(page)
{	
	 $.ajax({
         type:"get",
         cache: false,
         dataType:"json",
         url: basePath+'/MedicalCase/manage/list/'+page,
         success: function(date)         
         {       	
        	 var medicalCaseList = [];         	
        	 for(var i=0;i<date.medicalCaseList.length;i++)
        	{
        		 var temp = date.medicalCaseList[i];
        		 var obj = 
        		 {        				 
        			 case_id : temp.id,
        			 case_user_name : temp.patient.user.user_name,
        			 case_des : temp.description,
        			 case_doctor:temp.patient.doctor.user.user_name,
        		 };
        		 medicalCaseList.push(obj);
        	}           	 
        	 $("#AllCase").html("");
        	 $("#CaseInfoTemplate").tmpl(medicalCaseList).appendTo("#AllCase");
         }
	 });
}
