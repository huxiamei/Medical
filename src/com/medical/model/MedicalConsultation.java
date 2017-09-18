package com.medical.model;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;


/**
 * 会诊表
 * Table MED_CONSULTATION 
 * 会诊id
 * 主治医生
 * 病例
 * 科室选择表(MedicalSelectDepartment)
 * 治疗方案
 * 状态
 * @author medical
 *
 */
public class MedicalConsultation {


	@NotNull(message="会诊表id不能为空")
	private int id;	
	private Doctor doctor;
	private MedicalCase medicalCase;
	private List<MedicalSelectDepartment> select_department;
	private MedicalCure cure;	
	private int con_status;
	private Date time;
	private String evaluate;
	private double amount;
	private String stringDate;
	private String selectDoctorId;
	
	
	public String getStringDate() {
		return stringDate;
	}
	public void setStringDate(String stringDate) {
		this.stringDate = stringDate;
	}
	public String getSelectDoctorId() {
		return selectDoctorId;
	}
	public void setSelectDoctorId(String selectDoctorId) {
		this.selectDoctorId = selectDoctorId;
	}
	public MedicalConsultation()
	{
		
	}
	public int getId() {
		return id;
		
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public MedicalCase getMedicalCase() {
		return medicalCase;
	}

	public void setMedicalCase(MedicalCase medicalCase) {
		this.medicalCase = medicalCase;
	}

	public List<MedicalSelectDepartment> getSelect_department() {
		return select_department;
	}

	public void setSelect_department(List<MedicalSelectDepartment> selectDepartment) {
		select_department = selectDepartment;
	}

	public MedicalCure getCure() {
		return cure;
	}

	public void setCure(MedicalCure cure) {
		this.cure = cure;
	}

	public int getCon_status() {
		return con_status;
	}

	public void setCon_status(int conStatus) {
		con_status = conStatus;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "MedicalConsultation [id=" + id + ", doctor=" + doctor
				+ ", medicalCase=" + medicalCase + ", select_department="
				+ select_department + ", cure=" + cure + ", con_status="
				+ con_status + ", time=" + time + "]";
	}
	
} 