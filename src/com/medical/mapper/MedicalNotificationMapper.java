package com.medical.mapper;


import java.util.List;
import java.util.Map;

import com.medical.model.MedicalNotification;
import com.medical.model.User;


/**
 * ��Ϣ
 * 
 * @author medical
 *
 */
public interface MedicalNotificationMapper {

	
	
	/**
	 * �õ�������û�в鿴�����Ϣ
	 * @param userId
	 * @return
	 */
	public MedicalNotification getMyNotReadingMessge(int userId);
	
	
	/**
	 * ������Ϣ
	 * @param message
	 */
	public void addMedicalNotification(MedicalNotification message);
	
	/**
	 * �����Ϣid��ѯ��Ϣ���
	 * @param id
	 * @return
	 */
	public MedicalNotification selectMessageById(int id);
	
	/**
	 * �õ���Ϣ����
	 * @return
	 */
	public int selectCount();
	
	/**
	 * ���id�����û���������Ϣ��ķ����ߺͽ����߲���
	 * @param id
	 * @return
	 */
	public User selectUser(int id);

	/**
	 * ɾ����Ϣ
	 * @param message
	 */
	public void deleteMessage(int id);
	
	/**
	 * �޸���Ϣ
	 * @param message
	 */
	public void updateMessage(MedicalNotification message);
	
	/**
	 * 得到我发送的信息总数
	 * @param userId
	 * @return
	 */
	public int selectSendMessageCount(int id);
	
	/**
	 * 得到我收到的信息总数
	 * @param userId
	 * @return
	 */
	public int selectReceiveMessageCount(int id);
	
	/**
	 * 得到我发送的信息集合
	 * @param param
	 * @return
	 */
	public List<MedicalNotification> findSendMessage(Map<String,Object> param);
	
	/**
	 * 得到我收到的信息集合
	 * @param param
	 * @return
	 */
	public List<MedicalNotification> findReceiveMessage(Map<String,Object> param);
}
