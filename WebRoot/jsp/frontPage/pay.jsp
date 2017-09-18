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

<script type="text/javascript" src="${basepath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery-ui.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.json.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="${basepath }/js/PayCtr.js"></script>
<title>Pay</title>
<script>
function fun(obj){
    obj.value = "";
    obj.type = "password";
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
	  <div class="nav">
	   <div class="logo"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><img src="${basepath }/images/logo.png"/></a></div>
			<div class="userName"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p><p id="currentPatId" hidden="hidden">${sessionScope.CurrentUser.id }</p></a></div>
	  </div>
	</div>

	<!--head over-->
	<!--main conten-->
	<div class="mainContent">
	    <div class="mainTop">
	       <div class="dia1"></div>
		   <p class="preAmount"></p>
		   <p class="amountBal"></p>
	    </div>
		<div class="mainNext">
		   <p><span></span></p>		   
		</div>
		<div class="mainNextp2"><b>快捷支付</b></div>
		<div class="mainMid">
		    <p class="tabTitle"><b>会诊信息：</b></p>
			<div class="tabContent">
				<table border="0">
				  <tr>
					<th>ID</th>
					<th>姓名</th>
					<th>主治医生</th>
					<th>诊断结果</th>
					<th>会诊时间</th>				
					<th>已缴预付款</th>
					<th>应缴费用</th>
					<th>还需支付</th>
				  </tr>
				  <tbody id="conBody">
				  </tbody>			 
				 
				</table>
                <p class="paySign"></p>
			</div>
			<div class="inputPwd">
				<p class="tabTitle"><b>输入密码：</b></p>
				
				<input type="password" id="test">
			</div>		 
		</div>
		<!-- <a href="pay_success.jsp"> -->
		<div class="bottomButton">
		    <p id="surePay">确认付款</p>
		</div>
		</a>
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
<script type="text/x-jquery-tmpl" id="conBodyTemplate">
	<tr>
	<td id="currentConId">\${currentConId}</td>
	<td id="currentPatName">\${currentPatName}</td>
	<td id="currentPatDoctor">\${currentPatDoctor}</td>
	<td id="currentCaseDes">\${currentCaseDes}</td>
	<td id="currentConTime">\${currentConTime}</td>
	<td id="currentPaid">\${currentPaid}</td>
	<td id="currentConAmount">\${currentConAmount}</td>
	<td id="currentNeedPay">\${currentNeedPay}</td>
	</tr>
</script>
</body>
</html>