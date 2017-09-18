package com.medical.model;


/**
 * ����ѡ���
 * Tabel MED_SELECT_DEPARTMENT
 * 
 * ����ѡ��id
 * ����
 * ҽ��
 * @author medical 
 *
 */
public class MedicalSelectDepartment {

	private int id;
	private HospitalDepartment department;
	private Doctor doctor;
	private MedicalConsultation consultation;
	
	public MedicalSelectDepartment()
	{
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HospitalDepartment getDepartment() {
		return department;
	}

	public void setDepartment(HospitalDepartment department) {
		this.department = department;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public MedicalConsultation getConsultation() {
		return consultation;
	}

	public void setConsultation(MedicalConsultation consultation) {
		this.consultation = consultation;
	}
	
	
	
}
