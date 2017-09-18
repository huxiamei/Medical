
$(document).ready(function () {	
	//科室列表
	departmentDictList={};
	//总页数
	var pageTotal=0;
	//当前页数
	var page=1;
	
	var hospital_flag=-1;
	var department_flag=-1;
	var doctor_flag=-1;
	//获取医院列表
	HospitalDictList={};
	 new MyAjax().onAjaxGet({
	        url: "http://localhost:8080/Medical/MedicalConsultation/getHospital"
	    }, function (hospitalList) {
	    	HospitalDictList=hospitalList;
	    });
	//获取result状态的书籍字典
	var resultDictList=[];
	 new MyAjax().onAjaxGet({
	        url: "http://localhost:8080/Medical/MedicalConsultation/getResultDict"
	    }, function (resultList) {
	    	resultDictList=resultList;
	    });
	//获取整个对象
	Consultation={};
	
	//获取总页数
	new MyAjax().onAjaxGet({
        url: "http://localhost:8080/Medical/MedicalConsultation/getTotalPage"
    }, function (pageNum) {
    	pageTotal=pageNum;
	var html='';
		if(pageNum>0){
			html+='<li name="reduce"><a href="#" >上一页</a></li>';
	    	for(var i=1;i<=pageNum;i++){
	    		if(page==i){
	    			html+='<li class="current" name="'+i+'">'+i+'</li>';
	    		}else{
	    			html+='<li name="'+i+'"><a href="#">'+i+'</a></li>';
	    		}
	    		
	    	}
	    	html+='<li name="add"><a href="#" >下一页</a></li>';
	    	$(".pageIndex").prepend(html);
		}
    });
	
	$(".pageIndex").on("click", "li", function () {
	    var otherpage=$(this).attr("name");
	    if(otherpage=="reduce"){
	    	if(page!=1){
	    		page--;
	    		consultationList();
	    	}
	    }else if(otherpage=="add"){
	    	if(page!=pageTotal){
	    		page++;
	    		consultationList();
	    	}
	    }else{
	    	 if(page!=otherpage){
	 	    	page=otherpage;
	 	    	consultationList();
	    	}
	    }
	});
	
	
	consultationList();
	
	//删除
	$(".list").on("click", ".delete-consultation", function () {
		if(confirm("确定要删除吗？")) {
			var medicalConsuitation={};
			medicalConsuitation.id=$(this).parent().parent().attr("name");
			$(this).parent().parent().remove();
			new MyAjax().onAjaxPost({
		        url: "http://localhost:8080/Medical/MedicalConsultation/delete",
			    data: medicalConsuitation
		    }, function (data) {
		    	
		    });
		}
	});
	
	//修改时，显示原有会诊详细信息
	$(".list").on("click", ".show-consultation", function () {
		//获取会诊id
		var id=$(this).parent().parent().attr("name");	
		
		//获取会诊单详细信息
		new MyAjax().onAjaxGet({
	        url: "http://localhost:8080/Medical/MedicalConsultation/viewDetail?id="+id
	    }, function (medicalConsultation) {
	    	Consultation=medicalConsultation;
	    	$('.popupBg').fadeIn(200);
			$('.popup').fadeIn();
	    	$('.box #doctor').val(medicalConsultation.doctor.user.user_name);
	    	$('.box #consultion_id').val(medicalConsultation.id);
	    	$('.box #case_id').val(medicalConsultation.cure.id);
	    	//medicalConsultation.select_department[i].department.hospital.hospital_name
	    	var length=medicalConsultation.select_department;
	    	$('.popupAdd .addmore').remove();
	    	var html='';
	    	
	    	for(var x in medicalConsultation.select_department ){
	        	if($('.popupAdd .addmore').size() < 5){
	        		html='<div class="addmore">'+ 
					'<div>'+
						
						'<select name="hospital" disabled>'+
							'<option>'+medicalConsultation.select_department[x].department.hospital.hospital_name+'</option>'+
						'</select>'+
						'<select name="department" disabled>'+
							'<option>'+medicalConsultation.select_department[x].department.department.dep_name+'</option>'+
						'</select>'+
						'<select name="doctor" disabled>'+
						    '<option>'+medicalConsultation.select_department[x].doctor.user.user_name+'</option>'+
						'</select>'+
					'</div>'+
				'</div>';
	        		$('.popupAdd #addConsultion').after(html);

	    			//给每个新加的会诊申请绑定点击事件
	    			$(".popupAdd .addmore:eq(0) .deleteConsultion").click(function(e) {
	                    $(this).parent().parent().remove();
	                });
	        	}       	
	        }
	    	
	    	//显示会诊状态信息值
	    	var status_option='';
	    	 for(var x in resultDictList){
	    			if(resultDictList[x].value==medicalConsultation.con_status){
	    				status_option+='<option value="'+resultDictList[x].value+'" selected="selected">'+resultDictList[x].result+'</option>';
	    			}else{
	    				status_option+='<option value="'+resultDictList[x].value+'">'+resultDictList[x].result+'</option>';
	    			}	  
	    		}
	    	$(".status").prepend(status_option);
	    	
	    });
		
		//做信息修改提交操作
		$('#btn-consultation').click(function(){
			var status_value=$('.status option:selected').val();
			Consultation.con_status=status_value;
			new MyAjax().onAjaxPost({
		        url: "http://localhost:8080/Medical/MedicalConsultation/update",
			    data: Consultation
		    }, function (data) {
		    	
		    });
		});
	});
	function depDictList(){
		
	}
	//显示所有会诊列表信息
	function consultationList(){
		
		new MyAjax().onAjaxGet({
	        url: "http://localhost:8080/Medical/MedicalConsultation/viewAllByPage?pageNo="+page
	    }, function (medicalConsultationList) {
	    	var html='';
	        for(var i in medicalConsultationList){
		        html='';	
		        html+='<tr name="'+medicalConsultationList[i].id+'"><td class="first w1 c">'+medicalConsultationList[i].id+'</td>'+
		              '<td class="w3 c">'+medicalConsultationList[i].medicalCase.patient.user.user_name+'</td>'+
		              '<td class="w2 c">'+medicalConsultationList[i].doctor.user.user_name+'</td>'+
		              '<td class="w3 c">';
		        for(var x in medicalConsultationList[i].select_department ){
		        	html+=medicalConsultationList[i].select_department[x].doctor.user.user_name+'、';
		        }
		        html=html.substring(0,html.length-1);
		        for(var x in resultDictList){
	    			if(resultDictList[x].value==medicalConsultationList[i].con_status){
	    				html+='</td><td class="w2 c">'+resultDictList[x].result+'</td>';
	    			}	  
	    		}
		        html+= '<td class="w4 c"><a class="show-consultation">修改</a> <a class="delete-consultation">删除</a></td></tr>';
		        $(".list").append(html);
	        }
	    });
	}
});