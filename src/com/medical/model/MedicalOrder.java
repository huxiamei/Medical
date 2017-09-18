package com.medical.model;

import javax.validation.constraints.NotNull;

/**
 * 订单
 * Table MED_ORDER
 * 
 * 订单id
 * 会诊
 * 金额
 * 支付状态
 * @author medical
 *
 */
public class MedicalOrder {

	@NotNull(message="订单id不能为空")
	private int id;
	@NotNull(message="会诊表id不能为空")
	private int consultation_id;
	@NotNull(message="金额不能为空")
	private double accout;
	private int order_status;
	private String patient_name;
	
	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public MedicalOrder()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAccout() {
		return accout;
	}

	public void setAccout(double accout) {
		this.accout = accout;
	}

	public int getOrder_status() {
		return order_status;
	}

	public void setOrder_status(int orderStatus) {
		order_status = orderStatus;
	}
	public int getConsultation_id() {
		return consultation_id;
	}

	public void setConsultation_id(int consultation_id) {
		this.consultation_id = consultation_id;
	}
	
	
	
}
