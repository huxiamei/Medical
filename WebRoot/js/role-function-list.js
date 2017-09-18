var basePath = '';

$(document).ready(function () {
	//路径前缀
	var location = (window.location+'').split('/'); 
	basePath = location[0]+'//'+location[2]+'/'+location[3]+'/background/roleFunction/'; 
	
	$(document).on('click','.update',function(){
		
		var $td = $(this).parent().parent();
		var role_name = $td.find('.role_name').eq(0).text();
		var function_name = $td.find('.function_name').eq(0).text();
		var updateId = $(this).attr('updateId');
		
		$('.roleFunctionForm select[name=role_id] option').each(function(){
			
	        if($(this).text() == role_name){
	        	$(this).attr("selected",true);
	        }
	    });
		$('.roleFunctionForm select[name=function_id] option').each(function(){
	        if($(this).text() == function_name){
	        	$(this).attr("selected",true);
	        }
	    }); 
		$('.roleFunctionForm input[name=id]').val(updateId);
	});
	
	
	$(document).on('click','.submitUpdateRoleFuntion',function(){
		var path = basePath + "roleFunctionAddUpdate";
		$.ajax({
	        type:"post",
	        cache: false,
	        dataType:"json",
	        data:$(".roleFunctionForm").serialize(), 
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
	         url: basePath + "roleFunctionDelete?id=" + id,
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
		$('.roleFunctionForm select[name=role_id] option').each(function(){
	        	$(this).removeAttribute('selected');
	    });
		$('.roleFunctionForm select[name=function_id] option').each(function(){
			$(this).removeAttribute('selected');
	    }); 
		$('.roleFunctionForm input[name=id]').val("");
	};
});
