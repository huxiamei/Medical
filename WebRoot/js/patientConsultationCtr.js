/**
 * 我的会诊
 */

var basePath = "";
var path = "";
var MedConTotalPage = 1;
var id = 0;

$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]+'/MedicalConsultation'; 
    id = $("#currentPatId").text();
    //初始化页面
    getPage();
    
    	
});

function tt()
{
	$(document).on('click','#theconsultationDetailId',function()
	{
    	var con_id = $(this).parent().text();
    	localStorage.setItem("theconsultationDetailId", con_id);    	
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
        	MedConTotalPage = data.totalpage;    
        	
        	$(".MedConPagenation").twbsPagination({ 
        		
                onPageClick: function (evt, page) {             	            
               	 getList(page);
                }, 
                totalPages: MedConTotalPage,
            });  
        	
        },
	});	
}

//分页数据查询
function getList(page)
{	
	
	 $.ajax({
         type:"get",
         cache: false,
         dataType:"json",
         url: basePath+'/show/'+page,
         success: function(date)       
         {  
        	
        	 var medicalConsultationList = [];         	
        	 for(var i=0;i<date.medicalConsultationList.length;i++)
        	{
        		 var temp = date.medicalConsultationList[i];     		 
        		 var option = "审核";
        		 var className ="sgBtn2";
        		 var idName = "btn2";
        		 var paystatus = "未付预款";
        		 
        		 if(temp.con_status>=0 &&temp.con_status<1)
	    		{       			 
    			  className = "sgBtn";
    			  idName = "checkAgreeBtn";
	    		}
	    		else if(temp.con_status==-1)
	    		{
	    			 option ="已驳回申请";		    			
	    		}
	    		else
    			{
	    			option ="已同意申请";		    			
    			}
        		     		
        		if(temp.con_status>=7){
        			   paystatus = "已付完款";
	    		}else if(temp.con_status<7&&temp.con_status>=3){
	    			paystatus = "已付预款";
	    		}        		 
        		 var obj = 
        		 {
        			 currentConId : temp.id,
        			 currentPatDoctor : temp.doctor.user.user_name,
        			 currentConTime : date.conTimeList[i],
        			 currentConStatus1:date.statusType[i], 
        			 currentConStatus2:paystatus, 
        			 option:option,
        			 className:className,
        			 idName:idName,
        		 };
        		 medicalConsultationList.push(obj);
        	}
        	$("#MedConInfoBody").html("");
        	$("#MedConInfoTemplate").tmpl(medicalConsultationList).appendTo("#MedConInfoBody");
        	tt();
         }
	 });
}