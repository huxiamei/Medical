package com.medical.mapper;


import java.util.List;
import java.util.Map;

import com.medical.model.Doctor;
import com.medical.model.HospitalDepartment;
import com.medical.model.Patient;
import com.medical.model.User;

/**
 * doctor的mapper，与DoctorMapper.xml绑定
 *
 */
public interface DoctorMapper {
	
	/**
	 * 根据医生id查找到医生对象
	 * @param id 医生id
	 * @return 医生对象
	 */
	public Doctor selectDoctorById(int id);
	
	/**
	 * 得到某个医生的审核医生
	 * @param department_id  医生的科室
	 * @return
	 */
	public Doctor selectCheckDoctor(int department_id);
	
	
	/**
	 * 根据医院科室对应表id找到医院科室对应对象
	 * @param id
	 * @return
	 */
	public HospitalDepartment selectHospitalDepartment(int id);
	
	/**
	 * 根据id得到user对象
	 * @param id
	 * @return
	 */
	public User selectUser(int id);
	
	/**
	 * 增加医生对象
	 * @param doctor
	 */
	public void addDoctor(Doctor doctor);
	
	/**
	 * 增加user对象
	 * @param user
	 * @return
	 */
	public int addUserWhenAddDoctor(User user);
	
	/**
	 * 修改医生对象
	 * @param doctor
	 */
	public void updateDoctor(Doctor doctor);
	
	/**
	 * 修改user对象
	 * @param user
	 */
	public void updateUser(User user);
	
	
	/**
	 * 通过ID删除医生对象
	 * @param id
	 */
	public void deleteDoctorById(int id);
	
	/**
	 * 删除user对象
	 * @param id
	 */
	public void deleteUser(int id);
	
	/**
	 * 根据当前页和页面显示数得打医生集
	 * @param param  map对象，里面包括当前页面和页面显示数
	 * @return
	 */
	public List<Doctor> findDoctor(Map<String,Object> param);
	
	/**
	 * 得打医生总数
	 * @return
	 */
	public int selectCount();
	
	/**
	 * 根据医生id得到这个医生名下所有病人
	 * @param id 医生id
	 * @return  病人集合
	 */
	public List<Patient> selectDoctorPatientsById(int id);

	
	/**
	 * @author linai
	 * 根据department_id得到这个医生名下所有病人
	 * @param department_id
	 * @return  List<Doctor>
	 */
	public List<Doctor> selectDoctorByDepartmentId(int department_id);
}
