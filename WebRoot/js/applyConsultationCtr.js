/**
 * applyConsultation page
 */

//$(document).ready(function () {
//	
//	$(document).on('click','#submitConsu',function()
//	{
//		var pid = $("#patientidba").text();
//		alert(pid);
//		var did = $("#doctoridba").text();
//		ApplyConsultation(pid,did);
//		
//	});
//});

function ApplyConsultation(patientId, doctorId){
	//家族病史
	var jzbs = $('#jzbs').val();
	//过往病史
	var gwbs = $('#gwbs').val();
	//病情描述
	var bqms = $('#bqms').val();
	//会诊表
	var medicalConsultation = {};
	//病例信息
	var medicalCase = {};
	//病人
	var patient = {};
	//病人的用户信息
	var user = {};
	
	user.id = patientId;
	patient.user = user;
	medicalCase.patient = patient;
	
	medicalCase.family_sick = jzbs;
	medicalCase.before_sick = gwbs;
	medicalCase.description = bqms;
	
	//获得日期
	var consultationDate = $('#txtBeginDate').val();
	medicalConsultation.stringDate = consultationDate;
	//被选择医生id
	var strId = $('#hideDoctorList').val();
//	var idList = "8/10";
	medicalConsultation.selectDoctorId = strId;
	//alert($('#hideDoctorList').val());
	var path = $('#hideFilePath').val();
	//alert(path);
	medicalCase.file_path = path;medicalConsultation.medicalCase = medicalCase;
	//alert(medicalCase.file_path);
	
	new MyAjax().onAjaxPost({
		 url : "http://localhost:8080/Medical/applyConsultation/getConsultationTable",
		 data : medicalConsultation
		 }, function(backObject) {
			 
			 
		 });
	window.location.href = "http://localhost:8080/Medical/jsp/frontPage/doctor_info.jsp";
}

function selectHos(){
	var html = "";
	// 获取选择医院
    new MyAjax().onAjaxGet({
        url: "http://localhost:8080/Medical/applyConsultation/getSelectHos",
    }, function (hospitalLsit) {
    	for(var i in hospitalLsit){
    		$("#selectHospital").html("");
    		var html =  '<option value="'+ hospitalLsit[i].id +'">'+hospitalLsit[i].hospital_name +'</option>';
    		$("#selectHospital").append(html);
    	}
    	
    });
} 	

//给department的select选择框赋值  
function setDepartmentValue(){
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    
	var head = "<option value='";
	var footer = "</option>";
	
	$("#selectDepartment").empty();	
	
	var hospital = $("#selectHospital").val();
	
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
    		 var html = '<option>请选择科室</option>';
    		 $("#selectDepartment").append(html);
			 for(var i=0;i<data.departmentList.length;i++)
			{
	    		var dep = data.departmentList[i];
	    		var option = head + dep.id +"'>"+dep.department.dep_name+footer;
			    $("#selectDepartment").append(option);
			}  
			 
		 }	     	
    },
    error:function(data)
	  {		 
		  alert("error"+data.message);
	   }
	 });
}

function setDoctors(){
	$('#showDoc').empty();	
	var departmentId = $('#selectDepartment').val();
	var doctor= {};
//	var hospitalDepartment = {};
//	var department = {};
//	department.id = departmentId;
//	hospitalDepartment.department = department;
//	doctor.hospitalDepartment = hospitalDepartment;
	doctor.department = departmentId;
	 new MyAjax().onAjaxGet({
       url: "http://localhost:8080/Medical/applyConsultation/getDoctor",
       data:doctor
   }, function (doctorLsit) {
	   	var html = '<ul>';
	   	for(var i in doctorLsit){
	   		html += '<li id="'+doctorLsit[i].user.id+'"><input type="hidden" value="" class="test"/>'+doctorLsit[i].user.user_name+'</li>';
	   		
	   	}
	   	htmml =+ '</ul>';
	   	$("#showDoc").append(html);
	   	chooseDoc(doctorLsit);
	   	
   });
}

function chooseDoc(flag){
    var valueStr;
    var valueStrId;
//    var idStr="";
	
	 for(var i =0;i<flag.length;i++)
	 {
	    flag[i] = 0;
	 }
	 
   $("#showDoc li").click(function(){	  
	  var liIndex =$(this).index();
		  if(flag[liIndex]==0)
		  {
		    flag[liIndex]=1;
		  }
		  else{
		    flag[liIndex]=0;
		  }
		  valueStr ="";
		  valueStrId="";
		  for(var i=0;i<4;i++)
		  {
		    if(flag[i]==1)
			{
			  var str = $("#showDoc").find("li").eq(i).text(); 
			  valueStr = valueStr + str+",";
			  var strId = $("#showDoc").find("li").eq(i).attr("id");
			  valueStrId = valueStrId + strId+"/";
			}
		  }
		  var showStr = valueStr.substr(0,valueStr.length-1);
	      $("#chooseDoctors").val(showStr);
	      var showStrId = valueStrId.substr(0,valueStr.length-1);
	      $("#hideDoctorList").val(showStrId);
	      
//	      var idVal = $(this).attr('id');
//	      var $input = $(this).children();
//	      var inputVal = $input.val();
//	      if(inputVal == '')
//	    	  $input.val(idVal);
//	      else
//	    	  $input.val("");
	      
   });
 
}


$(function(){
	
	//设置科室
	setDepartmentValue();
	$("#selectHospital").on('change',function()
	{
		setDepartmentValue();
	});
	
	$('#selectDepartment').on('change',function()
	{
		 setDoctors();
	});
	
	$('.submitFile').click(function(){
		  var formData = new FormData($( "#uploadForm" )[0]);  
		     $.ajax({  
		          url: "http://localhost:8080/Medical/upload/springUpload",  
		          type: 'POST',  
		          data: formData,  
		          async: false,  
		          cache: false,  
		          contentType: false,  
		          processData: false,  
		          success: function (returndata) {  
		             //alert(returndata.file); 
		        	 $('#hideFilePath').val(returndata.file);
		        	 alert("上传成功");
		          } 
		          
		     });  
		
		
	});
});
