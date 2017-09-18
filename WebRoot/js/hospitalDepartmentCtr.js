/**
 * hospitalDepartment page 
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
	setSelectValue();
	
	//新增医院科室
	$("#HospitalDepartmentAdd").on('click',function()
	{		
		path = basePath+"/HospitalDepartment/add";
	});
	
	//点击修改
	$(document).on('click','#hospitalDepartmentUpdate',function()
	{
		var id = $(this).parent().parent().find("#hospitalDepartmentId").text();
		$("#HospitalDepartmentLayer").css("display","block");
	    path = basePath+"/HospitalDepartment/manage/update/"+id;
		update();	
	});
	
	
	//提交新增或者修改表单
	$("#HospitalDepartmentSubmit").on('click',function()
    {
		var hospitalId = $("#hospital").val();  
		var departmentId = $("#department").val();
		$.ajax({
	        type:"post",
	        cache: false,
	        data:{'hospitalId':hospitalId,'departmentId':departmentId},
	        dataType:"json",
	        url: path,
	        success: function (data) {        
	        	//弹出框显示
	        	alert(data.message);
	        	ResetTotalPage();
	        },
	        error:function(data)
		   {
	        	// $("#HospitalLayer").css("display","block");
	        	alert("新增失败");
		   }
		 });
	});	
	
	//删除按钮
	$(document).on('click','#hospitalDepartmentDelete',function()
	{	
		var id = $(this).parent().parent().find("#hospitalDepartmentId").text();
	    path = basePath+"/HospitalDepartment/manage/delete/"+id;
	    if(confirm("确定要删除吗？"))
    	{
	    	deleteHospitalDepartment();
    	}
});
	
});


//删除函数
function deleteHospitalDepartment()
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
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+'/HospitalDepartment/manage/totalPage',   
        dataType:"json",
        success: function (data) {  
        	TotalPage = data.page;    
        	$(".HospitalDepartmentPagenation").twbsPagination({     
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
	$(".HospitalDepartmentPagenation").twbsPagination('destroy');
	getPage();
	           
}


function setSelectValue()
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
        },
        error:function(data)
  	  {		 
  		  alert("error"+data.message);
  	   }
  	 });
	
	//给department的select选择框赋值
	
	$.ajax({
        type:"get",
        cache: false,
        url: basePath+"/Department/manage/Alldata",   
        dataType:"json",
        success: function (data) {          
        	for(var i=0;i<data.departmentList.length;i++)
    		{
        		var department = data.departmentList[i];
        		var option = head + department.id +"'>"+department.dep_name+footer;
    		    $("#department").append(option);
    		}       	
        },
        error:function(data)
  	  {		 
  		  alert("error"+data.message);
  	   }
  	 });
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
        	$("#hospital").val(data.UpdatehospitalDeaprtment.hospital.id); 
        	$("#department").val(data.UpdatehospitalDeaprtment.department.id);        	
        },
	  error:function(data)
	  {		 
		  alert("error"+data.message);
	   },
	 });
}

//分页数据查询
function getList(page)
{	
	 $.ajax({
         type:"get",
         cache: false,
         dataType:"json",
         url: basePath+'/HospitalDepartment/manage/list/'+page,
         success: function(date)       
         {   var hospitalDepartmentList = [];         	
        	 for(var i=0;i<date.hospitalDepartmentList.length;i++)
        	{
        		 var temp = date.hospitalDepartmentList[i];
        		 var obj = 
        		 {
        			 hd_id : temp.id,
        			 hos_name : temp.hospital.hospital_name,
        			 dep_name : temp.department.dep_name,
        		 };
        		 hospitalDepartmentList.push(obj);
        	}           	 
        	 $("#AllHospitalDepartment").html("");
        	 $("#HospitalDepartmentInfoTemplate").tmpl(hospitalDepartmentList).appendTo("#AllHospitalDepartment");
        	 
         }
	 });
}











