package com.medical.model;

import javax.validation.constraints.NotNull;

/**
 * ���Ʒ���
 * Table MED_CURE
 * 
 * ���Ʒ���id
 * ���Ʒ���
 * ������
 * @author medical
 *
 */
public class MedicalCure {

	@NotNull(message="id不能为空")
	private int id;
	@NotNull(message="治疗方案不能为空")
	private String plan;
	private String result;
    private int consultation_id;
    private String doctor;
    
	public MedicalCure()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getConsultation_id() {
		return consultation_id;
	}

	public void setConsultation_id(int consultation_id) {
		this.consultation_id = consultation_id;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	
	
} 