package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.MedicalCaseMapper;
import com.medical.model.Doctor;
import com.medical.model.HospitalDepartment;
import com.medical.model.MedicalCase;
import com.medical.model.MedicalConsultation;
import com.medical.model.Patient;
import com.medical.model.User;
import com.medical.service.MedicalCaseService;
import com.medical.tool.Page;

/**
 * medicalService具体实现
 *
 */
@Service("MedicalCaseService")
public class MedicalCaseServiceImpl implements MedicalCaseService {

	@Resource
	MedicalCaseMapper mapper;
	
	public List<MedicalCase> selectCaseById(int medicalCaseId,int patientId){
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("medicalCaseId",medicalCaseId);
		params.put("patientId", patientId);
		return mapper.selectCaseById(params);
		
	}
	
	public List<MedicalCase> selectCaseByCaseId(int medicalCaseId){
		return mapper.selectCaseByCaseId(medicalCaseId);
		
	}

	public List<MedicalCase> selectCaseByPatient_id(int patientId){
		return mapper.selectCaseByPatient_id(patientId);
	}
	
	/**
	 * 修改病例表
	 * @param medicalConsultation
	 * @return
	 */
	public int updateCase(MedicalCase medicalCase){
		return mapper.updateCase(medicalCase);
	}
	
	
	public List<MedicalCase> selectAllCase(Map<String,Object> param){
		return mapper.selectAllCase(param);
	}
	
	/**
	 * 分页查询病例表
	 * @param page
	 * @return
	 */
	public List<MedicalCase> findMedicalCase(Page page){
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("number", page.getRowNo());
		params.put("start", (page.getPageNo()-1)*page.getRowNo());
		return mapper.findMedicalCase(params);
	}

	/**
	 * 得到病例的总数
	 */
	public int selectCount() {
		return mapper.selectCount();
	}

	public void deleteCaseById(int caseId) {
		mapper.deleteCase(caseId);
	}
}
