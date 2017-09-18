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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MEDICAL MESSAGE</title>
<link href="${basepath }/css/manage-common.css" rel="stylesheet" />
<link href="${basepath }/css/manage-show.css" rel="stylesheet" />


<script type="text/javascript" src="${basepath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery-ui.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.min.js"></script>

<script type="text/javascript" src="${basepath }/js/manage.js"></script>

<script type="text/javascript" src="${basepath }/js/role-function-list.js"></script>
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
                    <li><a href="${basepath }/order/showOrderList/1">订单管理</a></li>
                </ul>       
                           
          
                <h4><a>✚ 系统管理</a></h4>
                <ul  class="block">
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
               	<div class="title-left">权限功能管理</div>
            <div>
            	<a class="add show" href="#">✚ 新增</a>
            </div>
        </div>
        
        <!-- 下划 线-->
        <hr class="clear"/>
        
        <div class="search">
            <form action="<%=basePath%>background/roleFunction/roleFunctionList" method="get">
           		     角色名称：<input class="text" name="role_name" type="text" value="${role_name}" /> 
           		 <input name="currPage" type="hidden" value="${page.currentPage}" /> 
           		 <input name="pageSize" type="hidden" value="10" /> 
                 <input class="btn searchRoleFunction" name="submit" value="查询" type="submit" />
            </form>
        </div>
        
        <!-- 内容显示部分 -->
        <div class="main">	
           <!-- 所有信息列表 -->
      		<div class="manage">
                <table class="list">
                    <tr>
                        <th>编号</th>
                        <th>角色</th>
   						<th>功能名称</th>
   						<th>功能回调</th>
                        <th>操作</th>
                    </tr>
          			<c:forEach items="${page.dataList}" var="roleFunctoin" varStatus="inde">
                    <tr>
                        <td class="first w1 c">${inde.index + 1}</td>
                        <td class="w3 c role_name">${roleFunctoin.role_name}</td>
                        <td class="w3 c function_name">${roleFunctoin.function_name}</td>
                        <td class="w3 c">${roleFunctoin.call_back}</td>
                        <td class="w3 c">
                        	<a href="javascript:;" class="show update" updateId="${roleFunctoin.id}">修改</a>
	                    	<a href="javascript:;" class="delete" deleteId="${roleFunctoin.id}">删除</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
			</div>
            <!-- 分页效果 --> 
          	 <div class="pager">
                <ul>
                    <c:if test="${page.currentPage > 1 }">
						<li><a href="${basepath }/background/roleFunction/roleFunctionList?currPage=${page.currentPage-1}&pageSize=10&role_name=${role_name}">上一页</a></li>
					</c:if>
					 <c:if test="${page.currentPage <= 1 }">
						<li><a href="javascript:;">上一页</a></li>
					</c:if>
					<c:forEach var="item" varStatus="status" begin="1" end="${page.totalPage}">
						<c:if test="${status.index<=10}">
							<li <c:if test="${status.index == page.currentPage}"> class="current"</c:if>> <a href="${basepath }/background/roleFunction/roleFunctionList?currPage=${status.index}&pageSize=10&role_name=${role_name}">${status.index}</a> </li>
						</c:if>
						<c:if test="${status.index==10 && page.totalPage>20}">
							<li>...</li>
						</c:if>
						<c:if test="${status.index>10 && status.index>page.totalPage-10}">
							<li <c:if test="${status.index == page.currentPage}"> class="current"</c:if>> <a href="${basepath }/background/roleFunction/roleFunctionList?currPage=${status.index}&pageSize=10&role_name=${role_name}">${status.index}</a> </li>
						</c:if>
					</c:forEach>
					<c:if test="${page.currentPage < page.totalPage}">
						<li><a href="${basepath }/background/roleFunction/roleFunctionList?currPage=${page.currentPage+1}&pageSize=10&role_name=${role_name}">下一页</a></li>	
					</c:if>
					<c:if test="${page.currentPage >= page.totalPage}">
						<li><a href="javascript:;">下一页</a></li>	
					</c:if>
                </ul>
           </div>
        </div>
    </div>
</div>
<!------ 弹出框 --------->
<div class="popupBg"></div>
<div class="popup role_function" class="animated">
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
            <form action="${basepath }/background/roleFunction/roleFunctionAddUpdate" method="post" class="roleFunctionForm">
            		<div>
                        <label>角色名： </label>
                      	<select name="role_id">
                      		<c:forEach items="${roleList}" var="role">
                      			<option value="${role.id}">${role.role_name}</option>
                      		</c:forEach>
                        </select>
                    </div>
                	<div>
                        <label>功能名： </label>
                      	<select name="function_id">
                           <c:forEach items="${functionList.dataList}" var="function">
                      			<option value="${function.id}">${function.function_name}</option>
                      		</c:forEach>
                        </select>
                    </div>
                    <div class="bottom">
                    	<input type="hidden" name="id" />
                        <input class="btn submitUpdateRoleFuntion" name="submit" value="  提交" type="button" />
                    </div> 
            </form>
        </div>
    </div>
</div>


</body>
</html>
