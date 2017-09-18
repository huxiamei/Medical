/**
 * department page 
 */
var basePath = "";
var path = "";
var currPage = 1;
var TotalPage = 1;
var department ;

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]+'/Department'; 
	
	//初始化页面
	getPage();
	
	//新增医院
	$("#departmentAdd").on('click',function()
	{
		path = basePath+"/manage/add";
		$("#DepartmentLayer").css("display","block");
	});
	
	//点击修改
	$(document).on('click','#departmentUpdate',function()
	{
		var id = $(this).parent().parent().find("#departmentId").text();
		$("#DepartmentLayer").css("display","block");
	    path = basePath+"/manage/update/"+id;
		update();	
	});
	
	
	//提交新增或者修改表单
	$("#DepartmentSubmits").on('click',function()
    {
		$.ajax({
	        type:"POST",
	        cache: false,
	        data:$("#departmentDetail").serialize(), 
	        dataType:"json",
	        url: path,
	        success: function (data) {        
	        	//弹出框显示
	        	alert(data.message);
	        	ResetTotalPage();
	        },
	        error:function(data)
		   {
	        	alert("erro");
	        	// $("#HospitalLayer").css("display","block");
	        	alert(data.message);
		   }
		 });
	});	
	
	//删除按钮
	$(document).on('click','#departmentDelete',function()
	{	
		var id = $(this).parent().parent().find("#departmentId").text();
	    path = basePath+"/manage/delete/"+id;
	    if(confirm("确定要删除吗？"))
    	{
	    	deleteDepartment();
    	}
});
	
	//关闭弹窗按钮
	$(".popupCloseBtn").on('click',function()
	{
		reset();
	});
});


//删除函数
function deleteDepartment()
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
        url: basePath+'/manage/totalPage',   
        dataType:"json",
        success: function (data) {  
        	TotalPage = data.page;    
        	$(".DepartmentPagenation").twbsPagination({   
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
	
	$(".DepartmentPagenation").twbsPagination('destroy');
	getPage();
	            
	
}
function reset()
{
	$("#id").val(0);  
	$("#dep_name").val(''); 
	$("#dep_description").val(''); 
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
        	$("#id").val(data.Updatedepartment.id);  
        	$("#dep_name").val(data.Updatedepartment.dep_name); 
        	$("#dep_description").val(data.Updatedepartment.description);        	
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
         url: basePath+'/manage/list/'+page,
         success: function(date)
         
         {   var departmentList = [];         	
        	 for(var i=0;i<date.departmentList.length;i++)
        	{
        		 var temp = date.departmentList[i];
        		 var obj = 
        		 {
        			 dep_id : temp.id,
        			 dep_name : temp.dep_name,
        			 dep_description : temp.description,
        		 };
        		 departmentList.push(obj);
        	}           	 
        	 $("#AllDepartment").html("");
        	 $("#DepartmentInfoTemplate").tmpl(departmentList).appendTo("#AllDepartment");
        	 
         }
	 });
}











