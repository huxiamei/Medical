$(document).ready(function () {
	var choose=1;
	var resultDictList=[];
	localStorage.setItem("chooseStyle", choose);
	
	//获取result状态的书籍字典
	 new MyAjax().onAjaxGet({
	        url: "http://localhost:8080/Medical/MedicalConsultation/getResultDict"
	    }, function (resultList) {
	    	resultDictList=resultList;
	    });
	 
	
	//根据选择显示对应的会诊信息
	chooseConsultation();
	$("#consultationTab #button_one").click(function(){
		
		choose=1;
		//alert(choose);
		chooseConsultation();
		localStorage.setItem("chooseStyle", choose);
	});
	$("#consultationTab #button_two").click(function(){
		
		choose=2;
		//alert(choose);
		chooseConsultation();
		localStorage.setItem("chooseStyle", choose);
	});
	$("#consultationTab #button_three").click(function(){
		choose=3;
		//alert(choose);
		chooseConsultation();
		localStorage.setItem("chooseStyle", choose);
	});
//	href="doctor_consultationTable.jsp"
	  $(".hzInfo1").on("click", "a", function () {
		  var consultationId = $(this).attr("name");
		  //alert(consultationId);
          localStorage.setItem("consultationId", consultationId);
		});
	  $(".hzInfo2").on("click", "a", function () {
		  var consultationId = $(this).attr("name");
		  //alert(consultationId);
          localStorage.setItem("consultationId", consultationId);
		});
	  $(".hzInfo3").on("click", "a", function () {
		  var consultationId = $(this).attr("name");
		 // alert(consultationId);
          localStorage.setItem("consultationId", consultationId);
		});
	function chooseConsultation(){
			new MyAjax().onAjaxGet({
		        url: "http://localhost:8080/Medical/MedicalConsultation/viewByDoctorIdAndChoose?choose="+choose
		    }, function (medicalConsultationList) {
	    		//alert(medicalConsultationList.length);
		    	var html='';
		    	if(choose==1||choose==3){
		    		
			    	for(var i in medicalConsultationList)
			    	{
			    		
			    		var time=getLocalTime(medicalConsultationList[i].time/1000);
			    		html+='<tr class="doctor_consultation"><td ><a href="doctor_consultationTable.jsp" name="'+medicalConsultationList[i].id+'">'+medicalConsultationList[i].id+
			                '</a></td><td>'+medicalConsultationList[i].doctor.user.user_name+
			                '</td><td>'+medicalConsultationList[i].medicalCase.patient.user.user_name+
			                 '</td><td>'+time;
			    		
			    		for(var x in resultDictList){
			    			
			    			if(resultDictList[x].value==medicalConsultationList[i].con_status){
			    				html+='</td><td>'+resultDictList[x].result;
			    			}	  
			    		}
			    		if(medicalConsultationList[i].con_status>=7){
			    			html+='</td><td>已付完款</td></tr>';
			    		}else if(medicalConsultationList[i].con_status<7&&medicalConsultationList[i].con_status>=3){
			    			html+='</td><td>已付预款</td></tr>';
			    		}else{
			    			html+='</td><td>未付预款</td></tr>';
			    		}
			    	}
			    	if(choose==1)
		    		{
		    		    $(".hzInfo1 .doctor_consultation").remove();			    	
				    	$(".hzInfo1").append(html);
		    		}
			    	else if(choose==3)
			    	{
			    		 $(".hzInfo3 .doctor_consultation").remove();					    	
					     $(".hzInfo3").append(html);
					    
			    	}
                  
		    	}
		    	else if(choose==2){    		

		    		for(var i in medicalConsultationList)
			    	{
			    		var time=getLocalTime(medicalConsultationList[i].time/1000);
			    		html+='<tr  class="doctor_consultation"><td id="ConId"><a href="doctor_consultationTable.jsp" name="'+medicalConsultationList[i].id+'">'+medicalConsultationList[i].id+
			                 '</a></td><td>'+medicalConsultationList[i].doctor.user.user_name+
			               '</td><td>'+medicalConsultationList[i].medicalCase.patient.user.user_name+
			                 '</td><td>'+time;
			    		for(var x in resultDictList){
			    			if(resultDictList[x].value==medicalConsultationList[i].con_status)
			    			{
			    				html+='</td><td>'+resultDictList[x].result;
			    			}	  
			    		}
			    		if(medicalConsultationList[i].con_status>=7){
			    			html+='</td><td>已付完款</td>';
			    		}else if(medicalConsultationList[i].con_status<7&&medicalConsultationList[i].con_status>=3){
			    			html+='</td><td>已付预款</td>';
			    		}else{
			    			html+='</td><td>未付预款</td>';
			    		}
			    		
			    		if(medicalConsultationList[i].con_status>=0 &&medicalConsultationList[i].con_status<2)
			    		{
			    			html+='<td><div class="sgBtn" id="checkAgreeBtn" >审核</div></td></tr>';
			    		}
			    		else if(medicalConsultationList[i].con_status==-2)
			    		{
			    			 html+='<td><div class="sgBtn2" id="btn2" style="display:block">已驳回申请</div></td></tr>';
			    		}
			    		else
		    			{
			    			html+='<td><div class="sgBtn2" id="btn2" style="display:block">已同意申请</div></td></tr>';
		    			}
			    	}
		    		$(".hzInfo2 .doctor_consultation").remove();
			    	
			    	$(".hzInfo2").append(html);
		    	}
		    });
		}
	
});
function getLocalTime(nS) {       
    return  new Date(parseInt(nS) * 1000).format("yyyy-MM-dd");    // hh:mm
 }   

Date.prototype.format = function(format) 
{
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
                  format = format.replace(RegExp.$1, RegExp.$1.length == 1? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
           }
    }
    return format;
};
