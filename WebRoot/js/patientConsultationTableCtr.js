/**
 * 我的会诊
 */

var basePath = "";
var path = "";
var MedConTotalPage = 1;
var con_id = 0;

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]+'/MedicalConsultation'; 
    con_id = localStorage.getItem("theconsultationDetailId");  
    patient_consultationTable();
    $("#chooseTab").on("click",function()
    {
    	document.getElementById('light').style.display='block';
    });

    write();
  
 	
  });

function patient_consultationTable(){
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+'/getConsultationDetail/'+con_id,   
        dataType:"json",
        success: function (data) {  
        	if(data.medicalConsultationDetail.con_status<6 || data.medicalConsultationDetail.con_status==8)
    		{
        		document.getElementById('chooseTab').style.display='none';
    		 
    		}
        		
        	fillMedConFormList(data.medicalConsultationDetail,data.con_time);
        },error:function(data){		 
		  alert("请登录");
		  alert("error"+data.message);
	   }
	});
}	 

function fillMedConFormList(medicalConsultationDetail,con_time){
	
	//显示在页面的信息
	
		var status=(medicalConsultationDetail.con_status==0?'未付款':'已付款');
	
		var gender=(medicalConsultationDetail.medicalCase.patient.user.gender=='M'?'男':'女');
		
		 $("#currentConId").text( medicalConsultationDetail.id);
		 $("#currentpatientname ").text( medicalConsultationDetail.medicalCase.patient.user.user_name);
		 $("#currentgender").text(gender);
		 $("#currentdescription ").text( medicalConsultationDetail.medicalCase.description);
		 $("#currentbeforeSick ").text( medicalConsultationDetail.medicalCase.before_sick);
		 $("#currentfamliysick ").text( medicalConsultationDetail.medicalCase.family_sick);
		 $("#currentPatDoctor").text(medicalConsultationDetail.doctor.user.user_name);
		 $("#currentCurePlan").text(medicalConsultationDetail.cure.plan);
		 $("#currentConStatus").text(medicalConsultationDetail.con_status);
		 $("#currentConTime ").text( con_time);		
		 $("#currentEvaluate").text(medicalConsultationDetail.evaluate);
		
}


function write()
{ //修改治疗方案
	  $("#cure-submit").click(function(){
		  	var evaluate=$("#evaluate").val();
		  	document.getElementById('light').style.display='none';
		  
		  	$.ajax({
		          type:"get",
		          cache: false,
		          url: "http://localhost:8080/Medical/Patient/WriteEvaluate/"+con_id+"/"+evaluate,   
		          success: function (data) {  
		          	alert("操作成功");   
		          	patient_consultationTable();
		          },
		  	});	
		  	});
}

function getLocalTime(nS) {       
    return  new Date(parseInt(nS) * 1000).format("yyyy-MM-dd");    // hh:mm
 }  

Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
//           "h+": this.getHours(),
//           "m+": this.getMinutes(),
//           "s+": this.getSeconds(),
//           "q+": Math.floor((this.getMonth() + 3) / 3),
//           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}
