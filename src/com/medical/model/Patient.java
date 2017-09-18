package com.medical.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.medical.tool.myenum.Blood;



/**
 * ���ˣ��̳��û���
 * Table BAS_PATIENTS
 * 
 * ����Ѫ��
 * ����ҽ��
 * �˻����
 * @author medical
 *
 */
public class Patient{

	private User user;
	
	@NotNull(message="Ѫ�Ͳ���Ϊ��")
	private Blood blood;
	
	@NotNull(message="����ҽ����Ϊ��")
	private Doctor doctor;
	
	@Min(value=0,message="�˻�����Ϊ��")
	private double account;
	
	public Patient()
	{
		super();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient(Blood blood, Doctor doctor, double account) {
		
		this.blood = blood;
		this.doctor = doctor;
		this.account = account;
	}
	public Blood getBlood() {
		return blood;
	}
	public void setBlood(Blood blood) {
		this.blood = blood;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor_id(Doctor doctorId) {
		doctor = doctorId;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	
	
}
