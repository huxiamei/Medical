package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.DoctorMapper;
import com.medical.model.Doctor;
import com.medical.model.HospitalDepartment;
import com.medical.model.Patient;
import com.medical.model.User;
import com.medical.service.DoctorService;
import com.medical.tool.Page;

/**
 * doctorService具体实现
 *
 */
@Service("DoctorService")
public class DoctorServiceImpl implements DoctorService {

	@Resource
	DoctorMapper mapper;
	
	
	
	public Doctor selectDoctorById(int id) {
		
		return mapper.selectDoctorById(id);
	}


	
	public void addDoctor(User user, int departmentId) {
		
		Doctor doctor = new Doctor();

		HospitalDepartment hd = new HospitalDepartment();
		hd.setId(departmentId);	
		
	    mapper.addUserWhenAddDoctor(user); //增加用户信息 	
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+user.getId());  //自动填充了id
		doctor.setUser(user);
		doctor.setHospitalDepartment(hd);
		
		mapper.addDoctor(doctor);
		
	}


	
	public void updateDoctor(User user, int departmentId) {
		
		Doctor doctor = new Doctor();
		doctor.setUser(user);
		if(departmentId>= 0)
		{
			HospitalDepartment hd = new HospitalDepartment();
			hd.setId(departmentId);
			doctor.setHospitalDepartment(hd);			
			mapper.updateDoctor(doctor);
		}
		
		mapper.updateUser(user);
		
	}


	public void deleteDoctorById(int id) {

		mapper.deleteUser(id);
		mapper.deleteDoctorById(id);
		
	}


	public List<Doctor> findDoctor(Page page) {

		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("number", page.getRowNo());  //每一页显示多少条数据
		params.put("start", (page.getPageNo()-1)*page.getRowNo());  //当前页
		return mapper.findDoctor(params);
	}


	public int selectCount() {
		
		return mapper.selectCount();
	}


	public List<Patient> selectDoctorPatientsById(int id) {

		return mapper.selectDoctorPatientsById(id);
	}



	public Doctor selectCheckDoctor(int department_id) {
		
		
		return mapper.selectCheckDoctor(department_id);
	}
	

	public List<Doctor> selectDoctorByDepartmentId(int department_id) {
		return mapper.selectDoctorByDepartmentId(department_id);
	}

}
