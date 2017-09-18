<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basepath" value="${pageContext.request.contextPath}"></c:set>
<!--<jsp:useBean id="dateObject" class="java.util.Date" scope="page"></jsp:useBean>
<jsp:setProperty property="time" name="dateObject" value="${i.effectiveDate}"/>
<fmt:formatDate value="${dateObject}" pattern="yyyy-MM-dd" /> -->
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
<link rel="stylesheet" type="text/css" href="${basepath }/css/xcConfirm.css"/>
<script src="${basepath }/js/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
<script src="${basepath }/js/xcConfirm.js" type="text/javascript" charset="utf-8"></script> 
<script src="${basepath }/js/jquery-1.11.1.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/bootstrap.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/index.js" type="text/JavaScript"></script> 
<script src="${basepath }/js/doctor_consultation.js" type="text/JavaScript"></script> 
<script type="text/javascript" src="${basepath }/js/myAjax.js"></script>
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
    var num=$("#consultationTab li").size();
    if(num==2)
    {
     $("#consultationTab li").click(function(){	  
     
		$("#consultationTab li").removeClass("hz_tabon");
	    $("#consultationTab li").addClass("hz_taboff");
        $(this).removeClass("hz_taboff");
        $(this).addClass("hz_tabon");  
        var liIndex = $(this).index();
	   		
        if(liIndex == 1)
		{		  
		 $(".hzInfo1").css("display","none");
		  $(".hzInfo3").css("display","block");		
		}	
        if(liIndex == 0)
		{	  
		  $(".hzInfo3").css("display","none");	
		  $(".hzInfo1").css("display","block");	  
		}			
    });
    }
    else
    {
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
    }

   
		
});

$(function(){
$(document).on('click','.agreeButtonOn',function()
  {	 

        $(this).removeClass("agreeButtonOn");	
	    
	});
});

//会诊申请审核
$(function(){
			
	$(document).on('click','.sgBtn',function()
         {	 
	    var con_id = $(this).parent().parent().find("#ConId").text();
	    var checkType = "doctor"; 
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
			<div class="logo"><a href="${basepath }/jsp/frontPage/doctor_info.jsp"><img src="${basepath }/images/logo.png"/></a></div>
			<div class="userName"><a href="${basepath }/jsp/frontPage/doctor_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p></a></div>
	    </div>
	  <!-- 导航-->
	</div>
	
	</div>
	<!--head over-->
	
	<!--main conten-->
      <div class="mainContent">
	    <!--mainTop -->
		<div class="doctor_mainTop">
		  <div class="textTitle"><span>医生中心</span></div>
		  <p class="thisLocation">当前位置>我的会诊</p>
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
					<table border="0" class="hzInfo1" >
						<tr>
							<th>会诊ID</th>
							<th>主治医生</th>
							<th>病人</th>
							<th>会诊时间</th>
							<th>会诊状态</th>
							<th>是否付款</th>													
						</tr>				 
	
					</table>
				    <!--我审核的会诊-->
				    <c:if test="${sessionScope.CurrentUser.role_id == 1}">
				      <table border="0" class="hzInfo2" >
						<tr>
							<th>会诊ID</th>
							<th>主治医生</th>
							<th>病人</th>
							<th>会诊时间</th>
							<th>会诊状态</th>
							<th>是否付款</th>
							<th>操作</th>																		
						</tr>								 	
					</table>
				    </c:if>
				    				
					<!--我申请的会诊 -->
					<table border="0" class="hzInfo3">
						<tr>
							<th>会诊ID</th>
							<th>主治医生</th>
							<th>病人</th>
							<th>会诊时间</th>
							<th>会诊状态</th>
							<th>是否付款</th>										
						</tr>						
					</table>			  
				</div>								
			</div>
		</div>
				
		<!-- 分页-->
				   <!--  <div class="paging">
				   
				     <ul class="pagination"><li class="disabled"><span>«</span></li> <li class="active"><span>1</span></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li><a href="#">4</a></li><li><a href="#">5</a></li><li><a href="#">6</a></li> <li><a href="#" rel="next">»</a></li></ul>
				   </div>-->
		<!--分页结束 -->
		<!--会诊选项 -->
		<div class="consultationTab" id="consultationTab">
		   <ul>
		      <li class="hz_tabon" id="button_one">我参与的会诊</li>
		       <c:if test="${sessionScope.CurrentUser.role_id == 1}">
		          <li class="hz_taboff" id="button_two">我审核的会诊</li>
		       </c:if>
			 
			  <li class="hz_taboff" id="button_three">我申请的会诊</li>
		   </ul>
		</div>
		<!--会诊选项结束 -->
		
		
		
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