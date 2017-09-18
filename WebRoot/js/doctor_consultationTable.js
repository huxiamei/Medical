$(document).ready(function () {
var resultDictList=[];
var consultationId=localStorage.getItem("consultationId");
var choose=localStorage.getItem("chooseStyle");
	//判定是否显示治疗方案
if(choose==1||choose==2){
	document.getElementById('chooseTab').style.display='none';
}
	//获取result状态的书籍字典
	 new MyAjax().onAjaxGet({
	        url: "http://localhost:8080/Medical/MedicalConsultation/getResultDict"
	    }, function (resultList) {
	    	resultDictList=resultList;
	    });
	 //获取会诊详情
	 cureDetail();
	 
	 $("#chooseTab").on("click",function()
    {
    	document.getElementById('light').style.display='block';
    });

	 
	 //修改治疗方案
	 $("#cure-submit").click(function(){
		var plan=$(".edit-cure").val();
		document.getElementById('light').style.display='none';
		document.getElementById('fade').style.display='none';
		var cure={};
		cure.consultation_id=consultation.id;
		cure.plan=plan;
		 new MyAjax().onAjaxPost({
			 url: "http://localhost:8080/Medical/MedicalCure/editCure",
		     data:cure
		    }, function (medicalConsultation) {
		    	cureDetail();
		 });
		 
	 });
	 
	 function cureDetail(){
		 
		 new MyAjax().onAjaxGet({
		        url: "http://localhost:8080/Medical/MedicalConsultation/viewDetail?id="+consultationId
		    }, function (medicalConsultation) {
		    	consultation=medicalConsultation;
		    	//会诊表状态大于5，填写治疗方案按钮消失
		    	if(medicalConsultation.con_status!=3)
	    		{   		
		    		document.getElementById('chooseTab').style.display='none';
	    		}
		    	var time=getLocalTime(medicalConsultation.time/1000);
		    	var gender=(medicalConsultation.medicalCase.patient.user.gender=='M'?'男':'女');
		    	var html='<table border="0" class="consultation-detail">'+
		    	         '<tr><th>会诊ID</th><td>'+medicalConsultation.id+'</td></tr>'+
		    	         '<tr><th>病人姓名</th><td>'+medicalConsultation.medicalCase.patient.user.user_name+'</td></tr>'+
		    	         '<tr><th>病人性别</th><td>'+gender+'</td></tr>'+
		    	         '<tr><th>出生日期</th><td>'+getLocalTime(medicalConsultation.medicalCase.patient.user.birthdate/1000)+'</td></tr>'+
		    	         '<tr><th>病情描述</th><td>'+medicalConsultation.medicalCase.description+'</td></tr>'+
		    	         '<tr><th>以往病史</th><td>'+medicalConsultation.medicalCase.before_sick+'</td></tr>'+
		    	         '<tr><th>家族病史</th><td>'+medicalConsultation.medicalCase.family_sick+'</td></tr>'+
		    	         '<tr><th>主治医生</th><td>'+medicalConsultation.doctor.user.user_name+'</td></tr>'+
		    	         '<tr><th>治疗方案</th><td>'+medicalConsultation.cure.plan+'</td></tr>'+		    	   
		    	         '<tr><th>会诊状态</th><td>';
		    	for(var x in resultDictList){
	   			if(resultDictList[x].value==medicalConsultation.con_status){
	   				html+=resultDictList[x].result+'</td></tr>';
	   			}	
		    	}
		    	html+='<tr><th>会诊时间</th><td>'+time+'</td></tr><tr><th>会诊评价</th><td>'+medicalConsultation.evaluate+'</td></tr></table>';
		    	$(".consultation-detail").replaceWith(html);
		    	 $(".edit-cure").val(medicalConsultation.cure.plan);
		    }); 
	 }	
});

function getLocalTime(nS) {       
    return  new Date(parseInt(nS) * 1000).format("yyyy-MM-dd");    // hh:mm
 }   
Date.prototype.format = function(format) {
    var date = {
           "M+": this.getMonth() + 1,
           "d+": this.getDate(),
//           "h+": this.getHours(),
//           "m+": this.getMinutes(),
//           "s+": this.getSeconds(),
//           "q+": Math.floor((this.getMonth() + 3) / 3),
//           "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
           format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
           if (new RegExp("(" + k + ")").test(format)) {
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1
                         ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
}