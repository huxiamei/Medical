<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@include file="../header.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8"    
        pageEncoding="utf-8"%> 
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
<script language="JavaScript" src="${basepath }/js/hospitalDepartmentCtr.js" ></script>
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
	<p>当前位置: 远程会诊 -- <a style="display:inline" href="${basepath }/Hospital/index">医院管理</a> -- 对应科室管理</p>
</div>

<div class="pub-box">
    
	<!---- 主内容 --->
    <div class="context">
    	<!-- 当前页面标题 -->
    	<div class="title">
               	<div class="title-left">医院科室管理</div>
            <div>
            	<a class="add show" href="#" id="HospitalDepartmentAdd">✚ 新增</a>
            </div>
        </div>        
        <!-- 下划 线-->
        <hr class="clear"/>
        
        <!-- 内容显示部分 -->
        <div class="main">	
           <!-- 所有信息列表 -->
      		<div class="manage">
                <table class="list">
                    <tr>
                        <th width="15%">ID</th>
                        <th width="35%">医院</th>
   						<th width="35%">科室</th>
                        <th width="15%">操作</th>
                    </tr>
                   <tbody id="AllHospitalDepartment">
                      
                    </tbody>
                </table>
			</div>
           <!-- 分页效果 --> 
           <div class="pager">
               <div class="panel-footer " align="center" id="MedicalHospitalDepartmentPagenation">
                    <nav>
                        <ul class="pagination pagination-sm HospitalDepartmentPagenation" style="margin:0;"></ul>
                    </nav>
              </div>
           </div>  
        </div>
    </div>
</div>
<!------ 弹出框 --------->
<div class="popupBg"></div>
<div class="popup role_function" class="animated" id="HospitalDepartmentLayer">
   <div class="box">
       <div class="popupTop">
           <div class="tab">
                <a class="active">医院科室管理</a>
           </div>
           <div class="popupClose">
                <a href="javascript:;" class="popupCloseBtn">×</a>
           </div>
       </div>
        <div class="popupAdd">
            <form action="#" method="post">
            		<div>
                        <label>医&nbsp;&nbsp;&nbsp;&nbsp;院： </label>
                      	<select name="hospital" id="hospital">
                        </select>
                    </div>
                	<div>
                        <label>科&nbsp;&nbsp;&nbsp;&nbsp;室： </label>
                      	<select name="department" id="department">
                           
                        </select>
                    </div>
                    <div class="bottom">
                        <input class="btn" name="submit" value="提交" type="submit" id="HospitalDepartmentSubmit"/>
                    </div> 
            </form>     
        </div>
    </div>
</div>

<script type="text/x-jquery-tmpl" id="HospitalDepartmentInfoTemplate">
        <tr>
		   <td id="hospitalDepartmentId">\${hd_id}</td>
		   <td>\${hos_name}</td>
		   <td>\${dep_name}</td>
		   <td><a class="show" id="hospitalDepartmentUpdate">修改</a> <a class="delete" id="hospitalDepartmentDelete">删除</a></td>  	
	    </tr>
</script>

</body>
</html>
