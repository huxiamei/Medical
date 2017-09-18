package com.medical.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.medical.model.Doctor;
import com.medical.model.Hospital;
import com.medical.model.MedicalConsultation;
import com.medical.service.ApplyConsultationService;
import com.medical.service.DoctorService;
import com.medical.service.MedicalConsultationService;
import com.medical.tool.MsgDTO;
import com.medical.tool.SendMessage;

@Controller
@RequestMapping("/applyConsultation")
public class ApplyConsultationController {
	
	@Autowired
	@Qualifier("ApplyConsultationServiceImpl")
	public ApplyConsultationService applyConsultationService;
	
	//Service对象，可以通过它对数据库进行访问
	@Autowired
	@Qualifier("DoctorService")
	DoctorService service;
	
	@Autowired
	@Qualifier("SendMessage")
	SendMessage send;
	
	@Autowired
	@Qualifier("MedicalConsultationService")
	MedicalConsultationService cs;
	
	@RequestMapping("/testFileuUpload")
	public String testFileuUpload(@RequestParam("desc") String desc,
			@RequestParam("file") MultipartFile file) {
		System.out.println("desc: "+ desc);
		System.out.println("file: "+ file.getOriginalFilename());
		return "view/doctor/success";
	}
	
	@RequestMapping(value="/showConsultationTable/{patient_id}", method=RequestMethod.GET)
	public String showConsultationTable(@PathVariable(value="patient_id") int patientId, Map<String, Object> map) {
		//创建一张会诊表
		MedicalConsultation medicalConsultation = applyConsultationService.getMedicalConsultation(patientId);
		//获得选择医院
		List<Hospital> hospitalList = applyConsultationService.getSelectHospital();
		map.put("medicalConsultation", medicalConsultation);
		map.put("hospitalList", hospitalList);
		return "frontPage/doctor_applyConsultation";
	}
	
//	@RequestMapping(value="/getConsultationTable")
//	public String getConsultationTable(@ModelAttribute(value="medicalConsultation") MedicalConsultation medicalConsultation) {
//		//病人id
//		int patientId = medicalConsultation.getMedicalCase().getPatient().getUser().getId();
//		//医生id
//		int doctorId = medicalConsultation.getDoctor().getUser().getId();
//		//医生姓名
//		String doctorName = applyConsultationService.getDocNameById(doctorId);
////		String doctorName = medicalConsultation.getDoctor().getUser_name();
//		//家族病史
//		String familySick = medicalConsultation.getMedicalCase().getFamily_sick();
//		//家族病史
//		String beforeSick = medicalConsultation.getMedicalCase().getBefore_sick();
//		//病情描述
//		String description = medicalConsultation.getMedicalCase().getDescription();
//		String filePath = "";
//		String plan = "会诊方案";
//		String result = "会诊结果";
//		//创建case，得到caseId
//		int caseId = applyConsultationService.createCase(patientId, familySick, beforeSick, description, filePath);
//		int cureId = applyConsultationService.createCure(plan, result, doctorName);
//		java.util.Date dt = new java.util.Date();
//		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String currentTime = sdf.format(dt);
//		applyConsultationService.createMedicalConsultation(doctorId, caseId, cureId, 0, currentTime);
//		return "view/doctor/success";
//	}
	
	 @RequestMapping(value = "/getConsultationTable", method = { RequestMethod.POST })
	 @ResponseBody
	 @POST
	 @Consumes({ "application/json", "application/xml" })
	public String getConsultationTable(@RequestBody MedicalConsultation medicalConsultation) {
		Doctor doctor = applyConsultationService.getDoctorByPatientId(medicalConsultation.getMedicalCase().getPatient().getUser().getId());
		//治疗方案
		
		String filePath = medicalConsultation.getMedicalCase().getFile_path();
		String plan = "";
		String result = "";
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		Date date = null;
		try {
			date = sdf.parse(medicalConsultation.getStringDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//创建病例
		int caseId = applyConsultationService.createCase(medicalConsultation.getMedicalCase().getPatient().getUser().getId(), medicalConsultation.getMedicalCase().getFamily_sick(), medicalConsultation.getMedicalCase().getBefore_sick(), medicalConsultation.getMedicalCase().getDescription(), filePath);
		//创建治疗方案
		int cureId = applyConsultationService.createCure(plan, result, doctor.getUser().getUser_name());
		//创建会诊
		int conId = applyConsultationService.createMedicalConsultation(doctor.getUser().getId(), caseId, cureId, 0, date, Double.parseDouble(doctor.getHospitalDepartment().getHospital().getChargeStandard()));
		applyConsultationService.setConIdForCure(cureId, conId);
		
		//得到选择的医生ID
		String doctorIdString = medicalConsultation.getSelectDoctorId();
		String arr[] = doctorIdString.split("/");
		for(int i = 0; i<arr.length; i++){
			Doctor doctorSelect = service.selectDoctorById(Integer.parseInt(arr[i]));
			applyConsultationService.createSelectDoctor(doctorSelect.getHospitalDepartment().getId(), Integer.parseInt(arr[i]), conId);
		}
		
		//发送消息
		send.SendApplyConsultationMessage(doctor.getUser(), conId);
		return "";
	}
	 
	 @GET
	 @RequestMapping("/getDoctor")
	 @ResponseBody
	public MsgDTO getDoctor(@QueryParam("doctor") Doctor doctor) {
		 MsgDTO msgDTO = new MsgDTO();
		 List<Doctor> doctorList = service.selectDoctorByDepartmentId(Integer.parseInt(doctor.getDepartment()));
		 msgDTO.setData(doctorList);
		return msgDTO;
	}

}
