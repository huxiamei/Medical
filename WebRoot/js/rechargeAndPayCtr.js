/**
 * 充值支付
 */
var basePath = "";
var path = "";
var id = 0;
var conlist = new Array();

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    id = $("#currentPatId").text();
    
    //获得病人基本信息
    getPatientInfo();
    
    //获得会诊记录
    $("#sendMsg").on("click",function(){
    	getMedConsultationListInfo();
    });
    //充值
    $("#recharge").on("click",function(){
    	
    	recharge();
    });

    //缴费
    $("#paMoney").on("click",function(){
    	
    	paMoney();
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
	$("#currentPatIdCard").text(patient.user.idCard); 
	$("#currentPatAccount").text(patient.account);
}

function fillMedConFormList(medicalConsultationList,conTimeList){
	
	var conInfoList=[];
	//显示在页面的信息
	for(var i=0;i< medicalConsultationList.length;i++){
		
		var paystatus = "未付预款";
		if(medicalConsultationList[i].con_status>=7){
			   paystatus = "已付完款";
		}else if(medicalConsultationList[i].con_status<7 && medicalConsultationList[i].con_status>=3){
			paystatus = "已付预款";
		}   
		
		$("#currentConId").text(medicalConsultationList[i].id); 
		$("#currentPatDoctor").text(medicalConsultationList[i].doctor.user.user_name); 
		$("#currentConTime").text(conTimeList[i]);
		$("#currentConStatus").text(paystatus);
		$("#currentPatAccount").text(medicalConsultationList[i].medicalCase.patient.account);
		
//		$(".conInfo").append("<tr class='conInfo'>"+
//				"<td id='currentConId'><a href='patient_consultationTable.jsp'></a></td>" +
//				"<td id='currentPatDoctor'></td>"+
//				"<td id='currentConTime'></td>"+
//				"<td id='currentConStatus'></td>" +
//				"<td><div class='selectedBox'><img src='basePath/images/unSelectedIcon.png' name='unSelectedIcon' onclick='selected(this)' /></div></td>"+   
//		"</tr>");
		 var obj = 
		 {
			 currentConId : medicalConsultationList[i].id,
			 currentPatDoctor : medicalConsultationList[i].doctor.user.user_name,
			 currentConTime : conTimeList[i],
			 currentConStatus:paystatus,
			 currentPatAccount: medicalConsultationList[i].amount,  
		 };
		 conInfoList.push(obj);
	}
	$("#conInfoList").html("");
	$("#conInfoTemplate").tmpl(conInfoList).appendTo("#conInfoList");
}

function showBotton()
{

	 $(".chooseTab").css("display","block"); 
	  $(".updateInfo").css("display","none");
	  $(".infoSubmit").css("display","none");
	  $(".update").css("display","block");
}
function getMedConsultationListInfo()
{
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+'/MedicalConsultation/getMedicalConsultationList/'+id,   
        dataType:"json",
        success: function (data) {  
        	for(var i=0;i<data.medicalConsultationList.length;i++)
    		{
        		var temp = data.medicalConsultationList[i];
        		if(temp.con_status==5)
    			{
        			conlist.push(temp);
    			} 		   
    		}
        	fillMedConFormList(conlist,data.conTimeList);
        },error:function(data){		 
		  alert("请登录");
		  alert("error"+data.message);
	   }
	});
}

