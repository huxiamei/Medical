/**
 * 病人
 */
var basePath = "";
var path = "";
var id = 0;
var TotalPage=1;
var currPage=1;
$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    id = $("#currentPatId").text();
    getPatientInfo();
    
    $("#infoSubmit").on("click",function(){
    	
    	updatePatient();
    });
});

function getPatientInfo()
{
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+'/Patient/getPatient/'+id,   
        dataType:"json",
        success: function (data) {  
        	fillForm(data.patient,data.birthdate);
        },error:function(data){		 
		  alert("请登录");
		  alert("error"+data.message);
	   }
	});
}


//根据后台传递来的数据填充前台
function fillForm(patient,birthdate)
{	
	var gender=(patient.user.gender=='M'?'男':'女');
	
	//显示在页面的信息
	$("#currentPatName").text(patient.user.user_name); 
	$("#currentPatGender").text(gender); 
	$("#currentPatBirthdate").text(birthdate);
	$("#currentPatemail").text(patient.user.email);
	$("#currentPattel").text(patient.user.tel); 
	//$("#idCard").text(patient.user.idCard); 
	$("#currentPatdepartment").text(patient.doctor.hospitalDepartment.department.dep_name);
	$("#currentPathospital").text(patient.doctor.hospitalDepartment.hospital.hospital_name);

	//要修改的表单
	$("#id").val(patient.user.id); 
	$("#user_name").val(patient.user.user_name);
	$("#gender").val(patient.user.gender);
	$("#birthdate").val(birthdate);
	$("#email").val(patient.user.email);
	$("#tel").val(patient.user.tel);
	$("#hospital").text(patient.doctor.hospitalDepartment.hospital.hospital_name); 	
	$("#department").text(patient.doctor.hospitalDepartment.department.dep_name);
}

function updatePatient()
{
	$.ajax({
        type:"post",
        cache: false,
        dataType:"json",
        data:$("#updatePatient").serialize(), //新建一个对象填充
        url: basePath+'/Patient/updatePatient',
        success: function (data) {  
        	//弹出框显示
        	alert(data.message);
        	getPatientInfo();
        	showBotton(); 
        	
        },
        error:function(data)
	   {      	
        	alert("error"+data.message);
	   }
	 });
}

function showBotton()
{

	 $(".chooseTab").css("display","block"); 
	  $(".updateInfo").css("display","none");
	  $(".infoSubmit").css("display","none");
	  $(".update").css("display","block");
}

//设置医院select的值
function setHospitalValue()
{	
	var head = "<option value='";
	var footer = "</option>";
	
	//给hospital的select选择框赋值
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+"/Hospital/manage/Alldata",   
        dataType:"json",
        success: function (data) {          
        	for(var i=0;i<data.hospitalList.length;i++)
    		{
        		var hospital = data.hospitalList[i];
        		var option = head + hospital.id +"'>"+hospital.hospital_name+footer;     		
    		    $("#hospital").append(option);
    		}  
        	setDepartmentValue();
        },
        error:function(data)
  	  {		 
  		  alert("error"+data.message);
  	   }
  	 });
}

//给department的select选择框赋值  
function setDepartmentValue(dep)
{
	var head = "<option value='";
	var footer = "</option>";
	
	$("#hospitalDepartment").empty();	
	
	var hospital = $("#hospital").val();
	var ddd = dep;
	
    $.ajax({
     type:"get",
     cache: false,
     url: basePath+"/HospitalDepartment/manage/getDepartment/"+hospital,   
     dataType:"json",
     success: function (data) { 
    	 if(data.departmentList.length==0)
		 {	 
    		 alert("该医院下没有科室，请确认选择");
		 }
    	 else
		 {
			 for(var i=0;i<data.departmentList.length;i++)
			{
	    		var dep = data.departmentList[i];
	    		var option = head + dep.id +"'>"+dep.department.dep_name+footer;
			    $("#hospitalDepartment").append(option);
			}  
			 if(ddd !="")
			{
				 $("#hospitalDepartment").val(ddd); 
			}
		 }	     	
    },
    error:function(data)
	  {		 
		  alert("error"+data.message);
	   }
	 });
}


//删除函数
function deletePatient()
{
	$.ajax({
        type:"get",
        cache: false,
        url: path,   
        success: function (data) {  
        	alert("删除成功");
        	ResetTotalPage();
        },
	});		
}
//得到页面总数
function getPage()
{
	getList(1);
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+'/Patient/manage/selectPatient',   
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


//重置页面数量
function ResetTotalPage()
{ 
	$(".DcotorPagenation").twbsPagination('destroy');
	
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
        	fillForm(data.Updatedoctor,data.docbirthdate);

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
        			 pat_gender : temp.user.gender,
        			 pat_birthdate:temp.user.birthdate,
        			 pat_email : temp.user.email,
        			 pat_tel:temp.user.tel
        		 };
        		 patientList.push(obj);
        	}           	 
        	 $("#AllPatient").html("");
        	 $("#PatientInfoTemplate").tmpl(patientList).appendTo("#AllPatient");
         }
	 });
}
