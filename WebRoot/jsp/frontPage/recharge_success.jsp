<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basepath" value="${pageContext.request.contextPath}"></c:set>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE"> 
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${basepath }/css/pay.css" rel="stylesheet" type="text/css">
<link href="${basepath }/css/success.css" rel="stylesheet" type="text/css">

<title>Recharge</title>
<script>
//选择会诊单缴费
function selected(theName){
  var  name = theName.name; 
  if(name=="unSelectedIcon")
  {
   theName.src="./images/SelectedIcon.png";
   theName.name="SelectedIcon";
  }
  else{
   theName.src="./images/unSelectedIcon.png";
   theName.name="unSelectedIcon"; 
  }
}

var t=5;//设定跳转的时间 
setInterval("refer()",1000); //启动1秒定时 
function refer(){ 
if(t==0){ 
location="patient_info.jsp"; //#设定跳转的链接地址 
} 
document.getElementById('jumpNumer').innerHTML=""+t; // 显示倒计时 
t--; // 计数器递减 
//本文转自： 
} 
</script>
</head>
<body>
  <div class="containner">
    <!--head -->
    <div class="head">
	  <div class="top">
	    <p>远程医疗会诊官网</p>
	  </div>
	  <div class="nav">
	   <div class="logo"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><img src="${basepath }/images/logo.png"/></a></div>
			<div class="userName"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p></a></div>	
	  </div>
	</div>
	</div>
	<!--head over-->
	<!--main conten-->
	<div class="mainContent">
	   <div class="tipIcon">
	  <img src="${basepath }/images/success.png" width="200" height="200"/>
	  <p class="tipWord_success"><strong>充值成功 !</strong></p>
	  <p class="tipJump_success"><strong>页面正在跳转<span class="jumpNumer_success" id="jumpNumer">5</span>.....</strong></p>
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
</body>
</html>