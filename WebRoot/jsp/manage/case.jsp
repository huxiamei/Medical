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
<script type="text/javascript" src="${basepath }/js/caseCtr.js"></script>
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
                <ul  class="block">
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
             	<div class="title-left">病例管理</div>
        </div>
        
        <!-- 下划 线-->
        <hr class="clear"/>
        
        <div class="search">
            <form method="get">
                病例编号：<input class="text" name="searchCaseId" type="text" id="searchCaseId"/> 
                病人：<input class="text" name="searchPatientId" type="text" id="searchPatientId"/> 
                <input class="btn"name="submit" value="查询" type="submit" id="searchCase"/>
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
                        <th>病情描述</th>
                        <th>主治医生</th>
                        <th>操作</th>
                    </tr>
                    <tbody id="AllCase">
                   	</tbody>
                </table>
			</div>
           <!-- 分页效果 --> 
           <div class="pager">
               <div class="panel-footer " align="center" id="MedicalCasePagenation">
                    <nav>
                        <ul class="pagination pagination-sm CasePagenation" style="margin:0;"></ul>
                    </nav>
              </div>
           </div>     
        </div>
    </div>
</div>
<!------ 弹出框 --------->
<div class="popupBg"></div>
<div class="popup case" class="animated" id="MedicalCaseLayer">
   <div class="box">
       <div class="popupTop">
           <div class="tab">
                <a class="tab1 active" href="javascript:;">病人</a>
                <a class="tab2" href="javascript:;">病情</a>
           </div>
           <div class="popupClose">
                <a href="javascript:;" class="popupCloseBtn">×</a>
           </div>
       </div>
        <div class="popupAdd">
            <form action="#" method="post" id="caseDetail">  
                <fieldset id="tab1">
                	<div>
                		<input class="text" type="text" name="id" id="id" style="display:none"/>
                		<input class="text" type="text" name="patient.user.id" id="patient_id" style="display:none"/>
                        <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名： </label>
                        <input class="text" type="text" name="patient.user.user_name" id="user_name" required="required"/>
                    </div>
                    <div>
                        <label>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别： </label>
                    	男<input class="gender" type="radio" name="patient.user.gender" value="M" />
                        女<input class="gender" type="radio" name="patient.user.gender" value="F" />
                    </div>
                    <div>
                        <label>身&nbsp;&nbsp;份&nbsp;&nbsp;证： </label>
                        <input class="text" type="text" name="patient.user.idCard" id="idCard" required="required"/>
                    </div>
                    <div>
                        <label>手&nbsp;&nbsp;机&nbsp;&nbsp;号： </label>
                    	<input class="text" type="tel" name="patient.user.tel" id="tel" required="required"/>
                    </div>
                    <div>
                        <label>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：</label>
                    	<input class="text" type="email" name="patient.user.email" id="email" required="required"/>
                    </div>
                    <div>
                        <label>出生日期： </label>
                        <input class="text" type="date" name="patient.user.birthdate"  id="birthdate" required="required"/>
                    </div>
                    <div>
                        <label>血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</label>
                    	<select name="patient.boold" id="blood">
                                <option value="O">O型</option>
                                <option value="A">A型</option>
                                <option value="B">B型</option>
                                <option value="AB">AB型</option>
                                <option value="RH">RH型</option>
                        </select>  
                    </div>
                    <div class="bottom">
                        <input class="tab2 btn" name="next" value="下一页" type="button"/>
                    </div>
                    </fieldset>
                    
                    <fieldset id="tab2">
                    	<div>
                            <label>就诊医院：</label>
                           	<select name="doctor.hospitalDepartment.hospital.id" id="hospital_id">
                                    <option value="2">重师附属医院</option>
                                    <option value="3">重医附属医院</option>
                            </select>
                            
                            <select name="doctor.hospitalDepartment.department.id" id="dep_id">
                                <option value="6">神经科</option>
                                <option value="10">肠胃科</option>
                            </select>
                            
                             <select name="doctor.user.id" id="doctor_id">
                                <option value="6">冯欣</option>
                                <option value="8">胡夏美</option>
                             </select>
                        </div>  
                        <div class="relative">
                            <label class="middle">家族病史：</label>
                            <textarea cols="26" rows="2" name="family_sick" id="family_sick"></textarea>
                        </div>
                        <div class="relative">
                            <label class="middle">前&nbsp;&nbsp;病&nbsp;&nbsp;史：</label>
                            <textarea cols="26" rows="2" name="before_sick" id="before_sick"></textarea>
                        </div>
                        <div class="relative">
                            <label class="middle">病情描述：</label>
                            <textarea cols="26" rows="3" name="description" id="description"></textarea>
                  	    </div>
                        <div>
                            <label>文件上传：</label>
                            <input class="text" type="file"  name="file_path" id="file_path"/>
                  	    </div>
                        <div class="bottom">
                        	<input class="tab1 btn" name="next" value="上一页" type="button"/>
                        	<input class="btn" name="submit" value="提交" type="submit" id="MedicalCaseSubmits"/>
                        </div> 
                </fieldset>
            </form>
        </div>
    </div>
</div>

<script type="text/x-jquery-tmpl" id="CaseInfoTemplate">
	 <tr>
		   <td class="first w1 c" id="caseId">\${case_id}</td>
		   <td class="w3 c">\${case_user_name}</td>
		   <td class="w2 c">\${case_des}</td>
		   <td class="w4 c">\${case_doctor}</td>
		   <td class="w4 c"><a class="show" id="caseUpdate">修改</a> <a class="delete" id="caseDelete">删除</a></td>  	
	    </tr>
</script>
</body>
</html>
