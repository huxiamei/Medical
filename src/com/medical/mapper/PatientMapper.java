package com.medical.mapper;


import java.util.List;
import java.util.Map;

import com.medical.model.Doctor;
import com.medical.model.HospitalDepartment;
import com.medical.model.Patient;
import com.medical.model.User;
public interface PatientMapper {
	
	/**
	 * 根据病人id查找病人表
	 * @param id
	 * @return
	 */
	public Patient selectPatientById(int id);

	/**
	 * 根据医生id查找医生表
	 * @param id
	 * @return
	 */
	public Doctor selectDoctorById(int id);
	
	/**
	 * 根据用户id查找用户表
	 * @param id
	 * @return
	 */
	public User selectUser(int id);
	
	/**
	 * 根据医院科室对应表id查找医院科室表
	 * @param id
	 * @return
	 */
	public HospitalDepartment selectHospitalDepartmentById(int id);
	
	/**
	 * 添加病人
	 * @param patient
	 */
	public void addPatient(Patient patient);
	
	/**
	 * 添加病人时同时添加用户
	 * @param user
	 * @return
	 */
	public int addUserWhenAddPatient(User user);
	
	/**
	 * 更新病人表
	 * @param patient
	 */
	public void updatePatient(Patient patient);
	
	/**
	 * 更新用户表
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 根据id删除病人
	 * @param id
	 */
	public void deletePatientById(int id);
	
	/**
	 * 根据id删除用户
	 * @param id
	 */
	public void deleteUser(int id);
	
	/**
	 * 根据当前页面和页面显示数查看病人列表
	 * @param param 
	 * @return
	 */
	public List<Patient> findPatient(Map<String,Object> param);
	
	/**
	 * 查询病人总数
	 * @return
	 */
	public int selectCount();
	
	/**
	 * 根据病人ID查看他的主治医师 
	 * @param id
	 * @return
	 */
	public Doctor selectPatientDoctorById(int id);

}
