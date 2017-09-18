package com.medical.model;

/**
 * ����Ա
 * Table SYS_ADMIN
 * 
 * ����Աid
 * ��¼��
 * ����
 * Ȩ��
 * @author medical
 *
 */
public class SysAdmin {

	private int id;
	private String admin_name ;
	private String admin_password;
	private int role_id;
	
	public SysAdmin()
	{
		
	}
	
	public SysAdmin(int id, String name, String password, int roleId) {
		super();
		this.id = id;
		this.admin_name = name;
		this.admin_password = password;
		role_id = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	
}
