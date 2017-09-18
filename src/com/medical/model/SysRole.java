package com.medical.model;

import javax.validation.constraints.NotNull;

/**
 * 权限实体类
 * 
 * Table SYS_ROLE
 * 权限ID
 * 权限名称
 * 权限代号
 * 权限描述
 * 
 * @author medical
 *
 */
public class SysRole {

	@NotNull(message="权限id不能为空")
	private int id;
	
	@NotNull(message="权限名称不能为空")
	private String role_name;
	
	@NotNull(message="权限代号不能为空")
	private String code;
	
	private String description;

	public SysRole() {
	}

	
	public SysRole(String role_name, String code, String description) {
		this.role_name = role_name;
		this.code = code;
		this.description = description;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
