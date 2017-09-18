package com.medical.service;

import java.util.List;

import com.medical.model.MedicalOrder;
import com.medical.model.OrderDetail;
import com.medical.model.Pager;

public interface OrderService {

	public Pager<MedicalOrder> getOrderList(int currPage);

	public String getPatientNameById(int consultationId);

	public int deleteOrderById(int orderId);

	public Pager<MedicalOrder> searchOrder(int currPage, Integer id,
			String patientName);

	public List<OrderDetail> getOrderDetailByOrderId(Integer orderId);
	
	public Double getOrderDetailAccountByHosId(Integer hosId);
	
	public Double getTotalAccountByOrderId(Integer orderId);
	
	public int updateTotalAccountByOrderId(Integer orderId, Double account);
	
	public int updateOrderDetailByOrderId(Integer orderId, Integer hosId, Double account);
	
	public Double getHospitalTotalAccount(Integer hosId);
	
	public int updateHospitalTotalAccount(Integer hosId, Double account);
	
	public int updateOrderStatus(Integer orderId, Integer status);
}
