package com.medical.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * ����
 * Table BAS_DEPARTMENT
 * 
 * ����id
 * ��������
 * ��������
 * @author medical 
 *
 */
public class Department {

	@NotNull(message="����ID����Ϊ��")
	private int id;
	
	@NotNull(message="�������ֲ���Ϊ��")
	@Size(min=1,max=20,message="����������1~20���ַ�����")
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
