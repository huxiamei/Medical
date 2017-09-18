package com.medical.mapper;


import java.util.List;
import java.util.Map;

import com.medical.model.MedicalCase;
import com.medical.model.MedicalConsultation;
import com.medical.model.Patient;
import com.medical.tool.Page;



public interface MedicalCaseMapper {

	public List<MedicalCase> selectCaseById(Map<String,Object> param);
	
	/**
	 * 根据病例编号查找病历表
	 * @param id
	 * @return
	 */
	public List<MedicalCase> selectCaseByCaseId(int id);
	
	/**
	 * 根据病人编号查找病历表
	 * @param patientId
	 * @return
	 */
	public List<MedicalCase> selectCaseByPatient_id(int patientId);
	
	/**
	 * 新增病例
	 * @param id
	 */
	public void addCaseWhenAddPatient(MedicalCase medCase);
	
	/**
	 * 根据病例id删除病例
	 * @param id
	 * @return
	 */
	public void deleteCase(int id);
	
	/**
	 * 修改病历表
	 * @param medCase
	 * @return
	 */
	public int updateCase(MedicalCase medCase);

	/**
	 * 查看所有病例
	 * @param param
	 * @return
	 */
	public List<MedicalCase> selectAllCase(Map<String,Object> param);
	
	/**
	 * 分页查询会诊表
	 * @param page
	 * @return
	 */
	public List<MedicalCase> findMedicalCase(Map<String,Object> param);
	
	/**
	 * 得到病例的总数
	 * @return
	 */
	public int selectCount();
}
