package com.medical.model;

import java.util.Date;

import com.medical.tool.myenum.InforType;

/**
 * ��Ϣ
 * Table MED_NOTIFICATION
 * 
 * ��Ϣid
 * ������
 * ������
 * ��Ϣ����
 * ʱ�� 
 * �Ƿ��ȡ
 * �Ƿ�鿴
 * @author medical
 *
 */
public class MedicalNotification {

	private int id;
	private User sender;
	private User receiver;
	private InforType infor_type;
	private Date send_time;
	private int infor_status;
	private int reading;
	private int con_id;
	
	
	public int getCon_id() {
		return con_id;
	}



	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}



	public MedicalNotification()
	{
		
	}

	

	public MedicalNotification(int id, User sender, User receiver,
			InforType inforType, Date sendTime, int inforStatus, int reading) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		infor_type = inforType;
		send_time = sendTime;
		infor_status = inforStatus;
		this.reading = reading;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getSender() {
		return sender;
	}


	public void setSender(User sender) {
		this.sender = sender;
	}


	


	public User getReceiver() {
		return receiver;
	}


	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}


	public InforType getInfor_type() {
		return infor_type;
	}


	public void setInfor_type(InforType inforType) {
		infor_type = inforType;
	}


	public Date getSend_time() {
		return send_time;
	}


	public void setSend_time(Date sendTime) {
		send_time = sendTime;
	}


	public int isInfor_status() {
		return infor_status;
	}


	public void setInfor_status(int inforStatus) {
		infor_status = inforStatus;
	}


	public int isReading() {
		return reading;
	}


	public void setReading(int reading) {
		this.reading = reading;
	}


	
}
