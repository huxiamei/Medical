package com.medical.model;

import org.springframework.stereotype.Repository;

@Repository
public class SysRoleFunctionVO {
	
	private int id;
	
	private int role_id;
	
	private int function_id;
	
	private String role_code;
	
	private String role_name;
	
	private String function_name;
	
	private String call_back;

	public SysRoleFunctionVO() {
		
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getFunction_name() {
		return function_name;
	}

	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}

	public String getCall_back() {
		return call_back;
	}

	public void setCall_back(String call_back) {
		this.call_back = call_back;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public int getFunction_id() {
		return function_id;
	}

	public void setFunction_id(int function_id) {
		this.function_id = function_id;
	}
	
}	
