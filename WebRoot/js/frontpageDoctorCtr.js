/**
 * 医生前台页面
 */

var basePath = "";
var path = "";
var id = 0;

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    id = $("#currentDocId").text();
   
    getDoctorInfo();
    
    $("#infoSubmit").on("click",function(){
    	
    	updateDoctor();
    });
});

function updateDoctor()
{
	path=basePath+"/FrontPageDoctor/update";
	$.ajax({
        type:"post",
        cache: false,
        dataType:"json",
        data:$("#updateDoctor").serialize(), //新建一个对象填充
        url: path,
        success: function (data) {  
        	//弹出框显示
        	alert(data.message);
        	getDoctorInfo();
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

function getDoctorInfo()
{
	
	path=basePath+"/FrontPageDoctor/getDoctor/"+id;
	$.ajax({
        type:"get",
        cache: false,
        url: path,   
        dataType:"json",
        success: function (data) {     
        	fillForm(data.doctor,data.docbirthdate);
        },
	  error:function(data)
	  {		 
		  alert("请登录");
		  alert("error"+data.message);
	   }
	 });
}


//根据后台传递来的数据填充前台
function fillForm(doctor,docbirthdate)
{
	
	
	//要修改的表单
	$("#id").val(doctor.user.id);  
	$("#user_name").val(doctor.user.user_name); 
	$("#tel").val(doctor.user.tel); 
	$("#email").val(doctor.user.email);
	$("#birthdate").val(docbirthdate);
	 
	var gender = doctor.user.gender;
	var hospital = doctor.hospitalDepartment.hospital.hospital_name;
	var dep = doctor.hospitalDepartment.department.dep_name;
	
	$("#gender").val(gender);

	$("#hospital").text(hospital); 	
	$("#department").text(dep);
	
	
	//显示在页面的信息
	$("#currentDocName").text(doctor.user.user_name);
	$("#currentDocsex").text(doctor.user.gender);
	$("#currentDocdate").text(docbirthdate);
	$("#currentDocemail").text(doctor.user.email);
	$("#currentDoctel").text(doctor.user.tel);
	$("#currentDochospital").text(hospital);
	$("#currentDocdepartment").text(dep);
	
}