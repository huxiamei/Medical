package com.medical.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.MedicalOrder;
import com.medical.model.MsgDTO;
import com.medical.model.OrderDetail;
import com.medical.model.Pager;
import com.medical.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	@Qualifier("OrderServiceImpl")
	public OrderService orderService;

	/**
	 * 查看订单列表
	 * @param currentPage
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/showOrderList/{currentPage}", method = RequestMethod.GET)
	public String showOrderList(
			@PathVariable(value = "currentPage") int currentPage,
			Map<String, Object> map) {
		Pager<MedicalOrder> orderPager = orderService.getOrderList(currentPage);
		map.put("orderList", orderPager);
		return "manage/order";
	}

	/**
	 * 删除订单
	 * @param orderId
	 * @param currentPage
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/deleteOrderById/{orderId}/{currentPage}", method = RequestMethod.GET)
	public String deleteOrderById(@PathVariable(value = "orderId") int orderId,
			@PathVariable(value = "currentPage") int currentPage,
			Map<String, Object> map) {
		orderService.deleteOrderById(orderId);
		Pager<MedicalOrder> orderPager = orderService.getOrderList(currentPage);
		List<MedicalOrder> orderList = orderPager.getDataList();
		if (orderList.size() == 0) {
			orderPager = orderService.getOrderList(currentPage - 1);
		}
		map.put("orderList", orderPager);
		return "manage/order";
	}

	/**
	 * 查找订单
	 * @param orderId
	 * @param patientName
	 * @param currentPage
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/searchOrder/{orderId}/{patientName}/{currentPage}", method = RequestMethod.GET)
	// @Consumes({ "application/json", "application/xml" })
	public String searchOrder(@PathVariable(value = "orderId") Integer orderId,
			@PathVariable(value = "patientName") String patientName,
			@PathVariable(value = "currentPage") int currentPage,
			Map<String, Object> map) {
		String name = null;
		try {
			name = java.net.URLDecoder.decode(patientName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (orderId == 10203040) {
			orderId = null;
		}
		if ("10203040".equals(name)) {
			name = null;
		}
		Pager<MedicalOrder> orderPager = orderService.searchOrder(currentPage,
				orderId, name);
		map.put("orderList", orderPager);

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("patientName", name);
		condition.put("orderId", orderId);
		map.put("condition", condition);

		return "manage/order";
	}

	// @RequestMapping(value = "/searchOrder", method = { RequestMethod.POST })
	// @ResponseBody
	// @POST
	// @Consumes({ "application/json", "application/xml" })
	// public MsgDTO searchOrder(@RequestBody SeachParam param,
	// Map<String, Object> map) {
	// MsgDTO msgDTO = new MsgDTO();
	// Integer orderId = null;
	// if (param.getId() != null && !"".equals(param.getId())) {
	// if (isNumeric(param.getId())) {
	// orderId = Integer.parseInt(param.getId());
	// }
	// }
	// int currentPage = Integer.parseInt(param.getCurrPage());
	// String patientName = param.getName();
	// Pager<MedicalOrder> orderPager = orderService.searchOrder(currentPage,
	// orderId, patientName);
	// map.put("orderList", orderPager);
	// msgDTO.setData(orderPager);
	// System.out.println(orderPager.getDataList().get(0));
	// System.out.println(map.get("orderList").toString());
	// // return "manage/order";
	// return msgDTO;
	// }

	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	@GET
	@RequestMapping("/modifyOrder")
	@ResponseBody
	public MsgDTO modifyOrder(@QueryParam("order") OrderDetail order) {
		MsgDTO msgDTO = new MsgDTO();
		List<OrderDetail> orderDetailList = orderService.getOrderDetailByOrderId(order.getId());
		msgDTO.setData(orderDetailList);
		return msgDTO;
	}
	
//	@RequestMapping(value = "/modifyDetailAccount/{orderId}/{hosId}/{detailAccount}", method = RequestMethod.GET)
//	// @Consumes({ "application/json", "application/xml" })
//	public String modifyDetailAccount(@PathVariable(value = "orderId") Integer orderId,
//			@PathVariable(value = "hosId") Integer hosId,@PathVariable(value = "detailAccount") Integer detailAccount) {
//		//得到医院的总金额
//		int hospitalTotalAccount = orderService.getHospitalTotalAccount(hosId);
//		//orderDetail中医院的金额
//		int originDetailAccount = orderService.getOrderDetailAccountByHosId(hosId);
//		//order中的总金额
//		int totalAccount = orderService.getTotalAccountByOrderId(orderId);
//		//差额
//		int diffValue = detailAccount - originDetailAccount;
//		//Order表的新的总金额
//		int newTotal = totalAccount + diffValue;
//		//新的医院总金额
//		int newHospitalTotalAccount = hospitalTotalAccount + diffValue;
//		//更新Order表中的总金额
//		orderService.updateTotalAccountByOrderId(orderId, newTotal);
//		//更新orderDetail中的金额
//		orderService.updateOrderDetailByOrderId(orderId, hosId, detailAccount);
//		//更新医院的总金额
//		orderService.updateHospitalTotalAccount(hosId, newHospitalTotalAccount);
//		return "";
//	}
	
	 @RequestMapping(value = "/modifyDetailAccount", method = { RequestMethod.POST })
	 @ResponseBody
	 @POST
	 @Consumes({ "application/json", "application/xml" })
	public String modifyDetailAccount(@RequestBody OrderDetail orderDetail) {
		//得到医院的总金额
		 Double hospitalTotalAccount = orderService.getHospitalTotalAccount(orderDetail.getHos_id());
		//orderDetail中医院的金额
		 Double originDetailAccount = orderService.getOrderDetailAccountByHosId(orderDetail.getHos_id());
		//order中的总金额
		 Double totalAccount = orderService.getTotalAccountByOrderId(orderDetail.getOrder_id());
		//差额
		 Double diffValue = orderDetail.getAcount() - originDetailAccount;
		//Order表的新的总金额
		 Double newTotal = totalAccount + diffValue;
		//新的医院总金额
		 Double newHospitalTotalAccount = hospitalTotalAccount + diffValue;
		//更新Order表中的总金额
		orderService.updateTotalAccountByOrderId(orderDetail.getOrder_id(), newTotal);
		//更新orderDetail中的金额
		orderService.updateOrderDetailByOrderId(orderDetail.getOrder_id(), orderDetail.getHos_id(), orderDetail.getAcount());
		//更新医院的总金额
		orderService.updateHospitalTotalAccount(orderDetail.getHos_id(), newHospitalTotalAccount);
		return "";
	}
	 
	@RequestMapping(value = "/modifyTotal/{orderId}/{selectVal}/{currPage}", method = RequestMethod.GET)
	public String modifyTotal(@PathVariable(value = "orderId") Integer orderId,@PathVariable(value = "selectVal") Integer selectVal,
			@PathVariable(value = "currPage") Integer currPage, Map<String, Object> map) {
		orderService.updateOrderStatus(orderId, selectVal);
		Pager<MedicalOrder> orderPager = orderService.getOrderList(currPage);
		map.put("orderList", orderPager);
		return "manage/order";
	}

}
