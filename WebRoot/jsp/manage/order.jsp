<%@include file="../header.jsp" %>
 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<script language="JavaScript" src="${basepath }/js/orderCtr.js"></script>
<script language="JavaScript" src="${basepath }/js/myAjax.js"></script>
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
	<p>当前位置: 远程会诊 > 订单管理</p>
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
                <ul class="block">
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
				<div class="title-left">订单管理</div>
			</div>

			<!-- 下划 线-->
			<hr class="clear" />

			<div class="search">
				<form id="searchOrderForm" action="" method="get">
					订单号：<input class="text" name="orderId" id="orderId" type="text"value="${condition.orderId}"/> 
					姓名：<input class="text"name="userName" id="userName" type="text"value="${condition.patientName }"/> 
					<input class="btn"name="submit" value="查询" type="submit"onclick="SearchOrder(${orderList.currentPage })"/>
				</form>
			</div>
			<!-- 内容显示部分 -->
			<div class="main">
				<!-- 所有信息列表 -->
				<div class="manage">
					<table class="list">
						<tr>
							<th>ID</th>
							<th>姓名</th>
							<th>价格</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${orderList.dataList }" var="order">
							<tr>
								<td class="first w1 c" id="orderId">${order.id}</td>
								<td class="w3 c">${order.patient_name}</td>
								<td class="w2 c">$ ${order.accout}</td>
								<td class="w3 c"><c:if test="${order.order_status == 0}">下单</c:if>
									<c:if test="${order.order_status == 1}">待预付款</c:if> <c:if
										test="${order.order_status == 2}">待补款</c:if> <c:if
										test="${order.order_status == 3}">交易成功</c:if></td>
								<td class="w4 c"><a class="show"
									onclick="ModifyOrder(${order.id},'${order.patient_name}',${order.accout},${order.order_status}, ${orderList.currentPage });">修改</a>
									<a class="delete" id="deleteOrder"
									onclick="DeleteOrder(${order.id}, ${orderList.currentPage });">删除</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- 分页效果 -->
				<div class="pager">
					<ul>
						<c:if test="${orderList.currentPage > 1 }">
							<li><a href="#" onclick="prePage(${orderList.currentPage })">上一页</a></li>
						</c:if>
						<c:forEach var="item" varStatus="status" begin="1"
							end="${orderList.totalPage}">
							<c:if test="${status.index<=10}">
								<li
									<c:if test="${status.index == orderList.currentPage}"> class="current"</c:if>>
									<a href="${basepath }/order/showOrderList/${status.index}">${status.index}</a>
								</li>
							</c:if>
							<c:if test="${status.index==10 && orderList.totalPage>20}">
								<li>...</li>
							</c:if>
							<c:if
								test="${status.index>10 && status.index>orderList.totalPage-10}">
								<li
									<c:if test="${status.index == orderList.currentPage}"> class="current"</c:if>>
									<a href="${basepath }/order/showOrderList/${status.index}">${status.index}</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${orderList.currentPage < orderList.totalPage}">
							<li><a href="#"
								onclick="nextPage(${orderList.currentPage })">下一页</a></li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>
<!------ 弹出框 --------->
<div class="popupBg"></div>
<div class="popup order" class="animated">
   <div class="box">
       <div class="popupTop">
           <div class="tab">
                <a class="active">会诊订单</a>
           </div>
           <div class="popupClose">
                <a href="javascript:;" class="popupCloseBtn">×</a>
           </div>
       </div>
        <div class="popupAdd">
       <form id="totalSubmitForm" action="" method="get">       
        	<div>
          		<label>病&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人： </label>
				<span id="modifyPatientName">我有病</span>
        	</div>
        	<div>
            	<label>会诊编号： </label>				
           		<span id="modifyOrderId">huizhenbianhao_001_sunxq</span>
          	</div>
			<div>
                 <label>会诊医院： </label>
            </div>
			
            <div class="clear" id="totalAccountDiv">
          		<label>总计价格： </label>
           	 	<input id="total" class="text" type="number" name="total" required="required" readonly="readonly" value="600"/>
        	</div>  
            <div>
                <label>状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
                <select  id="modifyStatus" name="status">
                   <option value="0">下单</option>
                   <option value="1">待预付款</option>
                   <option value="2">待补款</option>
                   <option value="3">交易成功</option>
                </select>
            </div>
            <div class="bottom">
           		<input id="hidePage" type="hidden" value=""/>
           		<input id="totalSubmit" class="btn" name="submit" onclick="TotalSubmit()" value="提交" type="submit"/>
            </div> 
        </form>      
        </div>
	</div>
</div>

 
<div class="popup-detailed">
<div class="popup order-detailed" class="animated">
   <div class="box">
       <div class="popupTop">
           <div class="popupClose">
                <a href="javascript:;" class="popupCloseBtn-detailed">×</a>
           </div>
       </div>
        <div class="popupAdd">
   <!--   <form id="detailSubForm" action="" method="get">    -->
        	
			<div>
				 <input id="hideHosId" type="hidden" value=""/>
				 <input id="hideOrderDetailId" type="hidden" value=""/>
				 <input id="hideOrderTotal" type="hidden" value=""/>
				 <div class="order-hosiptal">医院：<input id="detailHosName" name="" value="重师附属医院" type="text" class="middle-text" readonly/></div>
		         <div class="left"><span>金额：&nbsp;<span> <input id="detailAccount" class="smallText" type="number" name="name" required="required" value="200" /><span>&nbsp;元</span></div>
			</div>
		
            <div class="bottom">
           		<input id="detailAccountSubmit" class="btn" name="submit" onclick="ModifyAccount()" value="提交" type="submit"/>
            </div> 
   <!--  </form>  -->
		</div>
    </div>
</div>
</div>


 
 
</body>
</html>