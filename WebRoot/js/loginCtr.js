/**
 * 登录管理
 */

var basePath = "";
var path = "";

$(document).ready(function () {
	
	//路径前缀
	var location1 = (window.location+'').split('/'); 
    basePath = location1[0]+'//'+location1[2]+'/'+location1[3]+'/';
    
    $("#UserLogin").on("click",function()
	{
    	getUser();
        if(self.login)
    	{
        	loginin();
    	}
	});
    
    
});

function getUser()
{
	var self = this;
	var id = $("#u").val();
	var pwd= $("#p").val();
	self.role = $("#role").val();  //登录角色
	
	if(id!="" && pwd!="")
    {
		self.userId = id;
		self.password = pwd;
		self.login = true;
	}
	else
	{	
		self.login = false;
		alert("用户名和密码不能为空");
	}
	
}

function loginin()
{
	if(self.role == "admin")
	{
	  path =  basePath+"Login/AdminLogin";
	}
	else
	{
	   path =  basePath+"Login/UserLogin";
	}	
	$.ajax({
        type:"post",
        cache: false,
        data:{'userId':self.userId,'password':self.password},
        dataType:"json",
        async: false,  
        url: path,
        success: function (data) {        
        	//弹出框显示
        	alert(data.message);
        	if(data.success==1)
    		{
        		
        	 window.location.href=basePath+data.url;		
    		}
        	
        },
        error:function(data)
	   {
        	alert("登录失败,用户名或者密码不正确");
	   }
	 });
	
	 return false;  
	
}