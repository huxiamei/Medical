package com.medical.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medical.model.Doctor;
import com.medical.model.MedicalNotification;
import com.medical.model.User;
import com.medical.service.DoctorService;
import com.medical.service.MedicalConsultationService;
import com.medical.service.MedicalNotificationService;
import com.medical.service.UserService;
import com.medical.tool.SendMessage;
import com.medical.tool.myenum.InforType;

@Controller
@RequestMapping("/index")
public class someController {

	@Autowired
	@Qualifier("MedicalNotificationService")
	MedicalNotificationService ms;
	
	@Autowired
	@Qualifier("DoctorService")
	DoctorService ds;
	
	@Autowired
	@Qualifier("UserService")
	UserService us;
	
	@Autowired
	@Qualifier("MedicalConsultationService")
	MedicalConsultationService cs;
	
	@Autowired
	@Qualifier("SendMessage")
	SendMessage send;
	
	
	@RequestMapping("/sendmessage")
	public String messageShow(Model model)
	{
		User user =us.selectUser(6);	 //主治医生
		User patient = us.selectUser(2);  //病人
		send.SendApplyConsultationMessage(user, 1);  //主治医生提交会诊申请
		send.SendAgreeOrNotMessage(patient, 1, true);  //病人同意会诊
		User check = us.selectUser(9);  //审核医生
		send.SendCheckAgreeOrNotMessage(check, 1, true);  //审核医生同意会诊
		
		send.SendPrePayMessage(check, 1);
		send.SendPrePayOverMessage(patient, 1);
		send.SendInviteMessage(1);
		send.SendIngMessage(1);
		send.SendWriteResultOverMessage(user, 1);
		send.SendPayMessage(user, 1);
		send.SendConsultationOverMessage( 1);
		
		
		return "index";
	}
	
	@RequestMapping("/doctor")
	public String doctor(Model model)
	{
		System.out.println("dsasdsad");
		Doctor doctor = ds.selectDoctorById(5);
		model.addAttribute("doctor", doctor);
		return "index";
	}
	@RequestMapping("/message")
	public String dosome(Model model)
	{
		System.out.println("dsadassda");
		
		int x = ms.selectCount();
		model.addAttribute("x",x);
		
		MedicalNotification me = ms.selectMessageById(1);
		model.addAttribute("me", me);	
		return "index";
	}
	
	@RequestMapping("/some")
	public String oo(Model model)
	{
		MedicalNotification me = ms.getMyNotReadingMessge(2);
		model.addAttribute("me", me);
		return "index";
		
	}
	@RequestMapping("/show")
	public String showall(Model model)
	{
		//Page page = new Page(1,ms.selectCount(),5);	
//		List<MedicalNotification> list = ms.findMessage(page);
//		model.addAttribute("list", list);
		return "index";
	}
	@RequestMapping("/delete")
	public String delete()
	{
		ms.deleteMessage(1);
		return "index";
	}
	
	@RequestMapping("/update")
	public String update()
	{
		MedicalNotification me = ms.selectMessageById(2);
		me.setReading(1);
		ms.updateMessage(me);
		return "index";
	}
	
	
	@RequestMapping("/add")
	public String add(HttpSession session,Model model)
	{
		Date time = new Date();		
		MedicalNotification me = new MedicalNotification();
		User user =us.selectUser(3);
		User ruser = us.selectUser(6);
		me.setInfor_type(InforType.Apply);
		me.setReceiver(ruser);
		me.setSender(user);
		me.setSend_time(time);
		me.setCon_id(2);
		
		ms.addMedicalNotification(me);
		model.addAttribute("newme", me);
		
		return "index";
	}
	
	
}
