/**
 * 支付的js
 */
var basePath = "";
var path = "";
var id = 0;
var conlist = new Array();
//还需
var needPay=0;
$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    id = $("#currentPatId").text();
    
    //获得会诊信息
    getMedConsultationListInfo();
    
    $("#surePay").on("click",function(){
    	getPassword();
    	if(self.pay)
    	{
    		goToPay();
    	}
    });
   
});

function getPageInfo()
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
	var paystatus="未付预款";
	var conInfoList=[];
	//预付
	var preAmount=0;
	
	
	//显示在页面的信息
	for(var i=0;i< medicalConsultationList.length;i++){
		
		if(medicalConsultationList[i].con_status>=7){
			   paystatus = "已付完款";
		}else if(medicalConsultationList[i].con_status<7 && medicalConsultationList[i].con_status>=3){
			paystatus = "已付预款";
		}   
		
		
		 var obj = 
		 {
			 currentConId : medicalConsultationList[i].id,
			 currentPatName:medicalConsultationList[i].medicalCase.patient.user.user_name,
			 currentPatDoctor : medicalConsultationList[i].doctor.user.user_name,
			 currentCaseDes:medicalConsultationList[i].medicalCase.description,
			 currentConTime : conTimeList[i],
			 currentPaid:medicalConsultationList[i].amount*0.3,
			 currentConAmount:medicalConsultationList[i].amount,
			 currentNeedPay: medicalConsultationList[i].amount*0.7,  
		 };
		 conInfoList.push(obj);
		 preAmount+=obj.currentPaid;
		 needPay+=obj.currentNeedPay;
		 $(".amountBal").text("账户余额：￥"+medicalConsultationList[i].medicalCase.patient.account); 
		 $(".paySign").text("收款人："+medicalConsultationList[i].doctor.hospitalDepartment.hospital.hospital_name+"医院");
	}
	
	$(".preAmount").text("预付金额：￥"+preAmount); 	
	
	$(".mainNext p span").text("还需支付：￥"+needPay); 
	
	$("#conBody").html("");
	$("#conBodyTemplate").tmpl(conInfoList).appendTo("#conBody");
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

function getPassword()
{
	var self = this;
	var pwd= $("#test").val();
	
	if( pwd!="")
    {
		self.pwd = pwd;
		self.pay = true;
	}
	else
	{	
		self.pay = false;
		alert("支付密码不能为空");
	}
	
}

function goToPay()
{
	
	$.ajax({
        type:"GET",
        cache: false,
        url: basePath+'/Patient/getPatient/'+id,   
        dataType:"json",
        success: function (data) {  
        	if(self.pwd==data.patient.user.user_password && data.patient.account>=needPay){
        		for(var i=0;i<conlist.length;i++)
    			{
    			   needPay = conlist[i].amount*0.7;		  
    			  // alert(conlist[i].id);
    			   var account=data.patient.account-needPay;
           		 $.ajax({
           	        type:"post",
           	        cache: false,
           	        data:{'account':account},
           	        dataType:"json",
           	        async: false,  
           	        url: basePath+'/Patient/goToPay/'+id+"/"+conlist[i].id,
           	        success: function (data) { 
           	        	window.location.href=basePath+'/jsp/frontPage/pay_success.jsp';
           	        }
           		 });
    			}
        		
        		
        	}else if(self.pwd==data.patient.user.user_password && data.patient.account<needPay){
        		alert("余额不足，请先充值！");
        		window.location.href=basePath+'/jsp/frontPage/recharge.jsp';
        	}else if(self.pwd!=data.patient.user.user_password)
        		alert("支付失败,密码不正确！");
        },error:function(data){		 
		  alert("请登录");
		  alert("error"+data.message);
	   }
	});
	 return false;  
}
