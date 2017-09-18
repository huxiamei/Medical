package com.medical.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.medical.model.MedicalOrder;
import com.medical.model.OrderDetail;

public interface OrderMapper {

	@Select(value = "SELECT id, consultation_id, accout, order_status FROM med_order LIMIT #{orderId}, 10")
	public List<MedicalOrder> getOrderList(int currPage);

	@Select(value = "SELECT patient_id FROM med_case WHERE id = (SELECT case_id FROM med_consultation WHERE id = #{consultationId})")
	public int getPatientIdByConsultationId(int consultationId);

	@Select(value = "SELECT user_name FROM bas_user WHERE id = #{patientId}")
	public String getPatientNameById(int patientId);

	@Delete(value = "delete from med_order where id = #{orderId}")
	public int deleteOrderById(int orderId);

	@Select(value = "SELECT COUNT(*) AS orderCount FROM med_order")
	public int getOrderCount();

	public List<MedicalOrder> searchOrder(Map<String, Object> map);

	public int getOrderCountByName(String patientName);

	@Select(value = "SELECT id, order_id, hos_id, con_id, acount, order_status FROM order_detail WHERE order_id = #{orderId}")
	public List<OrderDetail> getOrderDetailByOrderId(Integer orderId);
	
	@Select(value = "SELECT hospital_name FROM bas_hospital WHERE id = #{hospitalId}")
	public String getHospitalNameById(Integer hospitalId);
	
	@Select(value = "SELECT acount FROM order_detail WHERE hos_id = #{hosId}")
	public Double getOrderDetailAccountByHosId(Integer hosId);
	
	@Select(value = "SELECT accout FROM med_order WHERE id = #{orderId}")
	public Double getTotalAccountByOrderId(Integer orderId);
	
	public int updateTotalAccountByOrderId(Map<String, Object> map);
	
	public int updateOrderDetailByOrderId(Map<String, Object> map);
	
	@Select(value = "SELECT account FROM bas_hospital WHERE id = #{hosId}")
	public Double getHospitalTotalAccount(Integer hosId);
	
	public int updateHospitalTotalAccount(Map<String, Object> map);
	
	public int updateOrderStatus(Map<String, Integer> map);

}
