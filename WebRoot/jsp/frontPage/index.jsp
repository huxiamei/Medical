
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basepath" value="${pageContext.request.contextPath}"></c:set>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${basepath }/css/common.css" rel="stylesheet" type="text/css">
<link href="${basepath }/css/index.css" rel="stylesheet" type="text/css"> 
<link href="${basepath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${basepath }/css/reset.css" rel="stylesheet" type="text/css">
<link href="${basepath }/css/common.css" rel="stylesheet" type="text/css">
<script src="${basepath }/js/jquery-1.11.1.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/bootstrap.min.js" type="text/JavaScript"></script>
<script src="${basepath }/js/index.js" type="text/JavaScript"></script> 
<title>Home</title>

</head>
<body>
  <div class="containner">
    <!--head -->
    <div class="head">
	  <div class="top">
	    <p>远程医疗会诊官网</p>
		<span class="top_login"><a href="login.jsp">[ 登录 ]</a></span>
	  </div>
	  <div class="nav">
	    <div class="logo"><a href="index.html"><img src="${basepath }/images/logo.png"/><a/></div>
		
		<div class="sideNav">
		   <ul>
		     
			  <li><a>新闻</a></li>
			  <li><a>案例</a></li>
			  <li><a>关于我们</a></li>
		   </ul>
        </div>		
	  </div>
	</div>
	</div>
	<!--head over-->
	<!--main conten-->
	<div class="mainContent">
	   <!-- 主页轮播-->
	   <div class="showImg">
	     <div class="outbg">
		    <div class="boderBox">
			</div>
			<div class="boderBox1">
			    <p><a href="doctor_info.jsp">医生中心</a></p>
			  
			</div>
			<div class="boderBox2">
			    <p><a href="patient_info.jsp">病人中心</a></p>
			</div>
			
		 </div>
	     <div id="myCarousel" class="carousel slide bigBox">
		<a class=" left bg-control1 control"> <span class="glyphicon glyphicon-chevron-left"  onclick="carouselPrev()"></span></a>
      	<a class=" left bg-control2 control"> <span class="glyphicon glyphicon-chevron-right"  onclick="carouselNext()"></span></a>
        <ol class="carousel-indicators revolve">
           <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
           <li data-target="#myCarousel" data-slide-to="1"></li>
           <li data-target="#myCarousel" data-slide-to="2"></li>
   		</ol>
        <div class="carousel-inner">
            <div class="item active">
                <img src="${basepath }/images/ylbg3.png" alt="">
                <div class="carousel-caption">
                 
				   </a>       
                </div>
            </div>
            <div class="item">
                <img src="${basepath }/images/ylbg5.png" alt="">
                <div class="carousel-caption">
               
			
				</a>
                </div>
            </div>
            <div class="item">
            	<img src="${basepath }/images/ylbg6.png" alt="">
                <div class="carousel-caption">
               
				</a>     
                </div>
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