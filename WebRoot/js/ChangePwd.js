/**
 * 修改密码
 */

var basePath = "";
var path = "";
var update = false;
var oldone = "";
var newone = "";

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    
    $("#UpdatePwd").on("click",function(){
    
    	judgePwd();
    	if(update)
		{
    		updatePwd();
		}
    	else
		{	
    	  alert("两次密码输入不一致");
		}
    	
    });
    
});

function judgePwd()
{

	oldone = $("#oldPassword").val();
	var newpwd1 = $("#newPassword").val();
	var newpwd2 = $("#newPasswordConfim").val();
	
	if(newpwd1 == newpwd2)
	{
		newone = newpwd1;
		update = true;
	}
}

function updatePwd()
{
	path = basePath+"/Login/ChangePassword";
	
	$.ajax({
        type:"post",
        cache: false,
        dataType:"json",
        data:{oldPassword:oldone,newPassword:newone}, //新建一个对象填充
        url: path,
        success: function (data) {  
        	//弹出框显示
        	alert(data.message);
        	if(data.success==1)
        	{
        		window.location.href=basePath+"/jsp/frontPage/login.jsp";
        	}
        		    	
        },
        error:function(data)
	   {      	
        	alert("error"+data.message);
	   }
	 });
}

