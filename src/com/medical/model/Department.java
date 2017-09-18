package com.medical.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 科室
 * Table BAS_DEPARTMENT
 * 
 * 科室id
 * 科室名字
 * 科室描述
 * @author medical 
 *
 */
public class Department {

	@NotNull(message="部门ID不能为空")
	private int id;
	
	@NotNull(message="部门名字不能为空")
	@Size(min=1,max=20,message="部门名字在1~20个字符以内")
	private String dep_name;
	
	private String description;
	
	public Department()
	{
		
	}
	
	public Department(int id, String name, String description) {
		super();
		this.id = id;
		this.dep_name = name;
		this.description = description;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String name) {
		this.dep_name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
