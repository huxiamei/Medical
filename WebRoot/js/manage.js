// JavaScript Document

var w,h,className;
function getSrceenWH(){
	w = $(window).width();
	h = $(window).height();
	$('.popupBg').width(w).height(h);
}
window.onresize = function(){  
	getSrceenWH();
}  
$(window).resize();  
$(function(){
	getSrceenWH();	
	//显示弹框
	$('.context .show').click(function(){
		$('.popupBg').fadeIn(200);
		$('.popup').fadeIn();
	});
	//关闭弹窗
	$('.popupCloseBtn').click(function(){
		$('.popupBg').fadeOut(200,function(){
			$('.popup').fadeOut(200);
		});
	});
	//删除
	/*$('.context .delete').click(function(){
		if(confirm("确定要删除吗？")) {
			$(this).parent().parent().remove();
		}
	});*/
	
	/*** 选项卡1 ***/
	/*** 实现点击选项，显示对应 ***/
	$('.tab a').click(function(){
		//遍历所有选项，移除选中样式
		$('.popupTop .tab a').each(function(index, element) {
			$(this).removeClass('active');
		});
		//添加选中后的样式
		$(this).addClass('active');
		//获取对应模块的id名字
		var className = $(this).attr('class').substr(0,4);
		//将所有模块都隐身
		$('.popupAdd fieldset').each(function(index, element) {
			$(this).css('display','none');
		});
		//显示对应模块
		$('#' + className).css('display', 'block');
	})
	
	
	/*** 选项卡2 ***/
	/*** 实现点击选项，显示对应 ***/
	$(".bottom input[type='button']" ).click(function(){
		//获取对应模块的id名字
		var className = $(this).attr('class').substr(0,4);
		//将所有模块都隐身
		$('.popupAdd fieldset').each(function(index, element) {
			$(this).css('display','none');
		});
		//显示对应模块
		$('#' + className).css('display', 'block');
		//遍历上面的选项
		$('.popupTop .tab a').each(function(index, element) {
			//将对应选项显示选中样式，未选中移除样式
			if($(this).hasClass(className)){
				$(this).addClass('active');
			}else{
				$(this).removeClass('active');
			}
		 });
	});
	
	//根据所选的角色来判断是否显示
	$(".popup .popupAdd select[name='role']").change(function(){
		var role = $(this).val();
		var idName = $(this).attr('class');
		if(role == 1){
			$('.popup .popupAdd #' + idName).hide();
		}else{
			$('.popup .popupAdd #' + idName).show();
		}
	});
	
	//添加会诊申请
	$('.popupAdd .addConsultion').click(function(){
		if($('.popupAdd .addmore').size() < 5){
			$('.popupAdd #addConsultion').after(
			'<div class="addmore">'+ 
				'<div>'+
					'<a class="deleteConsultion" href="javascript:;">▬</a>'+
					'<select name="hospital">'+
						'<option value="1">重师附属医院</option>'+
						'<option value="2">重医附属医院</option>'+
					'</select>'+
					'<select name="department">'+
						'<option value="1">神经科</option>'+
						'<option value="2">肠胃科</option>'+
					'</select>'+
					'<select name="doctor">'+
						'<option value="1">冯欣</option>'+
						'<option value="2">胡夏美</option>'+
					'</select>'+
				'</div>'+
			'</div>'
			);
			//给每个新加的会诊申请绑定点击事件
			$(".popupAdd .addmore:eq(0) .deleteConsultion").click(function(e) {
                $(this).parent().parent().remove();
            });
		}
	});
	
	//点击移除
	$('.popupAdd .addmore .deleteConsultion').click(function(){
			$(this).parent().parent().remove();
    });
	
	//下拉
	$('.pub-nav h4 a').click(function(){
		var findUl = $(this).parent().next();
		if(findUl.attr('display')=='block'){
			$(this).parent().next().slideToggle(600);
		}else{
			findUl.siblings('ul').hide(600);
			$(this).parent().next().slideToggle(600);
		}
    });
	
	//总计实收
	//$(".popupAdd .smallText").keyup(function(){
	//	var totalAmount = 0;
	//	$(".popupAdd .smallText").each(function(){
	//		totalAmount = totalAmount + parseFloat($(this).val());
	//	});
	//	
	//	$("#total").val(totalAmount);
	//});
	
	
	//总计实收
	$(".order .popupAdd .smallText").change(function(){
		var totalAmount = 0;
		$(".order .popupAdd .smallText").each(function(){
			totalAmount = totalAmount + parseFloat($(this).val());
		});
		
		$("#total").val(totalAmount);
	});
	
	//select 过长的问题
	$('body').on('mouseover', 'select', function(){
		var $this = $(this);
		var titleVal = $this.find("option:selected").text();	
	    $(this).attr("title", titleVal);
	});
	
	//select 过长的问题
	$('body').on('mouseover', '.order-hosiptal', function(){
		var titleVal = $(this).text();
	    $(this).attr("title", titleVal);
	});
	
	
	//修改金额
	$('body').on('click', '.popupAdd .smallText', function(){
		$('.popup-detailed').show();
	});
	
	//关闭弹窗
	$('.popupCloseBtn-detailed').click(function(){
		$('.popup-detailed').hide();
	});
	
	
	
});


