package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.DepartmentMapper;
import com.medical.model.Department;
import com.medical.service.DepartmentService;
import com.medical.tool.Page;

@Service("DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	DepartmentMapper departmentmapper;
	
	public void addDepartment(Department department) {
		
		departmentmapper.addDepartment(department);

	}

	public void deleteDepartment(int id) {
		departmentmapper.deleteDepartment(id);

	}

	public void updateDepartment(Department department) {
		departmentmapper.updateDepartment(department);

	}

	public Department selectDepartmentById(int id) {
		
		return departmentmapper.selectDepartmentById(id);
	}

	public List<Department> findDepartment(Page page) {
		
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("number", page.getRowNo());
		params.put("start", (page.getPageNo()-1)*page.getRowNo());
		return departmentmapper.findDepartment(params);
	}

	public int selectDepartmentCount() {
		
		return departmentmapper.selectDepartmentCount();
	}

}
