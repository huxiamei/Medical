/**
 * order page
 */

function DeleteOrder(id, currentPage) {
	if (confirm("确定要删除吗？")) {
		$(this).parent().parent().remove();
		window.location.href = "http://localhost:8080/Medical/order/deleteOrderById/"
				+ id + "/" + currentPage;
	}
}

function ModifyOrder(id, patientName, account, status, currPage) {
	$('#modifyPatientName').html(patientName);
	$('#modifyOrderId').html(id);
	$('#total').val(account);
	$('#hidePage').val(currPage);
	var order = {};
	order.id = id;
	new MyAjax()
			.onAjaxGet(
					{
						url : "http://localhost:8080/Medical/order/modifyOrder",
						data : order
					},
					function(OrderDetailList) {
						$("#modifyStatus")[0].selectedIndex = status;
						for ( var i in OrderDetailList) {
							var html = '<div> <div id="'
									+ OrderDetailList[i].id
									+ '" class="order-hosiptal">'
									+ OrderDetailList[i].hospital_name
									+ '</div>'
									+ '<div class="left"><span>金额：&nbsp;</span> <input id="'+OrderDetailList[i].hos_id+'"class="smallText" type="number" name="name" required="required" onclick="modifyDetailAccount(this);"  readonly="readonly" value="'
									+ OrderDetailList[i].acount
									+ '" ><span>&nbsp;元</span></div></div>'
							$('#totalAccountDiv').before(html);
							
//							var html2= '<div class="popup order-detailed" class="animated"><div class="box"><div class="popupTop">'+
//						           '<div class="popupClose"><a href="javascript:;" class="popupCloseBtn-detailed">×</a></div></div>'+
//						           '<div class="popupAdd"><form action="#" method="post"><div>'+
//										 '<div class="order-hosiptal">医院：<input name="" value="重师附属医院" type="text" class="middle-text" readonly/></div>'+
//								         '<div class="left"><span>金额：&nbsp;<span> <input class="smallText" type="number" name="name" required="required" value="200" ><span>&nbsp;元</span></div></div>'+
//						            '<div class="bottom"><input class="btn" name="submit" value="提交" type="submit"></div></form></div></div> </div>';
//						        
//							$('.popup-detailed').append(html2);	
						    
						
						}
					})
}


// function SearchOrder(currentPage) {
// var orderId = $('#orderId').val();
// var patientName = $('#userName').val();
// var param = {};
// param.id = orderId;
// param.name = patientName;
// param.currPage = currentPage;
// alert(orderId);
// new MyAjax().onAjaxPost({
// url : "http://localhost:8080/Medical/order/searchOrder",
// data : param
// }, function(orderPager) {
// var orderList = orderPager.dataList;
// for ( var i in orderList) {
// alert(orderList[i].id);
// var html = '<tr> <td class="first w1 c" id="orderId">'
// + orderList[i].id + '</td></tr>';
// $(".title-left").html(orderList[i].id);
// })
// }

function SearchOrder(currentPage) {
	var orderId = $('#orderId').val();
	var patientName = $('#userName').val();
	// var param = {};
	// param.id = orderId;
	// param.name = patientName;
	// param.currPage = currentPage;
	// alert(orderId);

	if (orderId == "" || orderId == null) {
		orderId = 10203040;
	}
	if (patientName == "" || patientName == null) {
		patientName = "10203040";
	}
	// new MyAjax().onAjaxPost({
	// url : "http://localhost:8080/Medical/order/searchOrder",
	// data : param
	// }, function(orderPager) {
	// var orderList = orderPager.dataList;
	// for ( var i in orderList) {
	// alert(orderList[i].id);
	// var html = '<tr> <td class="first w1 c" id="orderId">'
	// + orderList[i].id + '</td></tr>';
	// $(".title-left").html(orderList[i].id);

	url = "http://localhost:8080/Medical/order/searchOrder/" + orderId + "/"
			+ patientName + "/1";
	url = encodeURI(encodeURI(url));
	document.getElementById('searchOrderForm').action = url;
	document.getElementById("searchOrderForm").submit();
}

function prePage(currentPage) {
	var orderId = $('#orderId').val();
	var patientName = $('#userName').val();
	currentPage = currentPage - 1;
	if (orderId == '' && patientName == '') {

		window.location.href = "http://localhost:8080/Medical/order/showOrderList/"
				+ currentPage;
	} else {
		if (orderId == "" || orderId == null) {
			orderId = 10203040;
		}
		if (patientName == "" || patientName == null) {
			patientName = "10203040";
		}
		url = "http://localhost:8080/Medical/order/searchOrder/" + orderId
				+ "/" + patientName + "/" + currentPage;
		url = encodeURI(encodeURI(url));
		window.location.href = url;
	}

}

function nextPage(currentPage) {
	var orderId = $('#orderId').val();
	var patientName = $('#userName').val();
	currentPage = currentPage + 1;
	if (orderId == '' && patientName == '') {
		window.location.href = "http://localhost:8080/Medical/order/showOrderList/"
				+ currentPage;
	} else {
		if (orderId == "" || orderId == null) {
			orderId = 10203040;
		}
		if (patientName == "" || patientName == null) {
			patientName = "10203040";
		}
		url = "http://localhost:8080/Medical/order/searchOrder/" + orderId
				+ "/" + patientName + "/" + currentPage;
		url = encodeURI(encodeURI(url));
		window.location.href = url;
	}
}

function modifyDetailAccount(ob){
	//医院id
	 var thisId = $(ob).attr("id");
	 $('#hideHosId').val(thisId);
	 //orderDetailId
	 var orderDetailId = $(ob).parent().prev().attr("id");
	 $('#hideOrderDetailId').val(orderDetailId);
	 //医院名
	 var hosName = $(ob).parent().prev().text();
	 $('#detailHosName').val(hosName);
	 
	 var account =$('#'+thisId).val();
	 $('#detailAccount').val(account);
	 
}

function ModifyAccount(){
//	var totalAccount = $('#total').val();
	var orderId = $('#modifyOrderId').text();
	var hosId = $('#hideHosId').val();
	var detailAccount = $('#detailAccount').val();
	var orderDetail = {};
	orderDetail.order_id = orderId;
	orderDetail.hos_id = hosId;
	orderDetail.acount = detailAccount;
	 new MyAjax().onAjaxPost({
	 url : "http://localhost:8080/Medical/order/modifyDetailAccount",
	 data : orderDetail
	 }, function(orderPager) {
	 })
	 $('.popup-detailed').hide();
	 //改前的orderDetail中的总金额
	 var beforeAccount = $('#'+hosId).val();
	 //改后填入框
	 $('#'+hosId).val(detailAccount);
	 //差值
	 var difference = detailAccount-beforeAccount;
	 //订单总金额
	 var total = $('#total').val();
	 total = total*1+difference*1
	 $('#total').val(total);
	 $('#detailAccount').val(detailAccount);
	 
	
//	url = "http://localhost:8080/Medical/order/modifyDetailAccount/" + orderId + "/" + hosId + "/" + detailAccount;
//	url = encodeURI(encodeURI(url));
//	document.getElementById('detailSubForm').action = url;
//	document.getElementById("detailSubForm").submit();
}

function TotalSubmit(){
	var selectVal = $("#modifyStatus").val();
	var orderId = $('#modifyOrderId').text();
	var currPage = $('#hidePage').val()
	var order = {};
	order.order_status = selectVal;
	order.id = orderId
	
	url = "http://localhost:8080/Medical/order/modifyTotal/" + orderId + "/" + selectVal + "/" + currPage;
	url = encodeURI(encodeURI(url));
	document.getElementById('totalSubmitForm').action = url;
	document.getElementById("totalSubmitForm").submit();
	
//	new MyAjax().onAjaxPost({
//		 url : "http://localhost:8080/Medical/order/modifyTotal",
//		 data : order
//		 }, function(orderPager) {
//		 })
//		 $('.popupBg').fadeOut(200,function(){
//				$('.popup').fadeOut(200);
//			});
}

//$(function(){
//	//修改金额
//	$('body').on('click', '.popupAdd .smallText', function(){
//		$('.popup-detailed').show();
//	});
//	
//	//关闭弹窗
//	$('.popupCloseBtn-detailed').click(function(){
//		$('.popup-detailed').hide();
//	});
	


//});
