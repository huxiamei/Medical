package com.medical.mapper;

import java.util.Map;

import com.medical.model.User;

public interface UserMapper {

	/**
	 * 根据id得到user对象
	 * @param id
	 * @return
	 */
	public User selectUser(int id);


	public void updateUserPassword(Map<String, Object> params);
	
}
