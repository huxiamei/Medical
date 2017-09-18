/**
 * 医生的所有病人
 */

var basePath = "";
var path = "";
var currPage = 1;
var TotalPage = 1;

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    getPatientList();
    
});


function getPatientList()
{
	 $.ajax({
         type:"get",
         cache: false,
         dataType:"json",
         url: basePath+'/FrontPageDoctor/getPatientList' ,
         success: function(date)         
         {       	
        	 var patientList = [];         	
        	 for(var i=0;i<date.patientList.length;i++)
        	{
        		 var temp = date.patientList[i];
        		 var time = date.BirthdayList[i];
        		 
        		 var obj = 
        		 {        				 
        			 patient_id : temp.user.id,
        			 patient_name : temp.user.user_name,
        			 patient_gender : temp.user.gender,
        			 patient_birthday :time,//修改，显示不了出生日期
        			 patient_email:  temp.user.email ,
        			 patient_tel: temp.user.tel,
        			 patient_hospital:temp.doctor.hospitalDepartment.hospital.hospital_name,
        			 patient_department:temp.doctor.hospitalDepartment.department.dep_name,
        		 };
        		 patientList.push(obj);
        	}           	 
        	 $("#patientList").html("");
        	 $("#PatientInfoTemplate").tmpl(patientList).appendTo("#patientList");
         }
	 });
}