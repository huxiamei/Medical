/**
 * hospital page 
 */
var basePath = "";
var path = "";
var currPage = 1;
var TotalPage = 1;

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]+'/Hospital'; 
	
	//初始化页面
	getPage();
	
	//新增医院
	$("#hospitalAdd").on('click',function()
	{		
		path = basePath+"/add";
	});
	
	//点击修改
	$(document).on('click','#hospitalUpdate',function()
	{
		//var id = $("#hospitalId").text();
		var id = $(this).parent().parent().find("#hospitalId").text();
		$("#HospitalLayer").css("display","block");
	    path = basePath+"/manage/update/"+id;
		update();	
	});
	
	
	//提交新增或者修改表单
	$("#HospitalSubmits").on('click',function()
    {
		$.ajax({
	        type:"post",
	        cache: false,
	        dataType:"json",
	        data:$("#hospitalDetail").serialize(), 
	        url: path,
	        success: function (data) {        
	        	//弹出框显示
	        	alert(data.message);
	        	ResetTotalPage();
	        },
	        error:function(data)
		   {
	        	// $("#HospitalLayer").css("display","block");
	        	alert(data.message);
		   }
		 });
	});	
	
	//删除按钮
	$(document).on('click','#hospitalDelete',function()
	{	
		var id = $(this).parent().parent().find("#hospitalId").text();
	    path = basePath+"/manage/delete/"+id;
	    if(confirm("确定要删除吗？"))
    	{
	    	deleteHospital();
    	}
});
	
	//关闭弹窗按钮
	$(".popupCloseBtn").on('click',function()
	{
		reset();
	});
});


//删除函数
function deleteHospital()
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
        	$(".HospitalPagenation").twbsPagination({   
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
	
	$(".HospitalPagenation").twbsPagination('destroy');
	getPage();
	            
	
}
function reset()
{
	$("#id").val(0);  
	$("#hospital_name").val(''); 
	$("#account").val(''); 
	$("#chargeStandard").val(''); 
	$("#hospital_level").val(''); 
	$("#description").val('');
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
        	$("#id").val(data.Updatehospital.id);  
        	$("#hospital_name").val(data.Updatehospital.hospital_name); 
        	$("#account").val(data.Updatehospital.account); 
        	$("#chargeStandard").val(data.Updatehospital.chargeStandard); 
        	$("#hospital_level").val(data.Updatehospital.hospital_level); 
        	$("#description").val(data.Updatehospital.description);
        	

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
         
         {   var hospitalList = [];         	
        	 for(var i=0;i<date.hospitalList.length;i++)
        	{
        		 var temp = date.hospitalList[i];
        		 var obj = 
        		 {
        			 hos_id : temp.id,
        			 hos_name : temp.hospital_name,
        			 hos_description : temp.description,
        			 hos_level : temp.hospital_level,
        		 };
        		 hospitalList.push(obj);
        	}           	 
        	 $("#AllHospital").html("");
        	 $("#HospitalInfoTemplate").tmpl(hospitalList).appendTo("#AllHospital");
        	 
         }
	 });
}











