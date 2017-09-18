package com.medical.service;

import java.util.List;
import java.util.Map;

import com.medical.model.MedicalCase;
import com.medical.model.MedicalConsultation;
import com.medical.tool.Page;

public interface MedicalCaseService {

	public List<MedicalCase> selectCaseByCaseId(int medicalCaseId);

	public List<MedicalCase> selectCaseById(int medicalCaseId,int patientId);
	
	public List<MedicalCase> selectCaseByPatient_id(int patientId);
	
	/**
	 * 修改病例表
	 * @param medicalConsultation
	 * @return
	 */
	public int updateCase(MedicalCase medicalCase);
	
	
	public List<MedicalCase> selectAllCase(Map<String,Object> param);
	
	/**
	 * 分页查询病例表
	 * @param page
	 * @return
	 */
	public List<MedicalCase> findMedicalCase(Page page);
	
	/**
	 * 得到病例的总数
	 * @return
	 */
	public int selectCount();
	
	public void deleteCaseById(int caseId);
}
