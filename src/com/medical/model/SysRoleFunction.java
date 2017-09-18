package com.medical.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

/**
 * 权限功能对应实体类
 * 
 * Table SYS_ROLE_FUNCTION
 * 编号
 * 权限ID
 * 功能ID
 * 
 * @author medical
 *
 */
@Repository
public class SysRoleFunction {

	@NotNull(message="id不能为空")
	private int id;
	
	@NotNull(message="权限ID不能为空")
	private int role_id;
	
	@NotNull(message="功能ID不能为空")
	private int function_id;

	public SysRoleFunction() {
	}

	public SysRoleFunction(int role_id, int function_id) {
		this.role_id = role_id;
		this.function_id = function_id;
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
