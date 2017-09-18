package com.medical.service;

import com.medical.model.User;

public interface UserService {

	public User selectUser(int id);

	public boolean userLogin(int id, String password);
	
	public void updateUserPassword(int id,String password);
}
