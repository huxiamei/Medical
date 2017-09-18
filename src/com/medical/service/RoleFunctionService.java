package com.medical.service;

import com.medical.model.Pager;
import com.medical.model.SysRoleFunction;
import com.medical.model.SysRoleFunctionVO;
import com.medical.tool.Result;

public interface RoleFunctionService {
	
	public Result<SysRoleFunction> addRoleFunction(SysRoleFunction role);
	
	public Result<SysRoleFunction> updateRoleFunction(SysRoleFunction role);
	
	public Result<SysRoleFunction> deleteRoleFunction(int id);
	
	public Pager<SysRoleFunctionVO> queryRoleFunctionByCondition(String role_name,int currPage, int pageSize);
}
