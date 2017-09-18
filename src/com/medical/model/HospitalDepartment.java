package com.medical.model;

import javax.validation.constraints.NotNull;

/**
 * ҽԺ���Ҷ�Ӧ��
 * Table BAS_DEPAERMENT_HOSPITAL
 * 
 * ���
 * ҽԺ
 * ����
 * @author medical
 *
 */
public class HospitalDepartment {

	@NotNull
	private int id;
	
	@NotNull(message="ҽԺ����Ϊ��")
	private Hospital hospital;
	@NotNull(message="���Ҳ���Ϊ��")
	private Department department;
	
	public HospitalDepartment()
	{
		
	}

	
	public HospitalDepartment(int id, Hospital hospital, Department department) {
		super();
		this.id = id;
		this.hospital = hospital;
		this.department = department;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
