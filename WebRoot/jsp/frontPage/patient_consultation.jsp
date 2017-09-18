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
<link rel="stylesheet" type="text/css" href="${basepath }/css/xcConfirm.css"/>
<script src="${basepath }/js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
<script src="${basepath }/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script> 
<script src="${basepath }/js/jquery-1.11.1.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/bootstrap.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/index.js" type="text/JavaScript"></script> 
<script type="text/javascript" src="${basepath }/js/jquery.json.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="${basepath }/js/patientConsultationCtr.js"></script>
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

$(function(){
    $("#consultationTab li").click(function(){	  
		$("#consultationTab li").removeClass("hz_tabon");
	    $("#consultationTab li").addClass("hz_taboff");
        $(this).removeClass("hz_taboff");
        $(this).addClass("hz_tabon");  
        var liIndex = $(this).index();
	   
		if(liIndex == 2)
		{
		  $(".hzInfo1").css("display","none");
		  $(".hzInfo2").css("display","none");
		  $(".hzInfo3").css("display","block");
		  
		  
		}		
        if(liIndex == 1)
		{		  
		 $(".hzInfo1").css("display","none");
		  $(".hzInfo3").css("display","none");
		  $(".hzInfo2").css("display","block");	  
		}	
        if(liIndex == 0)
		{
		  
		  $(".hzInfo3").css("display","none");
		  $(".hzInfo2").css("display","none");
		  $(".hzInfo1").css("display","block");	  
		}			
    });
		
});

$(function(){
    $(".agreeButtonOn").click(function(){	 
	
        $(this).removeClass("agreeButtonOn");	
	    
	});
});

//会诊申请审核
$(function(){
			
	$(document).on('click','.sgBtn',function()
    {	 
	    var con_id = $(this).parent().parent().find("#currentConId").text();
	    var checkType = "patient"; 
	    var param = checkType+"/"+con_id;
		var txt=  "是否同意此次会诊申请？";
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.confirm,param);

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
		 
		  <div class="textTitle"><span>病人中心</span></div>
		  <p class="thisLocation">当前位置>我的会诊</p>
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
		    
		    <div class="search">
			  <form>
			  <input type="text" placeholder="搜索从这里开始...">
			  <button type="submit" value="搜索">搜索</button>
			  </form>
            </div>
		    <div class="msgTab">
			<!-- 基本信息-->
			<p class="basicInfoTitle"><b>我的会诊</b></p>
			</div>
			
			<div class="basicInfo_received">
			    <div class="msgPanel">
				  <div class="basicInfo">
				  <!--我参与的会诊 -->
					<table border="0" class="hzInfo1">
						<tr>
							<th>会诊ID</th>
							<th>主治医生</th>
							<th>会诊时间</th>
							<th>会诊状态</th>
							<th>是否付款</th>
							<th>操作</th>
													
						</tr>	
						<tbody id="MedConInfoBody">
						</tbody>			 
						
					</table>
				   
				  
				</div>
		
				<div class="updateInfo" id="updateInfo">
				<form action="" method="post">
				    <p>
					   <label for="username">姓名&nbsp;</label>
                       <input type="text" name="username" />
					</p>
					<p>
					   <label for="sex">性别&nbsp;</label>
                        <select name="sex">
					     <option value="male">男</option>
					     <option value="female">女</option>					
					    </select>
                    </p>
					<p>   
					   <label for="birthday">生日&nbsp;</label>
                       <input type="text" name="birthday" />
					</p>
					<p>
					   <label for="email">邮箱&nbsp;</label>
                       <input type="text" name="email" />
					</p>
					<p>
					   <label for="tel">电话&nbsp;</label>
                       <input type="text" name="tel" />
					</p>
					<p>
					   <label for="hospital">所属医院&nbsp;</label>
                       <select name="hospital">
					     <option value="xina">重庆西南医院</option>
					     <option value="female">重庆妇科医院</option>					
					    </select>
					</P>   
					<p>   
					   <label for="keshi">所属科室&nbsp;</label>
                      <select name="keshi">
					     <option value="male">妇产科</option>
					     <option value="female">外科</option>					
					    </select>
					</p>   
                    <input type="submit" value="提交" id="infoSubmit" class="infoSubmit"/>
                    </form>
					</div>
							
			</div>	
		</div>
				
		<!-- 分页-->
				   <div class="paging">
				   		<ul class="pagination pagination-sm MedConPagenation" style="margin:0;"></ul>
				   </div>
		<!--分页结束 -->

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
<script type="text/x-jquery-tmpl" id="MedConInfoTemplate">
	<tr>
	<td id="currentConId"><a href="patient_consultationTable.jsp" id="theconsultationDetailId">\${currentConId}</a></td>
	<td id="currentPatDoctor">\${currentPatDoctor}</td>
	<td id="currentConTime">\${currentConTime}</td>
	<td id="currentConStatus1">\${currentConStatus1}</td>
	<td id="currentConStatus2">\${currentConStatus2}</td>
	<td><div class=\${className} id=\${idName} style="display:block">\${option}</div></td>    
	</tr>
</script>		
</body>
</html>