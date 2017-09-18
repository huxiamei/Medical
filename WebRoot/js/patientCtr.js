/**
 * 病人后台管理js
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
    getPage();
    
    //新增病人 
	$("#PatientAdd").on('click',function()
	{		
		path = basePath+"/Patient/manage/add";
		
	});

	//点击修改
	$(document).on('click','#patientUpdate',function()
	{
		id = $(this).parent().parent().find("#patientId").text();
		$("#PatientLayer").css("display","block");
		path=basePath+"/Patient/manage/update/"+id;
		update();	
	});
	
	//提交新增或修改表单
	$("#PatientSubmits").on('click',function(){
	
		$.ajax({
	        type:"post",
	        cache: false,
	        dataType:"json",
	        data:$("#patientDetail").serialize(), //新建一个对象填充
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
	$(document).on('click','#patientDelete',function()
	{	
		var id = $(this).parent().parent().find("#patientId").text();
	    if(confirm("确定要删除吗？"))
    	{
	    	deletePatient(id);
    	}
	});
	
	//关闭弹窗按钮
	$(".popupCloseBtn").on('click',function()
	{
		reset();
	});
});

//得到页面总数
function getPage()
{
	getList(1);
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+'/Patient/manage/totalPage',   
        dataType:"json",
        success: function (data) {  
        	TotalPage = data.page;    
        	
        	$(".PatientPagenation").twbsPagination({ 
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
function fillForm(patient,birthdate)
{	
	var gender=patient.user.gender;
	var blood=patient.blood;
	
	$(".gender").each(function(){  
	    if($(this).val() == gender){  
	        $(this).prop( "checked", true );  
	    }  
	});  
	
	//要修改的表单
	$("#id").val(patient.user.id);
	$("#doctor_id").val(patient.doctor.user.id);
	$("#user_name").val(patient.user.user_name);
	$("#user_password").val(patient.user.user_password);
	$("#idCard").val(patient.user.idCard);
	$("#tel").val(patient.user.tel);
	$("#email").val(patient.user.email);
	$("#birthdate").val(birthdate);
	$("#blood").val(blood);
	$("#account").val(patient.account);
}

//删除函数
function deletePatient(id)
{
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+"/Patient/manage/delete/"+id,
        success: function (data) {  
        	alert("删除成功");
        	ResetTotalPage();
        },
	});		
}



//重置页面数量
function ResetTotalPage()
{ 
	$(".PatientPagenation").twbsPagination('destroy');
	
	getPage();	            
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
        	fillForm(data.updatePatient,data.patbirthdate);

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
         url: basePath+'/Patient/manage/list/'+page,
         success: function(date)         
         {       	
        	 var patientList = [];         	
        	 for(var i=0;i<date.patientList.length;i++)
        	{
        		 var temp = date.patientList[i];
        		 var obj = 
        		 {        				 
        			 pat_id : temp.user.id,
        			 pat_name : temp.user.user_name,
        			 pat_gender : temp.user.gender=='M'?'男':'女',
        			 pat_email : temp.user.email,
        			 pat_doctor:temp.doctor.user.user_name,
        		 };
        		 patientList.push(obj);
        	}           	 
        	 $("#AllPatient").html("");
        	 $("#PatientInfoTemplate").tmpl(patientList).appendTo("#AllPatient");
         }
	 });
}
