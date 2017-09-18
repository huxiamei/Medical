package com.medical.mapper;

import java.util.List;
import java.util.Map;

import com.medical.model.Department;


/**
 * ����
 * @author medical
 *
 */
public interface DepartmentMapper {

    public void addDepartment(Department department);
	
	public void deleteDepartment(int id);
	
	public void updateDepartment(Department department);
	
	public Department selectDepartmentById(int id);
	
	public List<Department> findDepartment(Map<String,Object> param);
	
	public int selectDepartmentCount();
}
