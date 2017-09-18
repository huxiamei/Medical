package com.medical.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ҽԺ
 * Table BAS_HOSPITAL
 * 
 * ҽԺID
 * ҽԺ����
 * ҽԺ����
 * �շѱ�׼
 * ҽԺ�Ǽ���1.��  2.�ң�
 * ҽԺ�˻�
 * @author medical
 *
 */
public class Hospital {

	@NotNull(message="ҽԺid����Ϊ��")
	private int id;
	
	@NotNull(message="ҽԺ��������Ϊ��")
	@Size(min=1,max=20,message="ҽԺ����������1��20���ַ�֮��")
	private String hospital_name;
	
	private String description;
	private String chargeStandard;
	
	@NotNull(message="ҽԺ�Ǽ�����Ϊ��")
	private int hospital_level;
	
	private double account;
	

	public Hospital()
	{
		
	}
	
	


	public Hospital(int id, String hospitalName, String description,
			int hospitalLevel, double account) {
		super();
		this.id = id;
		hospital_name = hospitalName;
		this.description = description;
		hospital_level = hospitalLevel;
		this.account = account;
	}


	public String getChargeStandard() {
		return chargeStandard;
	}

	public void setChargeStandard(String chargeStandard) {
		this.chargeStandard = chargeStandard;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String name) {
		this.hospital_name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHospital_level() {
		return hospital_level;
	}

	public void setHospital_level(int hospital_level) {
		this.hospital_level = hospital_level;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}
	
	
}
