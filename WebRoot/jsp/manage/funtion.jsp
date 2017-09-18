<%@include file="../header.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="<%=basePath%>/js/jquery.twbsPagination.js"></script>

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
</head>
<body>

<!-- 显示logo的标题栏 -->
<div class="pub-logo relative">
        <div class="pub-logo-name">♌ MEDICAL+</div>
        <div class="pub-search">
            <form action="#" method="post">
                <input type="text" placeholder="  ✎  医院,医生,病人" required="required"/>
            </form>
        </div>
        <div class="pub-login">          
			<div class="userName">
			  <p><a href="${basepath }/jsp/manage/index.jsp">${sessionScope.CurrentUser.admin_name}</a></p>
			  
			</div>
				    
        </div>
        <a href="${basepath }/jsp/frontPage/login.jsp">[ 退出 ]</a>
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
                <ul>
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
                    <li><a href="${basepath }/jsp/manage/order.jsp">订单管理</a></li>
                </ul>       
                           
          
                <h4><a>✚ 系统管理</a></h4>
                <ul  class="block">
                    <li><a href="${basepath }/background/role/roleList">权限管理</a></li>
                    <li><a href="${basepath }/background/function/functionList?currPage=1&pageSize=5">功能管理</a></li>
                    <li><a href="${basepath }/background/roleFunction/roleFunctionList?currPage=1&pageSize=5">权限功能管理</a></li>
                </ul>     
        </nav>
    </div>
    
	<!---- 主内容 --->
    <div class="context">
    	<!-- 当前页面标题 -->
    	<div class="title">
              	<div class="title-left">功能管理</div>
            <div>
            	<a class="add show" href="#">✚ 新增</a>
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
                        <th>编号</th>
   						<th>功能名</th>
                        <th>功能描述</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${page.dataList}" var="fun" varStatus="inde">
                    <tr>
                        <td class="first w1 c">${inde.index + 1}</td>
                        <td class="w4 c function_name">${fun.function_name}</td>
                        <td class="w3 c description" title="${fun.description}">
                       		<c:choose>
                        		<c:when test="${fun.description.length() > 10}">
                        			${fun.description.substring(0,10)}...
                        		</c:when>
                        		<c:otherwise>
                        			${fun.description}
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                        <td class="w2 c">
	                        <a href="javascript:;" class="show update" updateId="${fun.id}">修改</a>
	                        <a href="javascript:;" class="delete" deleteId="${fun.id}">删除</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
			</div>
           <!-- 分页效果 --> 
           <div class="pager">
                <ul>
                    <li><a href="#">上一页</a></li>
                    <li class="current">1</li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">下一页</a></li>
                </ul>
           </div>
        </div>
    </div>
</div>
<!------ 弹出框 --------->
<div class="popupBg"></div>
<div class="popup roleType" class="animated">
   <div class="box">
       <div class="popupTop">
           <div class="tab">
                <a class="active">功能管理</a>
           </div>
           <div class="popupClose">
                <a href="javascript:;" class="popupCloseBtn">×</a>
           </div>
       </div>
        <div class="popupAdd">
            <form action="#" method="post" class="functionForm">  
                	<div>
                        <label>功能名称： </label>
                      	<input class="text" type="text" name="function_name" required="required" />
                    </div>
                    <div>
                        <label>功能描述： </label>
                        <textarea rows="4" cols="26" name="description"></textarea>                      
                    </div>
                    <div class="bottom">
                    	<input type="hidden" name="id"/>
                        <input class="btn submitUpdateFuntion" name="submit" value="提交" type="button">
                    </div> 
            </form>
        </div>
    </div>
</div>


<script type="text/javascript" src="<%=basePath%>/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/manage.js"></script>

<script type="text/javascript" src="<%=basePath%>/js/function-list.js"></script>

</body>
</html>
