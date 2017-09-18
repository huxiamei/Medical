var basePath = '';

$(document).ready(function () {
	//路径前缀
	var location = (window.location+'').split('/'); 
	basePath = location[0]+'//'+location[2]+'/'+location[3]+'/background/role/'; 
	
	$('.update').click(function(){
		
		var $tr = $(this).parent().parent();
		var code = $tr.find('.code').eq(0).text();
		var role_name = $tr.find('.role_name').eq(0).text();
		var description = $tr.find('.description').eq(0).attr('title');
		var updateId = $(this).attr('updateId');
		
		$('.roleForm input[name=code]').val(code);
		$('.roleForm input[name=role_name]').val(role_name);
		$('.roleForm textarea[name=description]').val(description);
		$('.roleForm input[name=id]').val(updateId);
	});
	
	
	$(document).on('click','.submitUpdateRole',function(){
		var path = basePath + "roleAddUpdate";
		$.ajax({
	        type:"post",
	        cache: false,
	        dataType:"json",
	        data:$(".roleForm").serialize(), 
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
	         url: basePath + "roleDelete?id=" + id,
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
		$('.roleForm input[name=code]').val("");
		$('.roleForm input[name=role_name]').val("");
		$('.roleForm textarea[name=description]').val("");
		$('.roleForm input[name=id]').val("");
	}
})
