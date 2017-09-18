package com.medical.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.UserMapper;
import com.medical.model.User;
import com.medical.service.UserService;


@Service("UserService")
public class UserServiceImpl implements UserService{

	@Resource
	UserMapper mapper;
	
	public User selectUser(int id) {
		
		return mapper.selectUser(id);
	}

	public boolean userLogin(int id, String password) {
		
		User user = mapper.selectUser(id);
		if(user!=null && user.getUser_password().equals(password))
		{
			return true;
		}
		return false;
	}

	public void updateUserPassword(int id,String password) {
		
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("id", id);
		params.put("password", password);
		mapper.updateUserPassword(params);
		
	}

}
