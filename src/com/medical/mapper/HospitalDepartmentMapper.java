package com.medical.mapper;

import java.util.List;
import java.util.Map;

import com.medical.model.Department;
import com.medical.model.Hospital;
import com.medical.model.HospitalDepartment;

/**
 * ҽԺ���Ҳ���
 * @author Administrator
 *
 */
public interface HospitalDepartmentMapper {  

    public void addHospitalDepartment(HospitalDepartment department);
	
	public void deleteHospitalDepartment(int id);
	
	public void updateHospitalDepartment(HospitalDepartment department);
	
	public HospitalDepartment selectHospitalDepartmentById(int id);
	
	public List<HospitalDepartment> findHospitalDepartment(Map<String,Object> param);
	
	public int selectHospitalDepartmentCount();  
	
	public Hospital selectHospital(int id);
	
	public Department selectDepartment(int id);
	
	public List<HospitalDepartment> selectDepartmentByHospitalId(int id);
	
	public List<HospitalDepartment> selectHospitalByDepartmentId(int id);
	
	public HospitalDepartment selectHDByHD(Map<String,Object> param);
}
