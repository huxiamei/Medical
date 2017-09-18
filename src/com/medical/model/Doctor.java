package com.medical.model;

import javax.validation.constraints.NotNull;


/**
 * ҽ��̳��û���
 * Table BAS_DOCTORS
 * 
 * ҽ������ҽԺ�����ң���ӦHospitalDepartment�е����ݣ�
 * 
 * @author medical 
 *
 */
public class Doctor{
	
	private User user;
	@NotNull(message="������Ϣ����Ϊ��")
	private HospitalDepartment hospitalDepartment;
	private String background;
	private String department;
	
	public Doctor()
	{
		
	}

	public Doctor(User user,HospitalDepartment department) {
		super();
		
		this.hospitalDepartment = department;
		this.user = user;
	}
	


	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	public HospitalDepartment getHospitalDepartment() {
		return hospitalDepartment;
	}

	public void setHospitalDepartment(HospitalDepartment department) {
		this.hospitalDepartment = department;
	}

}
