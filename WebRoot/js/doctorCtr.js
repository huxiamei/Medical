/**
 * 医生
 */
var basePath = "";
var path = "";
var currPage = 1;
var TotalPage = 1;

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
	
	//初始化页面
	getPage();
	
	//设置医院科室的select选择框
	setHospitalValue();
	
	//新增医生  
	$("#DoctorAdd").on('click',function()
	{		
		path = basePath+"/Doctor/manage/add";
	});
	
	//点击修改
	$(document).on('click','#doctorUpdate',function()
	{
		//var id = $("#hospitalId").text();
		var id = $(this).parent().parent().find("#doctorId").text();
		$("#DoctorLayer").css("display","block");
	    path = basePath+"/Doctor/manage/update/"+id;
		update();	
	});
	
	
	//提交新增或者修改表单
	$("#DoctorSubmits").on('click',function()
    {
		$.ajax({
	        type:"post",
	        cache: false,
	        dataType:"json",
	        data:$("#doctorDetail").serialize(), //新建一个对象填充
	        url: path,
	        success: function (data) {        
	        	//弹出框显示
	        	alert(data.message);
	        	ResetTotalPage();
	        },
	        error:function(data)
		   {
	        	// $("#HospitalLayer").css("display","block");
	        	alert("error"+data.message);
		   }
		 });
	});	
	
	//删除按钮
	$(document).on('click','#doctorDelete',function()
	{	
		var id = $(this).parent().parent().find("#doctorId").text();
	    path = basePath+"/Doctor/manage/delete/"+id;
	    if(confirm("确定要删除吗？"))
    	{
	    	deleteDcotor();
    	}
});
	
	//关闭弹窗按钮
	$(".popupCloseBtn").on('click',function()
	{
		reset();
	});
	
	$("#hospital").on('change',function()
	{
		setDepartmentValue();
	});
});




//设置医院select的值
function setHospitalValue()
{	
	var head = "<option value='";
	var footer = "</option>";
	
	//给hospital的select选择框赋值
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+"/Hospital/manage/Alldata",   
        dataType:"json",
        success: function (data) {          
        	for(var i=0;i<data.hospitalList.length;i++)
    		{
        		var hospital = data.hospitalList[i];
        		var option = head + hospital.id +"'>"+hospital.hospital_name+footer;     		
    		    $("#hospital").append(option);
    		}  
        	setDepartmentValue();
        },
        error:function(data)
  	  {		 
  		  alert("error"+data.message);
  	   }
  	 });
}

//给department的select选择框赋值  
function setDepartmentValue(dep)
{
	var head = "<option value='";
	var footer = "</option>";
	
	$("#hospitalDepartment").empty();	
	
	var hospital = $("#hospital").val();
	var ddd = dep;
	
    $.ajax({
     type:"get",
     cache: false,
     url: basePath+"/HospitalDepartment/manage/getDepartment/"+hospital,   
     dataType:"json",
     success: function (data) { 
    	 if(data.departmentList.length==0)
		 {	 
    		 alert("该医院下没有科室，请确认选择");
		 }
    	 else
		 {
			 for(var i=0;i<data.departmentList.length;i++)
			{
	    		var dep = data.departmentList[i];
	    		var option = head + dep.id +"'>"+dep.department.dep_name+footer;
			    $("#hospitalDepartment").append(option);
			}  
			 if(ddd !="")
			{
				 $("#hospitalDepartment").val(ddd); 
			}
		 }	     	
    },
    error:function(data)
	  {		 
		  alert("error"+data.message);
	   }
	 });
}


//删除函数
function deleteDcotor()
{
	$.ajax({
        type:"get",
        cache: false,
        url: path,   
        success: function (data) {  
        	alert("删除成功");
        	ResetTotalPage();
        },
	});		
}
//得到页面总数
function getPage()
{
	getList(1);
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+'/Doctor/manage/totalPage',   
        dataType:"json",
        success: function (data) {  
        	
        	TotalPage = data.page;   
        	$(".DoctorPagenation").twbsPagination({   
        		currentPage:currPage,
                onPageClick: function (evt, page) {
               	 currPage = page;                
               	 getList(page);
                }, 
                totalPages: TotalPage,
            });   
        },
	});	
}


//重置页面数量
function ResetTotalPage()
{ 
	$(".DcotorPagenation").twbsPagination('destroy');
	
	getPage();	            
}


function reset()
{
	$("#id").val(0);  
	$("#user_name").val(''); 
	$("#user_password").val(''); 
	$("#idCard").val(''); 
	$("#tel").val(''); 
	$("#email").val('');
	$("#birthdate").val('');
	
}

//根据后台传递来的数据填充前台
function fillForm(doctor,docbirthdate)
{	
	$("#id").val(doctor.user.id);  
	$("#user_name").val(doctor.user.user_name); 
	$("#user_password").val(doctor.user.user_password); 
	$("#idCard").val(doctor.user.idCard); 
	$("#tel").val(doctor.user.tel); 
	$("#email").val(doctor.user.email);
	$("#birthdate").val(docbirthdate);
	 
	var role = doctor.user.role_id;
	var gender = doctor.user.gender;
	var hospital = doctor.hospitalDepartment.hospital.id;
	var dep = doctor.hospitalDepartment.id;
	
	$(".gender").each(function(){  
	    if($(this).val() == gender){  
	        $(this).prop( "checked", true );  
	    }  
	});  

	$(".role").each(function(){  
	    if($(this).val() == role){  
	        $(this).prop( "checked", true );  
	    }  
	});  
	
	
	$("#hospital").val(hospital); 	
	setDepartmentValue(dep);
}

//修改表单内容填充
function update()
{
	//修改提交页面时的地址	
	$.ajax({
        type:"get",
        cache: false,
        url: path,   
        dataType:"json",
        success: function (data) {     
        	fillForm(data.Updatedoctor,data.docbirthdate);

        },
	  error:function(data)
	  {		 
		  alert("error"+data.message);
	   }
	 });
}

//分页数据查询
function getList(page)
{	
	 $.ajax({
         type:"get",
         cache: false,
         dataType:"json",
         url: basePath+'/Doctor/manage/list/'+page,
         success: function(date)         
         {       	
        	 var doctorList = [];         	
        	 for(var i=0;i<date.doctorList.length;i++)
        	{
        		 var temp = date.doctorList[i];
        		 var obj = 
        		 {        				 
        			 doc_id : temp.user.id,
        			 doc_name : temp.user.user_name,
        			 doc_gender :  temp.user.gender=='M'?'男':'女',
        			 doc_email : temp.user.email,
        			 hos_name: temp.hospitalDepartment.hospital.hospital_name +"--"+ temp.hospitalDepartment.department.dep_name
        		 };
        		 doctorList.push(obj);
        	}           	 
        	 $("#AllDoctor").html("");
        	 $("#DoctorInfoTemplate").tmpl(doctorList).appendTo("#AllDoctor");
         }
	 });
}
