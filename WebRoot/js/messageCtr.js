/**
 * message
 */

var basePath = "";
var path = "";
var SendTotalPage = 1;
var ReceiveTotalPage = 1;

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]+'/Message'; 
    
    //初始化页面
    getPage();
    
    
   //删除按钮
	$(document).on('click','#deleteMessage',function()
	{	
		var id = $(this).parent().parent().find("#messageID").text();
	    path = basePath+"/delete/"+id;
	    if(confirm("确定要删除吗？"))
    	{
	    	deleteMessge();
    	}
});
});

//删除函数
function deleteMessge()
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
        url: basePath+'/totalPage',   
        dataType:"json",
        success: function (data) {  
        	SendTotalPage = data.sendpage;    
        	ReceiveTotalPage = data.receivepage;
        	
        	$(".RMessagePagenation").twbsPagination({ 
        		
                onPageClick: function (evt, page) {             	            
               	 getList(page,"R");
                }, 
                totalPages: ReceiveTotalPage,
            });   
        	$(".SMessagePagenation").twbsPagination({  
        		
                onPageClick: function (evt, page) {             	            
               	 getList(page,"S");
                }, 
                totalPages: SendTotalPage,
            });   
        },
	});	
}

//重置页面数量
function ResetTotalPage()
{
	$(".RMessagePagenation").twbsPagination('destroy');
	$(".SMessagePagenation").twbsPagination('destroy');	
	getPage();	
}

//分页数据查询
function getList(page,send)
{	
	 $.ajax({
         type:"get",
         cache: false,
         dataType:"json",
         url: basePath+'/show/'+page+'/'+send,
         success: function(date)       
         {   var messageList = [];         	
        	 for(var i=0;i<date.MessageList.length;i++)
        	{       		       		
        		var temp = date.MessageList[i];      		 
        		var time = date.sendTimeList[i];
        		var mtype = date.messageType[i]; 
        	
        		 var obj = 
        		 {
        		     message_id : temp.id,
        			 send_time : time,
        			 receive_userName : temp.receiver.user_name,
        			 send_userName: temp.sender.user_name,
        			 content: "编号 "+temp.con_id+"的会诊，  "+mtype+"， 请去<我的会诊>做相应处理",  
        		 };
        		 messageList.push(obj);
        	}   
        	 var Ulname = "";
        	 if(send=="S")
        	{
        		Ulname = "#sendMessage" ;
        		template = "#SendMessageInfoTemplate";
        	}
        	 else
        	{
        		Ulname = "#receiveMessage";
        		template = "#ReceiveMessageInfoTemplate";
        	}
        	 $(Ulname).html("");
        	 $(template).tmpl(messageList).appendTo(Ulname);
        	 
         }
	 });
}