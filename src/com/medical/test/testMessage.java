package com.medical.test;


import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.medical.model.MedicalNotification;
import com.medical.service.MedicalNotificationService;
import com.medical.service.impl.MedicalNotificationServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:config/applicationContext.xml") 
public class testMessage {

	@Test
	public void testAddMessage()
	{
		
		MedicalNotification message = new MedicalNotification();
		
		MedicalNotificationService ms = new MedicalNotificationServiceImpl();
		ms.selectCount();
		message=ms.selectMessageById(1);
		System.out.println("ddas");
		
		
	}
}
	
	

