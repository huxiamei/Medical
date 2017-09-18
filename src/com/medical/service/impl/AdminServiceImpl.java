package com.medical.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.AdminMapper;
import com.medical.model.SysAdmin;
import com.medical.service.AdminService;


@Service("AdminService")
public class AdminServiceImpl implements AdminService{

	@Resource
	AdminMapper mapper;
	
	public SysAdmin selectAdmin(int id) {
		
		return mapper.selectAdmin(id);
	}

	public boolean adminLogin(int id, String password) {
		
		SysAdmin admin = mapper.selectAdmin(id);
		if(admin!=null &&admin.getAdmin_password().equals(password))
		{
			return true;
		}
		return false;
	}

}
