/**
 * 充值的js
 */
var basePath = "";
var path = "";
var id = 0;
//还需
var needPay=0;
$(document).ready(function () {
	
	//路径前缀
	var location = (window.location+'').split('/'); 
    basePath = location[0]+'//'+location[2]+'/'+location[3]; 
    id = $("#currentPatId").text();
    
    $("#rechargeNow").on("click",function(){
    	goToRecharge();
    });
    
});

function goToRecharge(){
	var url=basePath+'/Patient/getPatient/'+id;
	$.ajax({
        type:"GET",
        cache: false,
        url: url,   
        dataType:"json",
        success: function (data) {  
        		var account=data.patient.account+parseInt($("#rechargeMoney").val());
        		url=basePath+'/Patient/recharge/'+id;
				$.ajax({
			        type:"post",
			        cache: false,
			        data:{'account':account},
			        dataType:"json",
			        async: false,  
			        url: url,
			        success: function (data) { 
			        	window.location.href=basePath+'/jsp/frontPage/recharge_success.jsp';
			        }
				 });
        	}
        	});
}