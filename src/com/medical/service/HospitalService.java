package com.medical.service;

import java.util.List;

import com.medical.model.Hospital;
import com.medical.tool.Page;

public interface HospitalService {

	/**
	 * 增加医院信息
	 * @param hospital 医院
	 */
public void addHospital(Hospital hospital);
	 
    /**
     * 删除医院信息
     * @param id
     */
	public void deleteHospital(int id);
	
	/**
	 * 修改医院信息
	 * @param hospital
	 */
	public void updateHospital(Hospital hospital);
	
	/**
	 * 根据id得到医院信息
	 * @param id
	 * @return
	 */
	public Hospital selectHospitalById(int id);
	
	/**
	 * 得到当前页数的医院信息
	 * @param page
	 * @return
	 */
	public List<Hospital> findHospital(Page page);
	
	/**
	 * 得到医院总页数
	 * @return
	 */
	public int selectHospitalCount();

}
