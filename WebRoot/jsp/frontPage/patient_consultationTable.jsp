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
<link href="${basepath }/css/font-awesome.css" rel="stylesheet" />
<script src="${basepath }/js/jquery-1.11.1.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/bootstrap.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/index.js" type="text/JavaScript"></script> 
<script src="${basepath }/js/patientConsultationTableCtr.js" type="text/JavaScript"></script> 

<script type="text/javascript" src="${basepath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery-ui.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.json.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.twbsPagination.js"></script>
<title>Doctor</title>
<script type="text/javascript">

function updateButton(){
    
 //点击弹出浮框
 
  
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

function dropDownLi(){
if($(".dropDownLi").css("display") == "none")
{
 $(".dropDownLi").css("display","block");
}
else{
$(".dropDownLi").css("display","none");
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
			<div class="userName"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p></a><p hidden="hidden" id="currentPatId">${sessionScope.CurrentUser.id}</p></div>

	    </div>
	  <!-- 导航-->
	</div>
	
	</div>
	<!--head over-->
	
	<!--main conten-->
      <div class="mainContent">
	    <!--mainTop -->
		<div class="doctor_mainTop">
		  <!--<div class="docPhoto"><img src="images/doctor.png" width="150" height="200"/></div>-->
		  <div class="textTitle"><span>病人中心</span></div>
		  <p class="thisLocation">当前位置>会诊表详情</p>
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
						 <dt><a href="rechargeAndPay.jsp">充值缴费</a></dt>
						</dl>
					</li>
				</ul>
		   </div>
		    <!--leftSide over-->
			<div class="clear"></div>
			<div class="rightContent">
		
		    <div class="msgTab">
			<!-- 基本信息-->
			<p class="basicInfoTitle"><b>会诊表详情</b></p>
			</div>
			
			<div class="basicInfo_received">
			    <div class="msgPanel">
					<div class="basicInfo">
						<table border="0">
							<tr>
								<th>会诊ID</th>	
								<td id="currentConId"></td>                          						
							</tr>				 
							<tr>
							   <th>病人姓名</th>
								<td id="currentpatientname"></td>						
							</tr>
							<tr>
							   <th>病人性别</th>
								<td id="currentgender"></td>						
							</tr>
							<tr>
							   <th>病情描述</th>
								<td id="currentdescription"></td>						
							</tr>
							<tr>
							   <th>以往病史</th>
								<td id="currentbeforeSick"></td>						
							</tr>
							<tr>
							   <th>家族病史</th>
								<td id="currentfamliysick"></td>						
							</tr>
							
							<tr>
							   <th>主治医生</th>
								<td id="currentPatDoctor"></td>						
							</tr>
							<tr>
							   <th>治疗方案</th>
								<td id="currentCurePlan"></td>						
							</tr>
							<tr>
							   <th>会诊状态</th>
								<td id="currentConStatus"></td>						
							</tr>
							<tr>
							   <th>会诊时间</th>
								<td id="currentConTime"></td>						
							</tr>
							<tr>
							  <th>会诊评价</th>
								<td id="currentEvaluate"></td>
							</tr>
							
						</table>				

					</div>
		
					
			    </div>
			   <!-- 填写治疗方案-->			
			<div class="chooseTab" id="chooseTab" >
			    <span class="treatment">填写评价</span>
			</div>
			
			<div id="light" class="white_content">
				<a href = "javascript:void(0)" >				
				<div class="treatment_bg">
				 <span class="closeIcon" value="SAAAAAAAAAAAAAA" onclick = "document.getElementById('light').style.display='none';">×</span>
				 <textarea name="content" class="edit-cure" id="evaluate" rows="10" cols="80" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea>  
				 <button id="cure-submit">提交</button>
				</div>
				</a>
		    </div> 
			    
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
	
 </div>

</body>
</html>