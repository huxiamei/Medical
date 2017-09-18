package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.HospitalDepartmentMapper;
import com.medical.model.Department;
import com.medical.model.Hospital;
import com.medical.model.HospitalDepartment;
import com.medical.service.HospitalDepartmentService;
import com.medical.tool.Page;

@Service("HospitalDepartmentService")
public class HospitalDepartmentServiceImpl implements HospitalDepartmentService {

	@Resource
	HospitalDepartmentMapper hospitaldepartmentmapper;
	
	public void addHospitalDepartment(HospitalDepartment department) {
		
		hospitaldepartmentmapper.addHospitalDepartment(department);

	}

	public void deleteHospitalDepartment(int id) {


		hospitaldepartmentmapper.deleteHospitalDepartment(id);

	}

	public void updateHospitalDepartment(HospitalDepartment department) {


		hospitaldepartmentmapper.updateHospitalDepartment(department);

	}

	public HospitalDepartment selectHospitalDepartmentById(int id) {

		return hospitaldepartmentmapper.selectHospitalDepartmentById(id);
	}

	public List<HospitalDepartment> findHospitalDepartment(Page page) {
		
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("number", page.getRowNo());
		params.put("start", (page.getPageNo()-1)*page.getRowNo());
		return hospitaldepartmentmapper.findHospitalDepartment(params);
	}

	public int selectHospitalDepartmentCount() {
	
		return hospitaldepartmentmapper.selectHospitalDepartmentCount();
	}

	public Hospital selectHospital(int id) {
		
		return hospitaldepartmentmapper.selectHospital(id);
	}

	public Department selectDepartment(int id) {
		
		return hospitaldepartmentmapper.selectDepartment(id);
	}

	public List<HospitalDepartment> selectDepartmentByHospitalId(int id) {
		
		return hospitaldepartmentmapper.selectDepartmentByHospitalId(id);
	}

	public List<HospitalDepartment> selectHospitalByDepartmentId(int id) {
		// TODO Auto-generated method stub
		return hospitaldepartmentmapper.selectHospitalByDepartmentId(id);
	}

	public boolean selectHDByHD(int hospitalId,int departmentId) {
		
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("hospital_id", hospitalId);
		params.put("department_id", departmentId);
	   if( hospitaldepartmentmapper.selectHDByHD(params)!=null)
	   {
		   return true;
	   }
	   else
		   return false;
	}

}
