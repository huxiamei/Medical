<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"    
        pageEncoding="UTF-8"%> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>MEDICAL MESSAGE</title>
<link href="${basepath }/css/manage-common.css" rel="stylesheet" />
<link href="${basepath }/css/manage-show.css" rel="stylesheet" />

<script type="text/javascript" src="${basepath }/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery-ui.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.min.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/consultation.js"></script>
<script type="text/javascript" src="${basepath }/js/myAjax.js"></script>
<script type="text/javascript"
	src="${basepath }/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="${basepath }/js/manage.js"></script>
<script language="JavaScript" src="${basepath }/js/hospitalCtr.js">
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
                <ul >
                    <li><a href="${basepath }/Doctor/index">医生管理</a></li>
                    <li><a href="${basepath }/jsp/manage/patient.jsp">病人管理</a></li>
                </ul>
            
                <h4><a>✚ 医院管理</a></h4>
                <ul>
                    <li><a href="${basepath }/Hospital/index">医院管理</a></li>
                    <li><a href="${basepath }/Department/index">科室管理</a></li>
                </ul>
           
                <h4><a>✚ 会诊管理</a></h4>
                <ul class="block">
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
				<div class="title-left">会诊管理</div>
			</div>

			<!-- 下划 线-->
			<hr class="clear" />

			<div class="search">
				<form method="get">
					病例编号：<input class="text" name="orderId" type="text"/>
						病人：<input class="text" name="userName" type="text"/>
							<input class="btn" name="submit" value="查询" type="submit"/>
				</form>
			</div>
			<!-- 内容显示部分 -->
			<div class="main">
				<!-- 所有信息列表 -->
				<div class="manage manageNoSearch">
					<table class="list">
						<tr>
							<th>ID</th>
							<th>姓名</th>
							<th>主治医生</th>
						    <th>会诊医生</th> 
							<th>状态</th>
							<th>操作</th>
						</tr>
						
					</table>
				</div>
				<!-- 分页效果 -->
				<div class="pager">
					<ul class="pageIndex">
					<!--	<li><a href="#">上一页</a></li>
						<li class="current">1</li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">下一页</a></li>-->
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!------ 弹出框 --------->
	<div class="popupBg"></div>
	<div class="popup consultation" class="animated">
		<img class="popupIco" />
		<div class="box">
			<div class="popupTop">
				<div class="tab">
					<a class="tab1e active" href="javascript:;">会诊管理</a>
				</div>
				<div class="popupClose">
					<a href="javascript:;" class="popupCloseBtn">×</a>
				</div>
			</div>
			<div class="popupAdd">
				<form action="#" method="post">
					<div>
						<label>申请医生： </label> 
						<input class="text" type="text"id="doctor" name="doctor" required="required" readonly="readonly" />
					</div>
					<div>
						<label>会诊编号： </label> 
						<input class="text" type="text" id="consultion_id"name="consultion_id" required="required" readonly="readonly"/>
					</div>
					<div>
						<label>病例编号： </label> <input class="text" type="text" id="case_id"name="case_id" required="required" readonly="readonly" />
					</div>
					<div class="relative" id="addConsultion">
						<label>会诊申请： </label> 
					</div>
					<div>
						<label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
						<select name="status" class="status">
						
						</select>
					</div>
					<div class="bottom">
						<input class="btn" name="submit" value="提交" type="submit" id="btn-consultation"/>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>
