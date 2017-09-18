package com.medical.service;

import com.medical.model.SysAdmin;

public interface AdminService {

	public SysAdmin selectAdmin(int id);
	
	public boolean adminLogin(int id,String password);
}
