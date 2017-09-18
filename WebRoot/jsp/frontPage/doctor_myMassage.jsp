
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

 <script type="text/javascript" src="${basepath }/js/jquery-2.1.4.js"></script>
<script src="${basepath }/js/bootstrap.min.js" type="text/JavaScript"></script>

<script type="text/javascript" src="${basepath }/js/jquery-ui.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="${basepath }/js/dateFormat.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.dateFormat.js"></script>
<script src="${basepath }/js/index.js" type="text/JavaScript"></script> 
<script src="${basepath }/js/messageCtr.js" type="text/JavaScript"></script> 



<title>Doctor</title>
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
		  
		}		
        if(liIndex == 0)
		{
		  $(".basicInfo_sent").css("display","none");
		  $(".basicInfo_received").css("display","block");
		  
		  
		}			
    });
		
});
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
			<div class="logo"><a href="${basepath }/jsp/frontPage/doctor_info.jsp"><img src="${basepath }/images/logo.png"/></a></div>
			<div class="userName"><a href="${basepath }/jsp/frontPage/doctor_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p></a></div>
	    </div>
	  <!-- 导航-->
	</div>
	
	<!--head over-->
	
	<!--main content-->
      <div class="mainContent">
	    <!--mainTop -->
		<div class="doctor_mainTop">
		
		  <div class="textTitle"><span>医生中心</span></div>
		  <p class="thisLocation">当前位置>我的消息</p>
		</div>
		<div class="mainMid">
		<!--leftSide -->
		   <div class="leftsideNav">
		   
		        <ul  >
					<li>
					    <dl>
					      <dt><a href="doctor_info.jsp">个人信息</a></dt>
					    </dl>
					</li>
				    <li>
					    <dl>
					     <dt><a href="doctor_brManage.jsp">病人管理</a></dt>
					    </dl>
					</li>
					<li>
					    <dl>
					    <dt><a href="doctor_myMassage.jsp">我的消息</a></dt>
						</dl>
				    </li>
					
					<li>
					    <dl>
						 <dt><a href="doctor_consultation.jsp">我的会诊</a></dt>
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
			<li id="receiveMsg" class="msgOn"><b>收到的消息</b></li>
			<li ><a><b>|</b></a></li>
			<li id="sendMsg" class="msgOff"><b>发送的消息</b></li>
			</ul>
			</div>
			<div class="basicInfo_received">
			    <div class="msgPanel">
				  <ul id="receiveMessage">
				   
					
				  </ul>
				  
				</div>
				<!-- 分页-->
				   <div class="paging">	                     
	                    <ul class="pagination pagination-sm RMessagePagenation" style="margin:0;"></ul>
				   </div>
				  <!--分页结束 -->
			</div>
			<div class="basicInfo_sent">
			    <div class="msgPanel">
				  <ul id="sendMessage">
				    
					
				  </ul>
				  
				</div>
				<!-- 分页-->
				   <div class="paging">			   
	                      <ul class="pagination pagination-sm SMessagePagenation" style="margin:0;"></ul>       
	                 </div>
				   </div>
				  <!--分页结束 -->
			</div>										
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
 
<script type="text/x-jquery-tmpl" id="SendMessageInfoTemplate">
     <li>
	<p class="msg-head">
    <span style="display:none" id="messageID">\${message_id}</span>	
	<span class="span-item"> \${send_time}</span>
	<span class="span-item">我给\${receive_userName}</span>
	<span class="msg-title">发送了一条消息:</span>  				  
	<span class="msg-delete"> 
	<a id="deleteMessage"><i>×删除</i></a>
	</span>					      
	</p>				
	<p class="msg-content">\${content}</p>
	</li>
</script>

<script type="text/x-jquery-tmpl" id="ReceiveMessageInfoTemplate">
     <li>
	<p class="msg-head">
    <span style="display:none" id="messageID">\${message_id}</span>	
	<span class="span-item"> \${send_time}</span>
	<span class="span-item">\${send_userName}给我</span>
	<span class="msg-title">发送了一条消息:</span>  				  
	<span class="msg-delete"> 
	<a id="deleteMessage"><i>×删除</i></a>
	</span>					      
	</p>				
	<p class="msg-content">\${content}</p>
	</li>
</script>
</body>
</html>