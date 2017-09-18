package com.medical.service;


import java.util.List;



import org.apache.ibatis.annotations.Param;

import com.medical.model.MedicalConsultation;
import com.medical.model.ResultDict;
import com.medical.tool.Page;

/**
 * @Description: MedicalConsultation Service interface
 * @author: linai
 * @date： 日期：Dec 20, 2016 时间：5:33:13 PM
 * @version 1.0
 */
public interface MedicalConsultationService {

	//增加会诊表
	int addMedicalConsultation(MedicalConsultation medicalConsultation);
	
    //通过表id查询该表详情
	MedicalConsultation selectMedicalConsultationById(int id);
	
	//修改会诊表
	int updateMedicalConsultationById(MedicalConsultation medicalConsultation);
	
	//删除会诊表
	int deleteMedicalConsultationById(int id);
	
	//查询所有会诊表
	List<MedicalConsultation> queryAll();

	//根据医生选择对应的会诊信息
	List<MedicalConsultation> viewByDoctorIdAndChoose(int doctorId, int choose);
	
	//获取状态结果字典
	List<ResultDict> getResultDict();

	//获取总页数
	int selectCount();
	
	List<MedicalConsultation> queryAllByPage(Page page);

	public List<MedicalConsultation> selectMedicalConsultationsByPatientId(int patientId);
	
	/**
	 * 修改会诊表状态
	 * @param con_id  会诊表id
	 * @param con_status  会诊表状态
	 */
	public void updateConsultationStatus(int con_id,int con_status);
	
	public void updateEvaluate(int con_id,String evaluate);
	//summer
	/**
	 * 获得该病人的所有会诊信息总量
	 * @return
	 */
	public int selectMedicalConsultationCount(int userId);
	
	/**
	 * 分页查询会诊表
	 * @param page
	 * @param userId
	 * @return
	 */
	public List<MedicalConsultation> findMedicalConsultation(Page page,int userId);
}
