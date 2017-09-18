$(document).ready(function () {
	//总页数
	var pageTotal=0;
	//当前页数
	var page=1;
	//获取总页数
	new MyAjax().onAjaxGet({
        url: "http://localhost:8080/Medical/MedicalCure/getTotalPage"
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
	
	//点击页面
	$(".pageIndex").on("click", "li", function () {
	    var otherpage=$(this).attr("name");
	    if(otherpage=="reduce"){
	    	if(page!=1){
	    		page--;
	    		medicalCureList();
	    	}
	    }else if(otherpage=="add"){
	    	if(page!=pageTotal){
	    		page++;
	    		medicalCureList();
	    	}
	    }else{
	    	 if(page!=otherpage){
	 	    	page=otherpage;
	 	    	medicalCureList();
	    	}
	    }
	});
	
	medicalCureList();
	
	//删除
	$(".list").on("click", ".delete-cure", function () {
		if(confirm("确定要删除吗？删除同时会删除该病人会诊记录")) {
			var medicalCure={};
			medicalCure.consultation_id=$(this).parent().parent().attr("name");
			medicalCure.id=$(this).parent().parent().attr("id");
			$(this).parent().parent().remove();
			new MyAjax().onAjaxPost({
		        url: "http://localhost:8080/Medical/MedicalCure/delete",
			    data: medicalCure
		    }, function (data) {
		    	
		    });
		}
	});
	
	$(".list").on("click", ".show-cure", function () {
		$('.popupBg').fadeIn(200);
		$('.popup').fadeIn();
		$('.btn').click(function(){
			//做提交操作
			
		});
	});
	function medicalCureList(){
		//显示所有会诊列表信息
		new MyAjax().onAjaxGet({
	        url: "http://localhost:8080/Medical/MedicalCure/viewAll?pageNo="+page
	    }, function (medicalCureList) {
	    	var html='';
	        for(var i in medicalCureList){
	        html='';	
	        html+='<tr id="'+medicalCureList[i].id+'" name="'+medicalCureList[i].consultation_id+'"><td class="first w1 c">'+medicalCureList[i].id+'</td>'+
	              '<td class="w2 c">'+medicalCureList[i].consultation_id+'</td>'+
	              '<td class="w4 c">'+medicalCureList[i].plan+'</td>'+
	              '<td class="w2 c">'+medicalCureList[i].doctor+'</td>'+
	              '<td class="w2 c">'+medicalCureList[i].result+'</td>'+
	              '<td class="w3 c"><!--<a class="show-cure">修改</a>--> <a class="delete-cure">删除</a></td></tr>';
	        $(".list").append(html);
	        }
	    });
		
	}
});