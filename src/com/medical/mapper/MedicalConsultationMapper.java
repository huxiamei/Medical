package com.medical.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.medical.model.Department;
import com.medical.model.Doctor;
import com.medical.model.Hospital;
import com.medical.model.MedicalConsultation;
import com.medical.model.Patient;
import com.medical.tool.myenum.Blood;

/**
 * @Description: 会诊表 mapper
 * @author: linai
 * @date： 日期：Dec 20, 2016 时间：5:17:47 PM
 * @version 1.0
 */
public interface MedicalConsultationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalConsultation record);

    int insertSelective(MedicalConsultation record);

    MedicalConsultation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicalConsultation record);

    int updateByPrimaryKey(MedicalConsultation record);
    
    List<MedicalConsultation> queryAll();
    
    List<MedicalConsultation> selectByDepId(@Param("department_id")int department_id);
    
    List<MedicalConsultation> selectByDoctorId(int id);

	int selectCount();
    
	List<MedicalConsultation> queryAllByPage(Map<String, Object> params);
	/**
	 * 根据病人id得到他所有的会诊
	 * @param patientId
	 * @return
	 * 
	 * summer
	 */
	public List<MedicalConsultation> selectMedicalConsultationsByPatientId(int patientId);
	
	/**
	 * 修改会诊表状态
	 * @param con_id 要修改的会诊表
	 * @param con_status  状态
	 * 
	 * yana
	 */
	public void updateConsultationStatus(@Param("con_id")int con_id,@Param("con_status")int con_status);
	
	/**
	 * 修改评价
	 * @param con_id
	 * @param evaluate
	 */
	public void updateEvaluate(Map<String, Object> params);
	
	/**
	 * zoe
	 * @param id
	 * @return
	 */
	public Patient selectPatient(int id);

	int createCase(Map<String, Object> map);

	int createCure(Map<String, Object> map);

	public int createMedicalConsultation(Map<String, Object> map);

	@Select(value = "SELECT user_name FROM bas_user WHERE id = #{doctorId} ")
	String getDocNameById(int doctorId);
	

	public int setConIdForCure(Map<String, Integer> map);
	
	@Select(value = "SELECT id, hospital_name FROM bas_hospital")
	public List<Hospital> getSelectHospital();
	
	public int createSelectDoctor(Map<String, Integer> map);
	/**
	 * 根据会诊id查找到会诊表
	 * @param id 
	 * @return 
	 */
	public MedicalConsultation selectMedicalConsultationById(int medicalConsultation);

    
    /**
     * 获得该病人所有会诊信息
     * @return
     */
    public int selectMedicalConsultationCount(int userId);
    
    /**
	 * 得到会诊集合
	 * @param param
	 * @return
	 */
	public List<MedicalConsultation> findMedicalConsultation(Map<String,Object> param);

}