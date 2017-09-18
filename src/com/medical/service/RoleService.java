package com.medical.service;

import java.util.List;

import com.medical.model.SysRole;
import com.medical.tool.Result;

public interface RoleService {
	
	public List<SysRole> getAllRoleList();
	
	public Result<SysRole> addRole(SysRole role);
	
	public Result<SysRole> updateRole(SysRole role);
	
	public Result<SysRole> deleteRole(int id);
}
