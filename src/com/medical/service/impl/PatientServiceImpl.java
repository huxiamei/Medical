package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.PatientMapper;
import com.medical.model.Doctor;
import com.medical.model.Patient;
import com.medical.model.User;
import com.medical.service.PatientService;
import com.medical.tool.Page;
import com.medical.tool.myenum.Blood;

/**
 * 
 * PatientService具体实现类
 *
 */
@Service("PatientService")
public class PatientServiceImpl implements PatientService {

	@Resource
	PatientMapper mapper;
	
	public Patient selectPatientById(int id) {
		
		return mapper.selectPatientById(id);
	}

	public void addPatient(User user,Blood blood,int doctor_id,double account) {
		//添加用户
		mapper.addUserWhenAddPatient(user); 
		
		Patient patient = new Patient();
		//根据医生id获得医生实体对象
		Doctor doctor=mapper.selectDoctorById(doctor_id);
		
		patient.setUser(user);
		patient.setBlood(blood);
		patient.setDoctor(doctor);
		patient.setAccount(account);
		//添加病人
		mapper.addPatient(patient);
		
	}

	public void updatePatient(User user){
		mapper.updateUser(user);
	}
	
	public void updatePatient(Patient patient) {
		mapper.updateUser(patient.getUser());
		mapper.updatePatient(patient);
	}


	public void deletePatientById(int id) {
		mapper.deletePatientById(id);
		mapper.deleteUser(id);
		
	}


	public List<Patient> findPatient(Page page) {

		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("number", page.getRowNo());
		params.put("start", (page.getPageNo()-1)*page.getRowNo());
		return mapper.findPatient(params);
	}


	public int selectCount() {
		
		return mapper.selectCount();
	}


	public Doctor selectPatientDoctorById(int id) {

		return mapper.selectPatientDoctorById(id);
	}
}
