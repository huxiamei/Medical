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
<link href="${basepath }/css/recharge.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${basepath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery-ui.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.json.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="${basepath }/js/rechargeCtr.js"></script>

<title>Recharge</title>
<script>
//选择会诊单缴费
function selected(theName){
       var  name = theName.name; 
	   var name1=document.getElementById('payWay1').name;
       var name2=document.getElementById('payWay2').name;
       var name3=document.getElementById('payWay3').name;
	   var src1=document.getElementById('payWay1').src;
       var src2=document.getElementById('payWay2').src;
       var src3=document.getElementById('payWay3').src;
	   if(name == "unSelectedIcon")
	   {
         
		    theName.src="${basepath }/images/SelectedIcon.png";
            theName.name="SelectedIcon";
		}
       else
       {
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
	  </div>
	  <div class="nav">
	    <div class="logo"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><img src="${basepath }/images/logo.png"/></a></div>
			<div class="userName"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p><p id="currentPatId" hidden="hidden">${sessionScope.CurrentUser.id }</p></a></div>
	  </div>
	</div>
	</div>
	<!--head over-->
	<!--main conten-->
	<div class="mainContent">
	    <div class="mainTop">
	       <div class="dia1"></div>
		   <p class="rechargePage">充值页面</p>
		   <p class="amountBal"></p>
	    </div>
		<div class="mainNext">
		   <p>账户名：<span>${sessionScope.CurrentUser.user_name }</span></p>		   
		</div>
		
		<div class="mainMid">
		    <p class="tabTitle"><b>请选择支付方式：</b></p>
			<div class="rechargeWay">
				<table border="0">
				  
				  <tr>
				    <td>
						<div class="selectedBox" >
							<img src="${basepath }/images/unSelectedIcon.png" name="unSelectedIcon" onclick="selected(this)" id="payWay1" />
						</div>
					</td>
					<td>支付宝支付</td>
					<td><img src="${basepath }/images/pay_zhifubao.gif"/></td>
					
				  </tr>
				  <tr>
				    <td>
						<div class="selectedBox" >
							<img src="${basepath }/images/unSelectedIcon.png" name="unSelectedIcon" onclick="selected(this)" id="payWay2"/>
						</div>
					</td>
					<td>微信支付</td>
					<td><img src="${basepath }/images/pay_weixin.gif"/></td>
					
				  </tr>
				  <tr>
				    <td>
						<div class="selectedBox" >
							<img src="${basepath }/images/unSelectedIcon.png" name="unSelectedIcon" onclick="selected(this)" id="payWay3" />
						</div>
					</td>
					<td>在线支付</td>
					<td><img src="${basepath }/images/pay_zxzf.gif"/></td>
				
				  </tr>
				 
				</table>
              
			</div>
			<div class="inputPwd">
				<p class="tabTitle"><b>请输入充值金额：</b></p>
				
				<input type="text" id="rechargeMoney">
			</div>		 
		</div>
		<div class="bottomButton">
		    <p id="rechargeNow">立即充值</p>
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