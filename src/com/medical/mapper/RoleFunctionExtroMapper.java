package com.medical.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.medical.model.SysRoleFunctionVO;

public interface RoleFunctionExtroMapper {
	
	public List<SysRoleFunctionVO> getRoleFunctionExtroListByCondition(@Param(value="role_name") String role_name, @Param("limitStart")int limitStart, @Param("pageSize")int pageSize);
	
	public int getRoleFunctionCountByCondition(@Param(value="role_name") String role_name );
	
}
