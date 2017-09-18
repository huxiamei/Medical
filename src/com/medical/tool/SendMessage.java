package com.medical.tool;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.medical.model.MedicalConsultation;
import com.medical.model.MedicalNotification;
import com.medical.model.MedicalSelectDepartment;
import com.medical.model.User;
import com.medical.service.DoctorService;
import com.medical.service.MedicalConsultationService;
import com.medical.service.MedicalNotificationService;
import com.medical.tool.myenum.InforType;

/**
 * 工具类
 * 不同阶段发送消息
 * @author lyan
 *
 */

@Service("SendMessage")
public class SendMessage {

	@Autowired
	@Qualifier("MedicalNotificationService")
	MedicalNotificationService ms;
	
	@Autowired
	@Qualifier("MedicalConsultationService")
	MedicalConsultationService cs;
	
	@Autowired
	@Qualifier("DoctorService")
	DoctorService ds;
	
	/**
	 * 主治医生申请会诊时发送消息
	 * @param sendUser  当前登录用户，一般为主治医生
	 * @param patient 病人
	 * @param con_id  会诊ID
	 */
	public void SendApplyConsultationMessage(User sendUser,int con_id)
	{
		System.out.println(con_id);
		MedicalNotification message = new MedicalNotification();
		message.setSender(sendUser);  //设置发送方为当前登录用户 --主治医师
		MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); 
		message.setReceiver(consultation.getMedicalCase().getPatient().getUser());  //接受方为病人
		Date sendtime = new Date();		 //设置发送时间
		message.setInfor_type(InforType.Apply);  //消息状态为主治医生申请会诊
		message.setSend_time(sendtime);
		message.setCon_id(con_id);
		ms.addMedicalNotification(message);
	}
	
	/**
	 * 病人是否同意会诊的反馈
	 * @param sendUser  当前登录用户，一般为病人
	 * @param con_id 涉及到的会诊id
	 * @param agree  是否同意会诊
	 */
	public void SendAgreeOrNotMessage(User sendUser,int con_id,boolean agree)
	{
		MedicalNotification message = new MedicalNotification();
		
		message.setSender(sendUser);  //设置发送方为当前登录用户--病人
		//根据会诊id得到会诊信息
		MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); 
		
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);
		if(agree)
		{
			message.setInfor_type(InforType.PatientAgree);  //消息状态为病人同意会诊
			//发送给审核医生的消息
			int deaprtment = consultation.getDoctor().getHospitalDepartment().getId();			
			User r = ds.selectCheckDoctor(deaprtment).getUser();
			message.setReceiver(r); //设置接受方为审核医生，科室id相同，role_id为1
			ms.addMedicalNotification(message);
		}
		else
		{
			message.setInfor_type(InforType.PatientNotAgree);  //消息状态为病人不同意会诊
		}	
		
		//发送给主治医生的消息
		message.setReceiver(consultation.getDoctor().getUser());  //接受方为主治医生
		ms.addMedicalNotification(message);
		
	}
	
	/**
	 * 审核医生是否同意会诊的反馈
	 * @param sendUser  当前登录用户，一般为审核医生
	 * @param con_id 涉及到的会诊id
	 * @param agree 是否同意会诊
	 */
	public void SendCheckAgreeOrNotMessage(User sendUser,int con_id,boolean agree)
	{
		MedicalNotification message = new MedicalNotification();
		message.setSender(sendUser);  //设置发送方为当前登录用户--审核医生
		
		//根据会诊id得到会诊信息
		MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); 
		message.setReceiver(consultation.getDoctor().getUser());  //接受方为主治医生
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		if(agree)
		{
			message.setInfor_type(InforType.CheckDoctorAgree);  //消息状态为病人同意会诊
		}
		else
		{
			message.setInfor_type(InforType.CheckDoctorNotAgree);  //消息状态为病人同意会诊
		}	
		message.setCon_id(con_id);
		//发送给主治医生的消息
		ms.addMedicalNotification(message);
		
		//发送给病人的消息
		message.setReceiver(consultation.getMedicalCase().getPatient().getUser());
		ms.addMedicalNotification(message);	
		
	}
	
	/**
	 * 病人预缴费通知消息
	 * @param sendUser  发送方，一般为审核医生
	 * @param con_id 涉及到的会诊id
	 */
	public void SendPrePayMessage(User sendUser,int con_id)
	{
		MedicalNotification message = new MedicalNotification();
		message.setSender(sendUser);  //设置发送方为当前登录用户--审核医生	
		
		MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); //根据会诊id得到会诊信息
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);  //会诊id
		message.setInfor_type(InforType.PreviousPay); 
		message.setReceiver(consultation.getMedicalCase().getPatient().getUser());  //接受方为病人
		ms.addMedicalNotification(message);	  
	}
	
	/**
	 * 病人预缴费完成通知消息
	 * @param sendUser  发送方，一般为登录用户
	 * @param con_id 涉及到的会诊id
	 */
	public void SendPrePayOverMessage(User sendUser,int con_id)
	{
		MedicalNotification message = new MedicalNotification();
			
		MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); //根据会诊id得到会诊信息
		message.setSender(consultation.getMedicalCase().getPatient().getUser());  //设置发送方为当前登录用户--病人	
		
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);  //会诊id
		message.setInfor_type(InforType.PreviousPayOver);
		message.setReceiver(consultation.getDoctor().getUser());  //接受方为主治医生	
		ms.addMedicalNotification(message);	 
		
		int deaprtment = consultation.getDoctor().getHospitalDepartment().getId();
		User r = ds.selectCheckDoctor(deaprtment).getUser();
		message.setReceiver(r); //设置接受方为审核医生
		ms.addMedicalNotification(message);	 
	}
	
	/**
	 * 发送消息给参与会诊的医生
	 * @param con_id 涉及到的会诊id
	 */
	public void SendInviteMessage(int con_id)
	{
		MedicalNotification message = new MedicalNotification();		
		
        MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); //根据会诊id得到会诊信息          
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);  //会诊id
		message.setInfor_type(InforType.InviteJoinConsulation);
		
		int deaprtment = consultation.getDoctor().getHospitalDepartment().getId();
		User r = ds.selectCheckDoctor(deaprtment).getUser();
		message.setSender(r); //设置发送方为审核医生
		
		List<MedicalSelectDepartment> list = consultation.getSelect_department();
		for(int i=0;i<list.size();i++)
		{
			User receiver = list.get(i).getDoctor().getUser();
			message.setReceiver(receiver);
			ms.addMedicalNotification(message);		
		}
		
	}
	
	/**
	 * 会诊进行中
	 * @param con_id
	 */
	public void SendIngMessage(int con_id)
	{
		MedicalNotification message = new MedicalNotification();
	    MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); //根据会诊id得到会诊信息          
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);  //会诊id
		message.setInfor_type(InforType.StartConsulation);
		
		int deaprtment = consultation.getDoctor().getHospitalDepartment().getId();
		User r = ds.selectCheckDoctor(deaprtment).getUser();
		message.setSender(r); //设置发送方为审核医生
		
		message.setReceiver(consultation.getMedicalCase().getPatient().getUser());
		ms.addMedicalNotification(message);	
	}
	
	
	/**
	 * 会诊结果填写完毕
	 * @param sendUser
	 * @param con_id
	 */
	public void SendWriteResultOverMessage(User sendUser,int con_id)
	{
		MedicalNotification message = new MedicalNotification();
		message.setSender(sendUser);  //设置发送方为当前登录用户--主治医生
	    MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); //根据会诊id得到会诊信息          
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);  //会诊id
		message.setInfor_type(InforType.ConsulationResultWrite);
		
		//设置接受方为审核医生
		int deaprtment = consultation.getDoctor().getHospitalDepartment().getId();
		User r = ds.selectCheckDoctor(deaprtment).getUser();
		message.setReceiver(r); 
		ms.addMedicalNotification(message);	 
		
		//设置接受方为病人
		message.setReceiver(consultation.getMedicalCase().getPatient().getUser());
		ms.addMedicalNotification(message);
		
		//设置接受方为参会医生
		List<MedicalSelectDepartment> list = consultation.getSelect_department();
		for(int i=0;i<list.size();i++)
		{
			User receiver = list.get(i).getDoctor().getUser();
			message.setReceiver(receiver);
			ms.addMedicalNotification(message);		
		}
		
	}
	
	/**
	 * 缴费通知
	 * @param sendUser
	 * @param con_id
	 */
	public void SendPayMessage(User sendUser,int con_id)
	{
		MedicalNotification message = new MedicalNotification();
		message.setSender(sendUser);  //设置发送方为当前登录用户--主治医生
	    MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); //根据会诊id得到会诊信息          
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);  //会诊id
		message.setInfor_type(InforType.Pay);
		
		//设置接受方为病人
		message.setReceiver(consultation.getMedicalCase().getPatient().getUser());
		ms.addMedicalNotification(message);
	}
	
	/**
	 * 发送会诊完毕
	 * @param sendUser
	 * @param con_id
	 */
	public void SendConsultationOverMessage(int con_id)
	{
		MedicalNotification message = new MedicalNotification();
	    MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); //根据会诊id得到会诊信息          
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);  //会诊id
		message.setInfor_type(InforType.ConsulationOver);
		
		message.setSender(consultation.getDoctor().getUser());  //设置发送方为当前主治医生
		
		//设置接受方为审核医生
		int deaprtment = consultation.getDoctor().getHospitalDepartment().getId();
		User r = ds.selectCheckDoctor(deaprtment).getUser();
		message.setReceiver(r); 
		ms.addMedicalNotification(message);	 
		
		//设置接受方为病人
		message.setReceiver(consultation.getMedicalCase().getPatient().getUser());
		ms.addMedicalNotification(message);
		
		//设置接受方为参会医生
		List<MedicalSelectDepartment> list = consultation.getSelect_department();
		for(int i=0;i<list.size();i++)
		{
			User receiver = list.get(i).getDoctor().getUser();
			message.setReceiver(receiver);
			ms.addMedicalNotification(message);		
		}
	}
	
	/**
	 * 发送填写会诊评价完毕
	 * @param con_id 会诊id
	 */
	public void SendConsultationEvaluate(int con_id)
	{
		MedicalNotification message = new MedicalNotification();
	    MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id); //根据会诊id得到会诊信息          
		Date sendtime = new Date();		 //设置发送时间
		message.setSend_time(sendtime);
		message.setCon_id(con_id);  //会诊id
		message.setInfor_type(InforType.WriteEvaluateOver);
		
		message.setSender(consultation.getMedicalCase().getPatient().getUser());  //设置发送方为当前主治医生
		
		//设置接受方为审核医生
		int deaprtment = consultation.getDoctor().getHospitalDepartment().getId();
		User r = ds.selectCheckDoctor(deaprtment).getUser();
		message.setReceiver(r); 
		ms.addMedicalNotification(message);	 
		
		//设置接受方为主治医生
		message.setReceiver(consultation.getDoctor().getUser());
		ms.addMedicalNotification(message);
		
		//设置接受方为参会医生
		List<MedicalSelectDepartment> list = consultation.getSelect_department();
		for(int i=0;i<list.size();i++)
		{
			User receiver = list.get(i).getDoctor().getUser();
			message.setReceiver(receiver);
			ms.addMedicalNotification(message);		
		}
	}
}
