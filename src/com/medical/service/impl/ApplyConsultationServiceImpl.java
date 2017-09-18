package com.medical.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.mapper.MedicalConsultationMapper;
import com.medical.model.Doctor;
import com.medical.model.Hospital;
import com.medical.model.MedicalCase;
import com.medical.model.MedicalConsultation;
import com.medical.model.MedicalCure;
import com.medical.model.Patient;
import com.medical.service.ApplyConsultationService;

@Service("ApplyConsultationServiceImpl")
public class ApplyConsultationServiceImpl implements ApplyConsultationService{

	@Autowired
	private MedicalConsultationMapper medicalConsultationMapper;

	public MedicalConsultation getMedicalConsultation(int patientId) {
		//创建一张会诊表
		MedicalConsultation medicalConsultation = new MedicalConsultation();
		//创建一个病例
		MedicalCase medicalCase = new MedicalCase();
		//创建一个治疗方法
		MedicalCure medicalCure = new MedicalCure();
		
		//根据病人id查找病人基本信息(信息不够全，全部查出来)
		Patient patient = medicalConsultationMapper.selectPatient(patientId);
		Doctor doctor =patient.getDoctor();
		
		medicalCase.setPatient(patient);
		medicalConsultation.setMedicalCase(medicalCase);
		medicalConsultation.setDoctor(doctor);
		medicalConsultation.setCure(medicalCure);
		
		return medicalConsultation;
	}

	public int createCase(int patientId, String familySick, String beforeSick,
			String description, String filePath) {
		Map<String, Object> caseMap = new HashMap<String, Object>();
		caseMap.put("patientId", patientId);
		caseMap.put("familySick", familySick);
		caseMap.put("beforeSick", beforeSick);
		caseMap.put("description", description);
		caseMap.put("filePath", filePath);
		caseMap.put("id", null);
		medicalConsultationMapper.createCase(caseMap);
		Object id = caseMap.get("id");
		int caseId = Integer.parseInt(String.valueOf(id));
		return caseId;
	}
	
	public int createCure(String plan, String result, String doctorName) {
		Map<String, Object> cureMap = new HashMap<String, Object>();
		cureMap.put("plan", plan);
		cureMap.put("result", result);
		cureMap.put("doctorName", doctorName);
		cureMap.put("id", null);
		medicalConsultationMapper.createCure(cureMap);
		Object id = cureMap.get("id");
		int cureId = Integer.parseInt(String.valueOf(id));
		return cureId;
	}

	public int createMedicalConsultation(int doctorId, int caseId, int cureId, int status, Date date, Double amount) {
		Map<String, Object> medicalConsultationMap = new HashMap<String, Object>();
		medicalConsultationMap.put("doctorId", doctorId);
		medicalConsultationMap.put("caseId", caseId);
		medicalConsultationMap.put("cureId", cureId);
		medicalConsultationMap.put("status", status);
		medicalConsultationMap.put("date", date);
		medicalConsultationMap.put("amount", amount);
		medicalConsultationMap.put("id", null);
		medicalConsultationMapper.createMedicalConsultation(medicalConsultationMap);
		Object id = medicalConsultationMap.get("id");
		int consultationId = Integer.parseInt(String.valueOf(id));
		return consultationId;
	}

	public String getDocNameById(int doctorId) {
		String doctorName = medicalConsultationMapper.getDocNameById(doctorId);
		return doctorName;
	}

	public Doctor getDoctorByPatientId(int patientId) {
		Patient patient = medicalConsultationMapper.selectPatient(patientId);
		Doctor doctor =patient.getDoctor();
		return doctor;
	}

	public int setConIdForCure(int cureId, int conId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("cureId", cureId);
		map.put("conId", conId);
		int n = medicalConsultationMapper.setConIdForCure(map);
		return n;
	}

	public List<Hospital> getSelectHospital() {
		List<Hospital> hospitals = medicalConsultationMapper.getSelectHospital();
		return hospitals;
	}

	public List<Doctor> selectDoctorByDepartmentId(int departId) {
		return null;
	}

	public int createSelectDoctor(int departId, int doctorId, int conId) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("departId", departId);
		map.put("doctorId", doctorId);
		map.put("conId", conId);
		map.put("id", null);
		int n = medicalConsultationMapper.createSelectDoctor(map);
		return n;
	}
}
