package com.medical.service;

import java.util.List;

import com.medical.model.Doctor;
import com.medical.model.Patient;
import com.medical.model.User;
import com.medical.tool.Page;

/**
 * 操作数据库接口
 *
 */
public interface DoctorService {

	/**
	 * 根据id查找医生
	 * @param id
	 * @return
	 */
	public Doctor selectDoctorById(int id);
	
	/**
	 * 得到某个医生的审核医生
	 * @param department_id  医生的科室
	 * @return
	 */
	public Doctor selectCheckDoctor(int department_id);
	
	/**
	 * 增加医生
	 * @param user 医生基本信息
	 * @param departmentId  医生所在医院及部门
	 */
	public void addDoctor(User user,int departmentId);
	
	/**
	 * 修改医生信息
	 * @param user 医生基本信息
	 * @param departmentId 医生所在医院及部门
	 */
	public void updateDoctor(User user,int departmentId);
	
	/**
	 * 根据id删除医生信息
	 * @param id
	 */
	public void deleteDoctorById(int id);
	
	/**
	 * 根据页信息查找当前页医生信息集
	 * @param page 页
	 * @return
	 */
	public List<Doctor> findDoctor(Page page);
	
	/**
	 * 得到医生总数
	 * @return
	 */
    public int selectCount();
	
    /**
     * 根据医生id得到该医生名下所有病人。
     * @param id
     * @return
     */
	public List<Patient> selectDoctorPatientsById(int id);
	
	

	/**
	 * 根据departmentId得到所有医生
	 * @param departmentId
	 * @return List<Doctor>
	 */
	public List<Doctor> selectDoctorByDepartmentId(int departmentId);
}
