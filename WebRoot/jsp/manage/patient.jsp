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
<script type="text/javascript" src="${basepath }/js/jquery.json.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="${basepath }/js/jquery.twbsPagination.js"></script>
<script type="text/javascript" src="${basepath }/js/manage.js"></script>
<script type="text/javascript" src="${basepath }/js/patientCtr.js"></script>
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
                <ul class="block">
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
        	<div class="title-left">病人管理</div>
            <div>
            	<a class="add show" id="PatientAdd">✚ 新增</a>
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
                        <th>ID</th>
                        <th>姓名</th>
                        <th>性别</th>
                        <th>Email</th>
                        <th>主治医生</th>
                        <th>操作</th>
                    </tr>
                   	<tbody id="AllPatient">
                   	</tbody>
                </table>
			</div>
           <!-- 分页效果 --> 
           <div class="pager">
               <div class="panel-footer " align="center" id="MedicalPatientPagenation">
                    <nav>
                        <ul class="pagination pagination-sm PatientPagenation" style="margin:0;"></ul>
                    </nav>
              </div>
           </div>     
        </div>
    </div>
</div>
<!------ 弹出框 --------->
<div class="popupBg"></div>
<div class="popup patient" class="animated" id="PatientLayer">
   <div class="box">
       <div class="popupTop">
           <div class="tab">
                <a class="tab1 active" href="javascript:;">病人管理</a>
           </div>
           <div class="popupClose">
                <a href="javascript:;" class="popupCloseBtn">×</a>
           </div>
       </div>
        <div class="popupAdd">
        <form action="#" method="post" id="patientDetail">  
            <div>
            	<input class="text" type="text" name="user.id" id="id" style="display:none"/>
                <label>病&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人： </label>
                <input class="text" type="text" name="user.user_name" id="user_name" required="required"/>
            </div> 
            <div>
                <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码： </label>
                <input class="text" type="text" name="user.user_password" id="user_password" required="required"/>
            </div> 
            <div>
                <label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别： </label>
                <input type="radio" name="user.gender" value="M" class="gender"/>男 
                <input type="radio" name="user.gender" value="F" class="gender"/>女
            </div> 
            <div>
                <label>身&nbsp;&nbsp;份&nbsp;&nbsp;证： </label>
                <input class="text" type="text" name="user.idCard" id="idCard" required="required"/>
            </div>
            <div>
                <label>手&nbsp;&nbsp;机&nbsp;&nbsp;号： </label>
                <input class="text" type="tel" name="user.tel" id="tel" required="required"/>
            </div>  
			<div>
                <label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱： </label>
                <input class="text" type="email" name="user.email" id="email" required="required"/>
            </div> 
            <div>
                <label>出生日期： </label>
                <input class="text" type="date" name="user.birthdate" id="birthdate" required="required"/>
            </div> 
            <div>
                <label>血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型： </label>
              	<select name="blood" id="blood">
                      	<option value="O">O型</option>
                        <option value="A">A型</option>
                        <option value="B">B型</option>
                        <option value="AB">AB型</option>
                        <option value="RH">RH型</option>
                </select>  
            </div> 
            <div>
                <label>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户： </label>
              	<input class="text" type="text" name="account" id="account" required="required"/>
            </div>
            <input class="text" type="text" name="doctor.user.id" id="doctor_id" style="display:none"/>
            <div class="bottom">
                <input class="btn" name="submit" value="提交" type="submit" id="PatientSubmits"/>
            </div>
        </form>
    </div>
</div>
</div>

<script type="text/x-jquery-tmpl" id="PatientInfoTemplate">
        <tr>
		   <td class="first w1 c" id="patientId">\${pat_id}</td>
		   <td class="w3 c">\${pat_name}</td>
		   <td class="w2 c">\${pat_gender}</td>
		   <td class="w4 c">\${pat_email}</td>
           <td class="w4 c">\${pat_doctor}</td>
		   <td class="w4 c"><a class="show" id="patientUpdate">修改</a> <a class="delete" id="patientDelete">删除</a></td>  	
	    </tr>
</script>
</body>
</html>
