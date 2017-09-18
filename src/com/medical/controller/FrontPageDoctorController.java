package com.medical.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.Doctor;
import com.medical.model.Hospital;
import com.medical.model.MedicalConsultation;
import com.medical.model.Patient;
import com.medical.model.User;
import com.medical.service.DoctorService;
import com.medical.service.HospitalService;
import com.medical.service.MedicalConsultationService;
import com.medical.service.PatientService;
import com.medical.tool.JsonUtil;
import com.medical.tool.SendMessage;
import com.medical.tool.myenum.ConStatus;

@Controller
@RequestMapping("/FrontPageDoctor")
public class FrontPageDoctorController {
	
	@Autowired
	@Qualifier("DoctorService")
	DoctorService service;
	
	@Autowired
	@Qualifier("MedicalConsultationService")
	MedicalConsultationService cs;
	
	@Autowired
	@Qualifier("SendMessage")
	SendMessage send;
	
	@Autowired
	@Qualifier("HospitalService")
	HospitalService hs;
	
	@Autowired
	@Qualifier("PatientService")
	PatientService ps;
	
	/**
	 * 得到医生信息
	 * @param DoctorId  要得到的医生信息的ID
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getDoctor/{DoctorId}", method = {RequestMethod.GET})
	@ResponseBody
	public void getDoctor(@PathVariable int DoctorId,HttpServletResponse response) throws IOException
	{
		//调用service层函数查找到指定id的医生对象
		Doctor doctor = service.selectDoctorById(DoctorId);		
		
		//转化医生的出生日期，以字符串的形式传递到前台，防止前台无法识别日期对象
		Date date = doctor.getUser().getBirthdate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = sdf.format(date);
		
		JSONObject json = new JSONObject();
		json.put("doctor", doctor);  //将要修改的医生对象传递给前台，前台自动填充
		json.put("docbirthdate", startTime);  //医生的出生日期
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
	/**
	 * 修改医生信息
	 * @param doctor  要修改的医生的信息
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/update", method = {RequestMethod.POST})
	@ResponseBody
	public void update(HttpSession session,User user,HttpServletResponse response) throws IOException
	{	
		Doctor doctor = service.selectDoctorById(user.getId());  //得到要修改的信息
		user.setUser_password(doctor.getUser().getUser_password());
		user.setIdCard(doctor.getUser().getIdCard());
		user.setRole_id(doctor.getUser().getRole_id());
		
		JSONObject json = new JSONObject();			
		//调用service层的函数，修改数据库医生信息
		service.updateDoctor(user, -1);
		json.put("message", "修改成功");
		session.setAttribute("CurrentUser", user);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());   //返回提示信息	
		
	}
	
	/**
	 * 得到当前医生下所有病人
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getPatientList", method = {RequestMethod.GET})
	@ResponseBody
	public void getPatientList(HttpSession session,HttpServletResponse response) throws IOException
	{
		User user = (User) session.getAttribute("CurrentUser");  //当前登录用户
		List<Patient> patientList = service.selectDoctorPatientsById(user.getId());  //得到当前医生下所有病人
		
		List<String> BirthdayList = new ArrayList<String>();  //将出生日期转化为字符串传到前台
		for(int i=0;i<patientList.size();i++)
		{
			Date date = patientList.get(i).getUser().getBirthdate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startTime = sdf.format(date);
			BirthdayList.add(startTime);
		}
		
		JSONArray a = JsonUtil.toJSONArray(patientList);
		JSONObject json = new JSONObject();
		json.put("patientList", a);
		
		JSONArray b = JsonUtil.toJSONArray(BirthdayList);
		json.put("BirthdayList", b);		

		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}

	/**
	 * 审核医生同意会诊
	 * @param con_id 相关会诊id
	 * @param agree  是否同意会诊
	 * @param session  
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/CheckConsultation/{con_id}/{agree}", method = {RequestMethod.GET})
	@ResponseBody
	public void CheckConsultation(@PathVariable int con_id,@PathVariable int agree,HttpSession session,HttpServletResponse response) throws IOException
	{
		try
		{
			MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id);//会诊对象
			User user = (User) session.getAttribute("CurrentUser");
			if(agree==1)//同意会诊
			{				
				//修改会诊状态
				cs.updateConsultationStatus(consultation.getId(),ConStatus.CheckDoctorAgree.getCode());
				//自动扣除预付款
				double premoney = consultation.getAmount()*0.3;

				//修改医院病人金额
				Patient patient = consultation.getMedicalCase().getPatient();
				double pay = patient.getAccount() - premoney;
				patient.setAccount(pay);
				Doctor doctor = patient.getDoctor();
				double hosMoney = doctor.getHospitalDepartment().getHospital().getAccount() + premoney;
				Hospital hospital = doctor.getHospitalDepartment().getHospital();
				hospital.setAccount(hosMoney);
				hs.updateHospital(hospital);				
				ps.updatePatient(patient);	
				//修改会诊状态
				
				cs.updateConsultationStatus(consultation.getId(),ConStatus.PatientPrePaySuccess.getCode());
				//发送消息
				send.SendCheckAgreeOrNotMessage(user, con_id, true); //同意会诊信息
				send.SendPrePayMessage(user, con_id);  //提醒病人预缴费信息	
				send.SendPrePayOverMessage(user, con_id); //病人预付款成功
			}
			else
			{			
				//修改会诊状态				
				cs.updateConsultationStatus(consultation.getId(),ConStatus.CheckDoctorNotAgree.getCode());
				//发送消息
				send.SendCheckAgreeOrNotMessage(user, con_id, false);
			}
		} catch (Exception ex)
        {
			System.out.println("dsssssssssssss");
			ex.printStackTrace();
		}
		
	}
}
