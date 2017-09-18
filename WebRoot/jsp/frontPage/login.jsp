<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basepath" value="${pageContext.request.contextPath}"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>Login</title> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<script type="text/javascript" src="${basepath }/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${basepath }/js/login.js"></script>
<script type="text/javascript" src="${basepath }/js/loginCtr.js"></script>
<link href="${basepath }/css/login.css" rel="stylesheet" type="text/css" />

</head>
<body>

 <!--head -->
    <div class="head">
	  <div class="top">
	    <p>远程医疗会诊官网</p>
	  </div>
	  <div class="nav">
	    <div class="logo"><a href="index.jsp"><img src="${basepath }/images/logo.png"/></a></div>
		<div class="userName"><p>未登录</p></div>		
	  </div>
	</div>

	<!--head over-->
<div class="login" style="margin-top:170px;">
    
    <div class="header">
        <div class="switch" id="switch">
        <a class="changePwd_btn" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
		
        </div>
    </div>    
  
    
    <div class="web_qr_login" id="web_qr_login" style="display: block; height: 250px;">    

            <!--登录-->
            <div class="web_login" id="web_login">             
               <div class="login-box">
    
            
			<div class="login_form">
				<form action="" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
				<input type="hidden" name="did" value="0"/>
                <input type="hidden" name="to" value="log"/>
                <div class="uinArea" id="uinArea">
                <label class="input-tips" for="u">帐号：</label>
                <div class="inputOuter" id="uArea">
                    
                    <input type="text" id="u" name="username" class="inputstyle"/>
                </div>
                </div>
                <div class="pwdArea" id="pwdArea">
               <label class="input-tips" for="p">密码：</label> 
               <div class="inputOuter" id="pArea">
                    
                    <input type="password" id="p" name="p" class="inputstyle"/>
                </div>
                </div>
                <div class="chooseRole" id="chooseRole">
				   <label class="input-tips" for="p">角色：</label> 
				   <div class="inputOuter" id="pArea">					
						<select name="role" class="inputstyle" id="role">
					     <option value="doctor">医生/病人</option>					    
						 <option value="admin">管理员</option>
					    </select>
					</div>
                </div>
               
                <div style="padding-left:50px;margin-top:20px;"><input type="button" value="登 录" style="width:150px;" class="button_blue" id="UserLogin"/></div>
              </form>
           </div>
           
            	</div>
               
            </div>
            <!--登录end-->
  </div>
    
</div>
<!--footer-->
	<div class="footer">
		<div class="copyright">
			<p>版权所有 Copyright(C)2016-2017 六小只医疗有限公司</p>			
		</div>
	    
	</div>
	<!--footer-->
</body></html>