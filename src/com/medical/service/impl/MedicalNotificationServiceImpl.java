package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.MedicalNotificationMapper;
import com.medical.model.MedicalNotification;
import com.medical.service.MedicalNotificationService;
import com.medical.tool.Page;

@Service("MedicalNotificationService")
public class MedicalNotificationServiceImpl implements
		MedicalNotificationService {

	@Resource
	private MedicalNotificationMapper mapper;
	
	
	public void addMedicalNotification(MedicalNotification message) {
		
		mapper.addMedicalNotification(message);
		
	}

	public MedicalNotification getMyNotReadingMessge(int userId) {
		
		return mapper.getMyNotReadingMessge(userId);
	}
	
	public MedicalNotification selectMessageById(int id)
	{
		return mapper.selectMessageById(id);
	}

	public int selectCount()
	{
		return mapper.selectCount();
	}

	public void deleteMessage(int id) {
		
		mapper.deleteMessage(id);
		
	}

	public void updateMessage(MedicalNotification message) {
	
		mapper.updateMessage(message);
		
	}
	
	public int selectMessageCount(int userId, boolean send) {
	
		if(send)  //我发送的信息
		{
			return mapper.selectSendMessageCount(userId);
		}
		else
		{
			return mapper.selectReceiveMessageCount(userId);
		}
	}

	public List<MedicalNotification> findMessage(Page page, int userId,
			boolean send) {
		
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("number", page.getRowNo());
		params.put("start", (page.getPageNo()-1)*page.getRowNo());
		params.put("userId", userId);
		if(send)
		{
			return mapper.findSendMessage(params); //调用不同的方法
		}
		else
		{
			return mapper.findReceiveMessage(params);
		}
		
	}
}
