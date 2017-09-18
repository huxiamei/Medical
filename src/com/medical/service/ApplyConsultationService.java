package com.medical.service;

import java.util.Date;
import java.util.List;

import com.medical.model.Doctor;
import com.medical.model.Hospital;
import com.medical.model.MedicalConsultation;

public interface ApplyConsultationService {

	//根据病人id得到会诊表中的基本信息
	public MedicalConsultation getMedicalConsultation(int patientId);
	
	//创建case
	public int createCase(int patientId, String familySick, String beforeSick, String description, String filePath);
	
	//创建cure
	public int createCure(String plan, String reslut, String doctorName);
	
	//生成会诊表
	public int createMedicalConsultation(int doctorId, int caseId, int cureId, int status, Date date, Double amount);
	
	//得到医生姓名
	public String getDocNameById(int doctorId);
	
	public Doctor getDoctorByPatientId(int patientId);
	
	public int setConIdForCure(int cureId, int conId);
	
	public List<Hospital> getSelectHospital();
	
	public List<Doctor> selectDoctorByDepartmentId(int departId);
	
	public int createSelectDoctor(int departId, int doctorId, int ConId);
	

}
