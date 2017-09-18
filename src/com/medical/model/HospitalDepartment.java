package com.medical.model;

import javax.validation.constraints.NotNull;

/**
 * 医院科室对应表
 * Table BAS_DEPAERMENT_HOSPITAL
 * 
 * 编号
 * 医院
 * 科室
 * @author medical
 *
 */
public class HospitalDepartment {

	@NotNull
	private int id;
	
	@NotNull(message="医院不能为空")
	private Hospital hospital;
	@NotNull(message="科室不能为空")
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
