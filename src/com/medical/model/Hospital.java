package com.medical.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 医院
 * Table BAS_HOSPITAL
 * 
 * 医院ID
 * 医院名字
 * 医院描述
 * 收费标准
 * 医院星级（1.甲  2.乙）
 * 医院账户
 * @author medical
 *
 */
public class Hospital {

	@NotNull(message="医院id不能为空")
	private int id;
	
	@NotNull(message="医院姓名不能为空")
	@Size(min=1,max=20,message="医院姓名必须在1到20个字符之内")
	private String hospital_name;
	
	private String description;
	private String chargeStandard;
	
	@NotNull(message="医院星级不能为空")
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
