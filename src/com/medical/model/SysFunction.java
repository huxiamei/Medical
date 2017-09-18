package com.medical.model;

import javax.validation.constraints.NotNull;

/**
 * 功能实体类
 * 
 * Table SYS_FUNCTION
 * 功能id
 * 功能名称
 * 
 * @author medical
 *
 */
public class SysFunction {
	
	@NotNull(message="功能id不能为空")
	private int id;
	
	@NotNull(message="功能名称不能为空")
	private String function_name;
	
	@NotNull(message="功能回调不能为空")
	private String call_back;
	
	private String description;

	public SysFunction() {
		super();
	}

	public SysFunction( String call_back, String function_name, String description) {
		this.call_back = call_back;
		this.function_name = function_name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFunction_name() {
		return function_name;
	}

	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCall_back() {
		return call_back;
	}

	public void setCall_back(String call_back) {
		this.call_back = call_back;
	}

}