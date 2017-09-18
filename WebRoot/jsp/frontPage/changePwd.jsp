<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basepath" value="${pageContext.request.contextPath}"></c:set>
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<title>Login</title> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
<script type="text/javascript" src="${basepath }/js/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="${basepath }/js/login.js"></script>
<script type="text/javascript" src="${basepath }/js/ChangePwd.js"></script>
<link href="${basepath }/css/login.css" rel="stylesheet" type="text/css" />

</head>
<body>

    <!--head -->
    <div class="head">
	  <div class="top">
	    <p>远程医疗会诊官网</p>
	    <span class="top_login"><a href="login.jsp">[ 退出 ]</a></span>
	  </div>
	  <div class="nav">
	    <div class="logo"><a href="${basepath }/jsp/frontPage/doctor_info.jsp"><img src="${basepath }/images/logo.png"/></a></div>
		<div class="userName"><a href="${basepath }/jsp/frontPage/doctor_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p></a></div>		
	  </div>
	</div>

	<!--head over-->
	
<div class="login" style="margin-top:170px;">
    
   <!--  -->
    <div class="header">
        <div class="switch" id="switch">
			<a class="changePwd_btn" id="changePwd_btn" href="javascript:void(0);" tabindex="8">修改密码</a>		
        </div>
    </div>    

  <!-- 修改密码 -->
    <div class="qlogin" id="qlogin" >
   
    <div class="web_login">
    <form name="form2" id="changePwd" accept-charset="utf-8"  action="" method="post">
	    
	   <input type="hidden" name="id" value="${sessionScope.CurrentUser.id}"/>
        <ul class="reg_form" id="reg-ul">
        		<div id="userCue" class="cue">修改密码时请注意格式</div>
                <li>
                	
                    <label for="user"  class="input-tips2">旧密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="oldPassword" name="oldPassword" maxlength="16" class="inputstyle2"/>
                    </div>
                    
                </li>
                
                <li>
                <label for="passwd" class="input-tips2">新密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="newPassword"  name="newPassword" maxlength="16" class="inputstyle2"/>
                    </div>
                    
                </li>
                <li>
                <label for="passwd2" class="input-tips2">确认密码：</label>
                    <div class="inputOuter2">
                        <input type="password" id="newPasswordConfim" name="newPasswordConfim" maxlength="16" class="inputstyle2" />
                    </div>
                    
                </li>
                          
                <li>
                    <div class="inputArea">
                        <input type="button" id="UpdatePwd"  style="margin-top:10px;margin-left:85px;width:200px;" class="button_blue" value="确认修改"/> 
                    </div>
                    
                </li>
                <div class="cl"></div>
            </ul></form>
           
    
    </div>
   
    
    </div>
    <!--修改密码end-->
</div>
<!--footer-->
	<div class="footer">
		<div class="copyright">
			<p>版权所有 Copyright(C)2016-2017 六小只医疗有限公司</p>			
		</div>
	    
	</div>
	<!--footer-->
</body></html>