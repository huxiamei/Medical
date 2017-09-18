<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<c:set var="basepath" value="${pageContext.request.contextPath}"></c:set>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible"content="IE=9; IE=8; IE=7; IE=EDGE"> 
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script type="text/javascript" src="<%=basePath%>/js/jquery-2.1.4.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.tmpl.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.twbsPagination.js"></script>
<link href="${basepath }/css/index.css" rel="stylesheet" type="text/css"> 
<link rel="stylesheet" href="${basepath }/css/bootstrap.css" />
<link rel="stylesheet" href="${basepath }/css/multiple-select.css" />
<link href="${basepath }/css/applyTable.css" rel="stylesheet" type="text/css"> 
<link href="${basepath }/css/doctor_myMassage.css" rel="stylesheet" type="text/css"> 
<link href="${basepath }/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${basepath }/css/common.css" rel="stylesheet" type="text/css">
<link href="<%=basePath%>/css/manage-show.css" rel="stylesheet" />
<link href="<%=basePath%>/css/lyz.calendar.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${basepath }/js/applyConsultationCtr.js"></script>
<script language="JavaScript" src="${basepath }/js/myAjax.js"></script>
<script src="${basepath }/js/multiple-select.js"></script>
<script src="${basepath }/js/lyz.calendar.min.js" type="text/javascript"></script>
<title>Doctor</title>
<script type="text/javascript">
/*选择医生函数*/
$(document).ready(function(){
	$(document).on('click','.chooseDoctors',function(){
		 $(".showDoc").css("display","block");
		 $(".chooseDoctors").val("");
	});
	
});

$(function(){
    $("#msg li").click(function(){	  
		$("#msg li").removeClass("msgOn");
	    $("#msg li").addClass("msgOff");
        $(this).removeClass("msgOff");
        $(this).addClass("msgOn");  
        var liIndex = $(this).index();
	   
		if(liIndex == 2)
		{
		  
		  $(".basicInfo_received").css("display","none");
		  $(".basicInfo_sent").css("display","block");
		  
		}		
        if(liIndex == 0)
		{
		  $(".basicInfo_sent").css("display","none");
		  $(".basicInfo_received").css("display","block");
		  
		  
		}			
    });
		
});

function dropDownLi(){
if($(".dropDownLi").css("display") == "none")
{
 $(".dropDownLi").css("display","block");
}
else{
$(".dropDownLi").css("display","none");
}


}

//增加会诊医生
$(function(){
    $('#addDoc').click(function(){
		
			$('#tr1').after(
				'<tr>'+
					'<th>所属医院</th>'+
					'<td>'+
						'<select name="hospital">'+
							'<option value="1">重师附属医院</option>'+
							'<option value="2">重医附属医院</option>'+
							'<option value="female">华西妇科医院</option>'+		
						'</select>'+
				    '</td>'+			
				    '<th>所属科室</th>'+
					'<td>'+
						'<select name="keshi">'+
							'<option value="1">妇产科</option>'+
							'<option value="2">儿童科</option>'+
							'<option value="female">牙科</option>'+		
						'</select>'+
				    '</td>'+
				    '<th>会诊医生</th>'+
					'<td>'+
					    '<input type="text" name="username" id="chooseDoctors" class="chooseDoctors" value="可多选"/>'+					   						
				    '</td>'+
				    				    
				'</tr>'
			);
			//给每个新加的会诊申请绑定点击事件
			/*
			$(".popupAdd .addmore:eq(0) .deleteConsultion").click(function(e) {
                $(this).parent().parent().remove();
            });*/
		
	});
});
 
 
 
 
  $(function () {

        $("#txtBeginDate").calendar({

            controlId: "divDate",                                 

            speed: 200,                                           

            complement: true,                                    

            readonly: true,                                      

            upperLimit: NaN,                               

            lowerLimit: NaN,                  

            callback: function () {                              

              //  alert("您选择的日期是：" + $("#txtBeginDate").val());

            }

        });

        $("#txtEndDate").calendar();

    });


//病例填写


function submitText(){
     var myVal= document.getElementById("writeWords").value;
     var bb = document.getElementById("jzbs").value = myVal;
      //alert(bb);
      $("#light").css("display","none");
	  $("#fade").css("display","block");

}
function submitText2(){
     var myVal= document.getElementById("writeWords2").value;
     var bb = document.getElementById("gwbs").value = myVal;
      //alert(bb);
      $("#light2").css("display","none");
	  $("#fade").css("display","block");

}
function submitText3(){
     var myVal= document.getElementById("writeWords3").value;
    var bb = document.getElementById("bqms").value = myVal;
    //  alert(bb);
      $("#light3").css("display","none");
	  $("#fade").css("display","block");

}

</script>

</head>
<body>
  <div class="containner">
    <!--head -->
    <div class="head">
	  <div class="top">
	    <p>远程医疗会诊官网</p>
		<span class="top_login"><a href="${basepath }/jsp/frontPage/login.jsp">[ 退出 ]</a></span>
	  </div>
	  <!-- 导航-->
	  
	    <div class="nav">
			<div class="logo"><a href="${basepath }/jsp/frontPage/doctor_info.jsp"><img src="${basepath }/images/logo.png"/></a></div>
			<div class="userName"><a href="${basepath }/jsp/frontPage/doctor_info.jsp"><p>${sessionScope.CurrentUser.user_name }</p></a></div>
	    </div>
	  <!-- 导航-->
	</div>
	
	</div>
	<!--head over-->
	
	<!--main conten-->
      <div class="mainContent">
	    <!--mainTop -->
		<div class="doctor_mainTop">
		 
		  <div class="textTitle"><span>医生中心</span></div>
		  <p class="thisLocation">当前位置>申请会诊</p>
		</div>
		
		
		<div class="mainMid">
		<!--leftSide -->
		   <!--<div class="leftsideNav">
		   
		        <ul  >
					<li>
					    <dl>
					      <dt><a href="doctor_info.jsp">个人信息</a></dt>
					    </dl>
					</li>
				    <li>
					    <dl>
					     <dt><a href="doctor_brManage.jsp">病人管理</a></dt>
					    </dl>
					</li>
					<li>
					    <dl>
					    <dt><a href="doctor_myMassage.jsp">我的消息</a></dt>
						</dl>
				    </li>
					<li>
					    <dl>
					    <dt><a href="doctor_applyConsultation.html">申请会诊</a></dt>
						</dl>
				    </li>
					<li>
					    <dl>
						 <dt><a href="doctor_consultation.jsp">我的会诊</a></dt>
						</dl>
					</li>
				</ul>
		   </div>-->
		<!--leftSide over-->
		<div class="clear"></div>
		<div class="rightContent">
		<!--
		    <div class="search">
			  <form>
			  <input type="text" placeholder="搜索从这里开始...">
			  <button type="submit" value="搜索">搜索</button>
			  </form>
            </div>
			-->
		    <div class="msgTab">
			<!-- 基本信息-->
			<p class="basicInfoTitle"><b>申请会诊</b></p>
			</div>
			
			<div class="basicInfo_received">
			    <div class="msgPanel">
				  <div class="basicInfo">
				  <p class="tabTitle"><strong>会诊申请表</strong></p>
				  <hr/>
				<form:form action="" method="post" modelAttribute="medicalConsultation">
				<p class="title_basicInfo">—— 基本信息 ——</p>
			    <table border="0" class="applyTable">
					<tr>
						<th>姓名</th>	 
						<td><input readonly="readonly" type="text" name="username" value="${medicalConsultation.medicalCase.patient.user.user_name }" /></td>
					    <th>性别</th>  
                        <td><input readonly="readonly" type="text" name="gender" value="<c:if test="${medicalConsultation.medicalCase.patient.user.gender == 'M' }">男</c:if> <c:if test="${medicalConsultation.medicalCase.patient.user.gender =='F' }">女</c:if>" /></td>	
						<th>血型</th>
						<td><input readonly="readonly" type="text" name="blood" value="<c:if test="${medicalConsultation.medicalCase.patient.blood.code == 0 }">未知</c:if> <c:if test="${medicalConsultation.medicalCase.patient.blood.code == 1 }">A型</c:if><c:if test="${medicalConsultation.medicalCase.patient.blood.code == 2 }">B型</c:if><c:if test="${medicalConsultation.medicalCase.patient.blood.code == 3 }">O型</c:if><c:if test="${medicalConsultation.medicalCase.patient.blood.code == 4 }">AB型</c:if><c:if test="${medicalConsultation.medicalCase.patient.blood.code == 5 }">HR型</c:if>" /></td>
					</tr>				 
				    
					<tr>
					   <th>电话</th>
					   <td><input readonly="readonly" type="text" name="tel" value="${medicalConsultation.medicalCase.patient.user.tel }"  /></td>
					   <th>身份证号</th>
					   <td><input readonly="readonly" type="text" name="tel" value="${medicalConsultation.medicalCase.patient.user.idCard }"  /></td>
					   <th>邮箱</th>
					   <td><input readonly="readonly" type="text" name="tel" value="${medicalConsultation.medicalCase.patient.user.email }"  /></td>
				    </tr>
				    
					
				</table>
				<p class="title_basicInfo">—— 病例信息 ——</p>
				 <table border="0" class="applyTable">
				    
					<tr>
						<th>家族病史</th>	 
						<td><input type="text" name="jzbs" id="jzbs" onclick = "document.getElementById('light').style.display='block';document.getElementById('fade').style.display='block'"/></td>
					    <th>过往病史</th>
					    <td><input type="text" name="gwbs" id="gwbs" onclick = "document.getElementById('light2').style.display='block';document.getElementById('fade').style.display='block'" /></td>
					    <th>病情描述</th>
					    <td><input type="text" name="bqms" id="bqms" onclick = "document.getElementById('light3').style.display='block';document.getElementById('fade').style.display='block'"/></td>
					</tr>				 
				    
				<div id="light" class="white_content">
						
					<div class="treatment_bg">
					 <span class="closeIcon" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'">×</span>
					 <textarea name="content" id="writeWords" rows="10" cols="80" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea>  
					 <div class="submitTxt" onclick = "submitText()"><span>提交</span></div>
					</div>	
		         </div> 
		         
		         <div id="light2" class="white_content">
						
					<div class="treatment_bg">
					 <span class="closeIcon" onclick = "document.getElementById('light2').style.display='none';document.getElementById('fade').style.display='none'">×</span>
					 <textarea name="content" id="writeWords2" rows="10" cols="80" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea>  
					 <div class="submitTxt" onclick = "submitText2()"><span>提交</span></div>
					</div>	
		         </div> 
		         <div id="light3" class="white_content">
						
					<div class="treatment_bg">
					 <span class="closeIcon" onclick = "document.getElementById('light3').style.display='none';document.getElementById('fade').style.display='none'">×</span>
					 <textarea name="content" id="writeWords3" rows="10" cols="80" onpropertychange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5"></textarea>  
					 <div class="submitTxt" onclick = "submitText3()"><span>提交</span></div>
					</div>	
		         </div> 
            <div id="fade" class="black_overlay"></div> 
					
					<tr class="tr1" id="tr1">
					   
					   <th>所属医院</th>
					   <td>
					     <select id="selectHospital"  name="hospital">
					     <c:forEach items="${hospitalList }" var="hospitalList">
					     <option value="${hospitalList.id }">${hospitalList.hospital_name }</option>
					     </c:forEach>
					 <!--  <option value="male">重庆西南医院</option> 
					     <option value="female">华西妇科医院</option>
                         <option value="female">华西妇科医院</option>	  -->   		
					     </select>
						</td>
					   <th>所属科室</th>
					   <td>
					    <select id="selectDepartment"  name="department">
					 <!--   <option value="male">妇产科</option>
					     <option value="female">牙科</option>	
                         <option value="female">骨科</option>		 -->  					 
					    </select>
					    </td>	
                       <th>会诊医生</th>
					    <td>
					    <input type="text" name="username" id="chooseDoctors" class="chooseDoctors" value="可多选"/>
					    <input id="hideDoctorList" type="hidden" value=""/>
					    </td>
						
				    </tr>
					<tr>
						<td colspan="6">
							<div class="showDoc" id="showDoc">
							 
							
							</div>
						</td>
				    </tr>
					<tr>
				     	
						<th>会诊时间</th>
						<td>					
						 <input id="txtBeginDate" value="会诊时间"/>
						</td>
					   <th>附件</th>
					    <input id="hideFilePath" type="hidden" value=""/>
					   <td>
					   <form id= "uploadForm" action= "http://localhost:8080/cfJAX_RS/rest/file/upload" method= "post" enctype ="multipart/form-data">  
					  
					   <input type="file" name="fileField" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
					   <input type="button" class="submitFile" value="确定" > 
					   </form>
					   </td>

				    </tr>
				</table>
				 <div class="addDoc" id="addDoc"></div>
				 <div class="chooseTab" id="chooseTab" >
				 <span style="display:none" id="patientidba">${medicalConsultation.medicalCase.patient.user.id }</span>
				 <span style="display:none" id="doctoridba">${medicalConsultation.doctor.user.id }</span>
				 <!--  <input class="treatment" type="button" id="submitConsu"/>提交申请-->
			   		  <span class="treatment"  id= "submitConsu"onclick="ApplyConsultation(${medicalConsultation.medicalCase.patient.user.id },${medicalConsultation.doctor.user.id })">提交申请</span>
				 </div>
				</form:form>
				
				</div>
							
			</div>
			
		</div>
		
		</div>	
	    
	    
	  </div>
	
	
	  </div>

 

<!--  -->
<script type="text/javascript">
 
       $("#txtBeginDate").calendar({

            controlId: "divDate",                                 

            speed: 200,                                           

            complement: true,                                    

            readonly: true,                                      

            upperLimit: NaN,                               

            lowerLimit: NaN,                  

            callback: function () {                              

              //  alert("您选择的日期是：" + $("#txtBeginDate").val());

            }

        });

        $("#txtEndDate").calendar();
</script>

<script>
    $(function() {
        $('#ms').change(function() {
            console.log($(this).val());
        }).multipleSelect({
            width: '100%'
        });
    });
    
    
    
    
</script>
</body>
</html>