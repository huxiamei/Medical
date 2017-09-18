var basePath = '';

$(document).ready(function () {
	//路径前缀
	var location = (window.location+'').split('/'); 
	basePath = location[0]+'//'+location[2]+'/'+location[3]+'/background/function/'; 
	
	$(document).on('click','.update',function(){
		
		var $tr = $(this).parent().parent();
		var function_name = $tr.find('.function_name').eq(0).text();
		var description = $tr.find('.description').eq(0).attr('title');
		var updateId = $(this).attr('updateId');
		
		$('.functionForm input[name=function_name]').val(function_name);
		$('.functionForm textarea[name=description]').val(description);
		$('.functionForm input[name=id]').val(updateId);
	});
	
	
	$(document).on('click','.submitUpdateFuntion',function(){
		var path = basePath + "functionAddUpdate";
		$.ajax({
	        type:"post",
	        cache: false,
	        dataType:"json",
	        data:$(".functionForm").serialize(), 
	        url: path,
	        success:function(res){
				if(res.code == 1){
					//隐藏div
					$('.popupBg').fadeOut(200,function(){
						$('.popup').fadeOut(200);
					});
					//提示语句
					alert(res.message);
					//刷新当前页面.
					window.location.reload();
				}else{
					alert(res.message);
					//隐藏div
					$('.popupBg').fadeOut(200,function(){
						$('.popup').fadeOut(200);
					});
				}
				
				
			}
		 });
	});
	
	$(document).on('click','.delete',function(){
		var id = $(this).attr('deleteId');
		var $deleteTr = $(this).parent().parent();
		 $.ajax({
	         type:"get",
	         cache: false,
	         dataType:"json",
	         url: basePath + "functionDelete?id=" + id,
	         success: function(res){  
	        	if(res.code == 1){
	        		$deleteTr.remove();
	        	}
	        	alert(res.message); 
	         }
		 });
	});
	
	
	
	/********************重置表单**********************/
	$(document).on('click','.popupCloseBtn',function(){
		resetForm();
	});
	
	var resetForm = function(){
		$('.functionForm input[name=function_name]').val("");
		$('.functionForm textarea[name=description]').val("");
		$('.functionForm input[name=id]').val("");
	};
});
