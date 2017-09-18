<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basepath" value="${pageContext.request.contextPath}"></c:set>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE"> 
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link href="${basepath }/css/index.css" rel="stylesheet" type="text/css"> 
<link href="${basepath }/css/doctor_myMassage.css" rel="stylesheet" type="text/css"> 
<link href="${basepath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${basepath }/css/common.css" rel="stylesheet" type="text/css">
<link href="${basepath }/css/rechargeAndPay.css" rel="stylesheet" type="text/css">
<script src="${basepath }/js/jquery-1.11.1.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/bootstrap.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/index.js" type="text/JavaScript"></script> 
<script src="${basepath }/js/rechargeAndPayCtr.js" type="text/JavaScript"></script> 

<script type="text/javascript" src="${basepath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery-ui.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.json.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.twbsPagination.js"></script>
<title>Patient</title>
<script type="text/javascript">



function updateButton(){
    
  $(".chooseTab").css("display","none"); 
  $(".updateInfo").css("display","block");
  $(".infoSubmit").css("display","block");
  
}

$(function(){
    $("#msg li").click(function(){	  
		$("#msg li").removeClass("msgOn");
	    $("#msg li").addClass("msgOff");
        $(this).removeClass("msgOff");
        $(this).addClass("msgOn");  
        var liIndex = $(this).index();
	   
		if(liIndex == 2)
		{
		  
		  $(".basicInfo_received").css("display","none");
		  $(".basicInfo_sent").css("display","block");
		  $(".recharge").css("display","none");
		  $(".paMoney").css("display","block");
		  
		}		
        if(liIndex == 0)
		{
		  $(".basicInfo_sent").css("display","none");
		  $(".basicInfo_received").css("display","block");
		  
		   $(".recharge").css("display","block");
		  $(".paMoney").css("display","none");
		  
		}			
    });
		
});
//选择会诊单缴费
function selected(theName){
  var  name = theName.name; 
  if(name=="unSelectedIcon")
  {
   theName.src="${basepath }/images/SelectedIcon.png";
   theName.name="SelectedIcon";
  }
  else{
   theName.src="${basepath }/images/unSelectedIcon.png";
   theName.name="unSelectedIcon"; 
  }
}
</script>
</head>
<body>
  <div class="containner">
    <!--head -->
    <div class="head">
	  <div class="top">
	    <p>远程医疗会诊官网</p>
		<span class="top_login"><a href="login.jsp">[ 退出 ]</a></span>
	  </div>
	  <!-- 导航-->
	  
	    <div class="nav">
			<div class="logo"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><img src="${basepath }/images/logo.png"/></a></div>
			<div class="userName"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p></a><p id="currentPatId" hidden="hidden">${sessionScope.CurrentUser.id}</p></div>
	    </div>
	  <!-- 导航-->
	</div>
	
	<!--head over-->
	
	<!--main conten-->
      <div class="mainContent">
	    <!--mainTop -->
		<div class="doctor_mainTop">
		  <!--<div class="docPhoto"><img src="images/doctor.png" width="150" height="200"/></div>-->
		  <div class="textTitle"><span>病人中心</span></div>
		  <p class="thisLocation">当前位置>我的消息</p>
		</div>
		<div class="mainMid">
		<!--leftSide -->
		   <div class="leftsideNav">
		   
		        <ul  >
					<li>
					    <dl>
					      <dt><a href="patient_info.jsp">个人信息</a></dt>
					    </dl>
					</li>
				    
					<li>
					    <dl>
					    <dt><a href="patient_myMassage.jsp">我的消息</a></dt>
						</dl>
				    </li>
					<li>
					    <dl>
					    <dt><a href="patient_consultation.jsp">我的会诊</a></dt>
						</dl>
				    </li>
					<li>
					    <dl>
						 <dt><a href="patient_rechargeAndPay.jsp">充值缴费</a></dt>
						</dl>
					</li>
				</ul>
		   </div>
		<!--leftSide over-->
		<div class="clear"></div>
		<div class="rightContent">
		    <div class="msgTab">
			<!-- 基本信息-->
			<ul id="msg">
			<li id="receiveMsg" class="msgOn"><b>充值</b></li>
			<li ><a><b>|</b></a></li>
			<li id="sendMsg" class="msgOff"><b>缴费</b></li>
			</ul>
			</div>
			
			<!-- 充值页面-->
			<div class="basicInfo_received">
			   
			    <div class="msgPanel">
				  <div class="basicInfo">
			    <table border="0">
				    <tr colspan="3"> <p class="userInfo"><b>账户信息</b></p><hr/></tr>
					<tr>
						<th>ID</th>
						<th>姓名</th>
						<th>性别</th>
						<th>生日</th>
						<th>邮箱</th>
						<th>电话</th>
						<th>身份证号</th>
						<th>账户余额</th>	
                          						
					</tr>				 
				    <tr>
				    	<td id="currentPatId">${sessionScope.CurrentUser.id}</td>
						<td id="currentPatName"></td>
						<td id="currentPatGender"></td>
						<td id="currentPatBirthdate"></td>
						<td id="currentPatemail"></td>
						<td id="currentPattel"></td>
						<td id="currentPatIdCard"></td>
						<td id="currentPatAccount"></td>
				    </tr>
				</table>
		
				  
				</div>
				  
				</div>
			</div>
			<!-- 缴费页面-->
			<div class="basicInfo_sent">
			    <div class="msgPanel">
				 <div class="basicInfo">
				  <!--我参与的会诊 -->
					<table border="0" class="hzInfo1">
						<tr>
							<th>会诊ID</th>
							<th>主治医生</th>
							<th>会诊时间</th>
							<th>是否付款</th>
							<th>选择</th>
													
						</tr>				 
						<tbody id="conInfoList">
                      
                    	</tbody>	
						
					</table>
	  
				</div>
		
				  
				</div>
			</div>		
			
			<!-- 充值-->
			<a href="recharge.jsp" style="text-decoration:none;line-height:40px;font-size:16px;" ><div class="recharge">点击充值</div></a>
			<!-- 充值-->
			<!-- 缴费-->
			<a href="pay.jsp" style="text-decoration:none;line-height:40px;font-size:16px;" ><div class="paMoney">点击缴费</div></a>
			<!-- 缴费-->
			
			
		</div>
		
		</div>	
	    
	    
	  </div>
	
	<!--main conten over-->
	<!--footer-->
	<div class="footer">
		<div class="copyright">
			<p>版权所有 Copyright(C)2016-2017 六小只医疗有限公司</p>	
		</div>
	    
	</div>
	<!--footer-->
	
 </div>

<script type="text/x-jquery-tmpl" id="conInfoTemplate">
	<tr>
	<td id="currentConId"><a href="patient_consultationTable.jsp">\${currentConId}</a></td>
	<td id="currentPatDoctor">\${currentPatDoctor}</td>
	<td id="currentConTime">\${currentConTime}</td>
	<td id="currentConStatus">\${currentConStatus}</td>
	<td><div class="selectedBox" ><img src="${basepath }/images/unSelectedIcon.png" name="unSelectedIcon" onclick="selected(this)" /></div></td>    
	</tr>
</script>						
</body>
</html>