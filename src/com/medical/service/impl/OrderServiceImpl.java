package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.mapper.OrderMapper;
import com.medical.model.MedicalOrder;
import com.medical.model.OrderDetail;
import com.medical.model.Pager;
import com.medical.service.OrderService;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	public Pager<MedicalOrder> getOrderList(int currPage) {
		int startIndex = (currPage - 1) * 10;
		List<MedicalOrder> orderList = orderMapper.getOrderList(startIndex);
		for (int i = 0; i < orderList.size(); i++) {
			orderList.get(i).setPatient_name(
					this.getPatientNameById(orderList.get(i)
							.getConsultation_id()));
		}
		int orderCount = orderMapper.getOrderCount();
		Pager<MedicalOrder> orderPager = new Pager<MedicalOrder>(10, currPage,
				orderCount, orderList);
		return orderPager;
	}

	public String getPatientNameById(int consultationId) {
		int patientId = orderMapper
				.getPatientIdByConsultationId(consultationId);
		String patientName = orderMapper.getPatientNameById(patientId);
		return patientName;
	}

	public int deleteOrderById(int orderId) {
		int num = orderMapper.deleteOrderById(orderId);
		return num;
	}

	public Pager<MedicalOrder> searchOrder(int currPage, Integer id,
			String patientName) {
		int startIndex = (currPage - 1) * 10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startIndex", startIndex);
		map.put("id", id);
		map.put("patientName", patientName);
		List<MedicalOrder> orderList = orderMapper.searchOrder(map);
		for (int i = 0; i < orderList.size(); i++) {
			orderList.get(i).setPatient_name(
					this.getPatientNameById(orderList.get(i)
							.getConsultation_id()));
		}
		int orderCount = orderMapper.getOrderCount();
		int orderCountByNmae = orderMapper.getOrderCountByName(patientName);
		Pager<MedicalOrder> orderPager = new Pager<MedicalOrder>();
		if (id != null) {
			orderPager = new Pager<MedicalOrder>(10, 1, 1, orderList);
		} else if (patientName != null) {
			orderPager = new Pager<MedicalOrder>(10, currPage,
					orderCountByNmae, orderList);
		} else {
			orderPager = new Pager<MedicalOrder>(10, currPage, orderCount,
					orderList);
		}
		return orderPager;
	}

	public List<OrderDetail> getOrderDetailByOrderId(Integer orderId) {
		List<OrderDetail> orderDetailList = orderMapper
				.getOrderDetailByOrderId(orderId);
		for (int i = 0; i < orderDetailList.size(); i++) {
			orderDetailList.get(i).setHospital_name(
					orderMapper.getHospitalNameById(orderDetailList.get(i)
							.getHos_id()));
		}
		return orderDetailList;
	}

	public Double getOrderDetailAccountByHosId(Integer hosId) {
		Double account = orderMapper.getOrderDetailAccountByHosId(hosId);
		return account;
	}

	public Double getTotalAccountByOrderId(Integer orderId) {
		Double total = orderMapper.getTotalAccountByOrderId(orderId);
		return total;
	}

	public int updateTotalAccountByOrderId(Integer orderId, Double account) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("orderId", orderId);
		int n = orderMapper.updateTotalAccountByOrderId(map);
		return n;
	}

	public int updateOrderDetailByOrderId(Integer orderId, Integer hosId,
			Double account) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("orderId", orderId);
		map.put("hosId", hosId);
		int n = orderMapper.updateOrderDetailByOrderId(map);
		return n;
	}

	public Double getHospitalTotalAccount(Integer hosId) {
		Double hosTotalAccount = orderMapper.getHospitalTotalAccount(hosId);
		return hosTotalAccount;
	}

	public int updateHospitalTotalAccount(Integer hosId, Double account) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("hosId", hosId);
		int n = orderMapper.updateHospitalTotalAccount(map);
		return n;
	}

	public int updateOrderStatus(Integer orderId, Integer status) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("orderId", orderId);
		map.put("status", status);
		int n = orderMapper.updateOrderStatus(map);
		return n;
	}

}
