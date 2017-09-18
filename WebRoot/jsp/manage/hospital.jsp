<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../header.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page language="java" contentType="text/html; charset=utf-8"    
        pageEncoding="utf-8"%> 

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MEDICAL MESSAGE</title>
<link href="${basepath }/css/manage-common.css" rel="stylesheet" />
<link href="${basepath }/css/manage-show.css" rel="stylesheet" />

  <script type="text/javascript" src="${basepath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery-ui.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="${basepath }/js/manage.js"></script>
<script language="JavaScript" src="${basepath }/js/hospitalCtr.js" >
</script>
</head>
<body>
<!-- 显示logo的标题栏 -->
<div class="pub-logo relative">
        <div class="pub-logo-name">♌ MEDICAL+</div>
        
        <div class="pub-login">          
			<div class="userName">
			  <p><a class="left" href="${basepath }/jsp/manage/index.jsp">管理员：${sessionScope.CurrentUser.admin_name}</a>
			  	 <a class="left" href="${basepath }/jsp/frontPage/login.jsp">[ 退出 ]</a>
			  </p>
			</div>
				    
        </div>
       
</div>

<!--显示当前位置-->
<div class="pub-position relative">
	<p>当前位置: 远程会诊 > 用户管理</p>
</div>

<div class="pub-box">
	  <!-- 右侧导航 -->
    <div class="hiddenScroll">
         <nav class="pub-nav">
                 <h4><a>✚ 用户管理</a></h4>
                <ul>
                    <li><a href="${basepath }/Doctor/index">医生管理</a></li>
                    <li><a href="${basepath }/jsp/manage/patient.jsp">病人管理</a></li>
                </ul>
            
                <h4><a>✚ 医院管理</a></h4>
                <ul  class="block">
                    <li><a href="${basepath }/Hospital/index">医院管理</a></li>
                    <li><a href="${basepath }/Department/index">科室管理</a></li>
                </ul>
           
                <h4><a>✚ 会诊管理</a></h4>
                <ul>
                    <li><a href="${basepath }/jsp/manage/case.jsp">病例管理</a></li>
                    <li> <a href="${basepath }/jsp/manage/cure.jsp">治疗方案管理</a></li>
                    <li><a href="${basepath }/jsp/manage/consultation.jsp">会诊单管理</a></li>
                </ul>
        
                <h4><a>✚ 订单管理</a></h4>
                <ul>
                     <li><a href="${basepath }/order/showOrderList/1">订单管理</a></li>
                </ul>       
                           
          
                <h4><a>✚ 系统管理</a></h4>
                <ul>
                   <li><a href="${basepath }/background/role/roleList">权限管理</a></li>
                    <li><a href="${basepath }/background/function/functionList?currPage=1&pageSize=10">功能管理</a></li>
                    <li><a href="${basepath }/background/roleFunction/roleFunctionList?currPage=1&pageSize=10">权限功能管理</a></li>
                </ul>     
        </nav>
    </div>
   
	<!---- 主内容 --->
    <div class="context">
    	<!-- 当前页面标题 -->
    	<div class="title">
        	<div class="title-left">医院管理</div>'
        	<div ><a href="${basepath}/HospitalDepartment/index">对应科室管理</a></div>
            <div>
            	<a class="add show" id="hospitalAdd">✚ 新增</a>
            </div>
        </div>
        <!-- 下划 线-->
        <hr class="clear"/>
        <!-- 内容显示部分 -->
        <div class="main">	
           <!-- 所有信息列表 -->
      		<div class="manage manageNoSearch">
                <table class="list">
                    <tr>
                        <th width="10%">ID</th>
                        <th width="20%">名称</th>
                        <th width="50%">描述</th>
                        <th width="10%">等级</th>
                        <th width="10%">操作</th>
                    </tr>
                    <tbody id="AllHospital">
                      
                    </tbody>		   
                    
                </table>
			</div>
           <!-- 分页效果 --> 
           <div class="pager">
               <div class="panel-footer " align="center" id="MedicalHospitalPagenation">
                    <nav>
                        <ul class="pagination pagination-sm HospitalPagenation" style="margin:0;"></ul>
                    </nav>
              </div>
           </div>          
        </div>
    </div>
</div>
<!------ 弹出框 --------->
<div class="popupBg"></div>
<div class="popup hospital" class="animated"  id="HospitalLayer">
   <div class="box">
       <div class="popupTop">
           <div class="tab">
                <a class="tab1 active" href="javascript:;">医院管理</a>
           </div>
           <div class="popupClose">
                <a href="javascript:;" class="popupCloseBtn">×</a>
           </div>
       </div>
        <div class="popupAdd">
         <form:form method="POST" commandName="hospitalInfo" id="hospitalDetail">
       	    <div>
       	        <form:hidden path="id" id="id"/>
                <label>名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称： </label>
                <form:input path="hospital_name" id="hospital_name"/>
            
            </div> 
            <div>
                <label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户： </label>
                <form:input path="account" id="account"/> 
                     
            </div> 
            <div>
                <label>收费标准： </label>
                <form:input path="chargeStandard" id="chargeStandard" /> 
                 
            </div> 
            <div class="relative">
                <label class="middle">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述： </label>
                <form:textarea path="description"  cols="26" rows="3"  id="description"/>
               
            </div> 
            <div>
                <label>等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级： </label>
                <form:select path="hospital_level" name="hospital_level" id="hospital_level">
                   <form:option value="1">一甲</form:option>
                   <form:option value="2">二甲</form:option>
                   <form:option value="3">三甲</form:option>
                </form:select>
            </div>  
            <div class="bottom">
                <input class="btn" name="submit" value="提交" type="submit" id="HospitalSubmits"/>
            </div>
         </form:form>
    </div>
    </div>
</div> 


<script type="text/x-jquery-tmpl" id="HospitalInfoTemplate">
        <tr>
		   <td id="hospitalId">\${hos_id}</td>
		   <td>\${hos_name}</td>
		   <td>\${hos_description}</td>
		   <td>\${hos_level}</td>
		   <td><a class="show" id="hospitalUpdate">修改</a> <a class="delete" id="hospitalDelete">删除</a></td>  	
	    </tr>
</script>
</body>
</html>
