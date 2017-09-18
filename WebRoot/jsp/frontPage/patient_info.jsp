<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
<script src="${basepath }/js/jquery-1.11.1.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/bootstrap.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/index.js" type="text/JavaScript"></script> 
<script src="${basepath }/js/patientInfoCtr.js" type="text/JavaScript"></script> 
<title>Patient</title>
<script type="text/javascript">



function updateButton(){
    
  $(".chooseTab").css("display","none"); 
  $(".updateInfo").css("display","block");
  $(".infoSubmit").css("display","block");
  $(".update").css("display","none");
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
			<div class="logo"><a href="${basepath }/jsp/frontPage/index.jsp"><img src="${basepath }/images/logo.png"/><a/></div>
			<div class="userName"><a href="${basepath }/jsp/frontPage/patient_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p></a></div>

	    </div>
	  <!-- 导航-->
	</div>
	
	</div>
	<!--head over-->
	
	<!--main conten-->
      <div class="mainContent">
	    <!--mainTop -->
		<div class="doctor_mainTop">
		
		  <div class="textTitle"><span>病人中心</span></div>
		  <p class="thisLocation">当前位置>个人信息</p>
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
			<p class="basicInfoTitle"><b>基本信息</b></p>
			</div>
			<div class="basicInfo_received">
			    <div class="msgPanel">
				<div class="basicInfo">
			    <table border="0">
					<tr>
						<th>ID</th>
						<th>姓名</th>
						<th>性别</th>
						<th>生日</th>
						<th>邮箱</th>
						<th>电话</th>
						<th>所属医院</th>
						<th>所属科室</th>	
                          						
					</tr>				 
				    <tr>
						<td id="currentPatId">${sessionScope.CurrentUser.id}</td>
						<td id="currentPatName"></td>
						<td id="currentPatGender"></td>
						<td id="currentPatBirthdate"></td>
						<td id="currentPatemail"></td>
						<td id="currentPattel"></td>
						<td id="currentPathospital"></td>
						<td id="currentPatdepartment"></td>
				    </tr>
				</table>
				<hr/>
				  
				</div>
		
				<div class="updateInfo" id="updateInfo">
				<form method="post" id="updatePatient">
					<input type="hidden" id="id" name="id"/>
				    <p>
					   <label for="username">姓名&nbsp;</label>
                       <input type="text" name="user_name" id="user_name"/>
					</p>
					<p>
					   <label for="sex">性别&nbsp;</label>
                        <select name="gender" id="gender" class="gender">
					     <option value="F">女</option>
					     <option value="M">男</option>					
					    </select>
                    </p>
					<p>   
					   <label for="birthday">生日&nbsp;</label>
                       <input type="text" name="birthdate" id="birthdate"/>
					</p>
					<p>
					   <label for="email">邮箱&nbsp;</label>
                       <input type="text" name="email" id="email"/>
					</p>
					<p>
					   <label for="tel">电话&nbsp;</label>
                       <input type="text" name="tel" id="tel"/>
					</p>
					<p>
					   <label for="hospital">所属医院&nbsp;</label>
					   <span id="hospital"></span>                  
					</p>   
					<p>   
					   <label for="keshi">所属科室&nbsp;</label>
					   <span id="department"></span>
					</p>   
                    <input type="button" value="提交" id="infoSubmit" class="infoSubmit"/>
                    </form>
					</div>
			
			</div>
			
			
			<!-- 编辑资料-->
			
			<div class="update" id="update" onclick="updateButton()">
			    <span>编辑资料</span>
			</div>
			<a href="changePwd.jsp">
			<div class="update" id="update">
			    <span>修改密码</span>
			</div>
			</a>
			
			
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