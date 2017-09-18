package com.medical.service;

import java.util.List;

import com.medical.model.MedicalNotification;
import com.medical.tool.Page;


public interface MedicalNotificationService {

	

	/**
	 * 得到我收到的未读的消息
	 * @param userId 接收用户id
	 * @return
	 */
	public MedicalNotification getMyNotReadingMessge(int userId);
	
	/**
	 * 增加消息
	 * @param message
	 */
	public void addMedicalNotification(MedicalNotification message);
	
	/**
	 * 根据消息id得到某条消息
	 * @param id
	 * @return
	 */
	public MedicalNotification selectMessageById(int id);
	
	/**
	 * 得到信息总数
	 * @return
	 */
	public int selectCount();
	
	/**
	 * 得到信息数量
	 * @param userId 信息所属用户
	 * @param send  我发送的或者是我接收到的
	 * @return
	 */
	public int selectMessageCount(int userId,boolean send);
	
	
	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteMessage(int id);
	
	
	/**
	 * 修改消息
	 * @param message
	 */
	public void updateMessage(MedicalNotification message);
	
	/**
	 * 分页查询信息
	 * @param page 页
	 * @param userId 用户id
	 * @param send 查询我发送的或者是收到的
	 * @return
	 */
	public List<MedicalNotification> findMessage(Page page,int userId,boolean send);
	
}
