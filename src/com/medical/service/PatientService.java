package com.medical.service;

import java.util.List;

import com.medical.model.Doctor;
import com.medical.model.Patient;
import com.medical.model.User;
import com.medical.tool.Page;
import com.medical.tool.myenum.Blood;

/**
 * 操作数据库接口
 *
 */
public interface PatientService {
	
	/**
	 * 根据病人id查找病人
	 * @param id
	 * @return
	 */
	public Patient selectPatientById(int id);

	/**
	 * 添加病人
	 * @param user
	 * @param blood
	 * @param doctor_id
	 * @param account
	 */
	public void addPatient(User user,Blood blood,int doctor_id,double account);
	
	/**
	 * 修改病人个人信息
	 * @param user
	 */
	public void updatePatient(User user);
	
	/**
	 * 修改病人信息
	 * @param patient
	 */
	public void updatePatient(Patient patient);
	
	/**
	 * 根据病人id删除病人
	 * @param id
	 */
	public void deletePatientById(int id);
	
	/**
	 * 根据页信息查找当前页病人列表
	 * @param page
	 * @return
	 */
	public List<Patient> findPatient(Page page);
	
	/**
	 * 获得病人总数
	 * @return
	 */
    public int selectCount();
	
    /**
     *  根据病人ID查看他的主治医师
     * @param id
     * @return
     */
	public Doctor selectPatientDoctorById(int id);
	
}
