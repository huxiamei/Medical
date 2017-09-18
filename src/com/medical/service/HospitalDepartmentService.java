package com.medical.service;


import java.util.List;

import com.medical.model.Department;
import com.medical.model.Hospital;
import com.medical.model.HospitalDepartment;
import com.medical.tool.Page;

public interface HospitalDepartmentService {

	/**
	 * 增加医院科室对应信息
	 * @param department  医院科室对应信息
	 */
public void addHospitalDepartment(HospitalDepartment department);
	
	/**
	 * 根据id删除信息
	 * @param id 
	 */
	public void deleteHospitalDepartment(int id);
	
	/**
	 * 修改医院科室对应信息
	 * @param department 医院科室对应信息
	 */
	public void updateHospitalDepartment(HospitalDepartment department);
	
	/**
	 * 得到医院科室对应信息
	 * @param id 
	 * @return
	 */
	public HospitalDepartment selectHospitalDepartmentById(int id);
	
	/**
	 * 得到当前页医院科室对应信息集
	 * @param page 页
	 * @return
	 */
	public List<HospitalDepartment> findHospitalDepartment(Page page);
	
	/**
	 * 得到医院科室对应信息总数
	 * @return
	 */
	public int selectHospitalDepartmentCount();  
	
	/**
	 * 根据id得到医院
	 * @param id
	 * @return
	 */
	public Hospital selectHospital(int id);
	
	/**
	 * 根据id得到科室
	 * @param id
	 * @return
	 */
	public Department selectDepartment(int id);
	
	/**
	 * 得到某个医院id下所有科室
	 * @param id 医院id
	 * @return
	 */
    public List<HospitalDepartment> selectDepartmentByHospitalId(int id);
	
    /**
     * 得到拥有某个科室的所有医院
     * @param id 科室id
     * @return
     */
	public List<HospitalDepartment> selectHospitalByDepartmentId(int id);
	
	/**
	 * 判断某个医院是否已经有了某个科室，避免重复添加相同的信息
	 * @param hosId 医院id
	 * @param depId 科室id
	 * @return
	 */
	public boolean selectHDByHD(int hosId,int depId);
}
