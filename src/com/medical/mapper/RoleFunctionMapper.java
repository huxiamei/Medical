package com.medical.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.medical.model.SysRoleFunction;

public interface RoleFunctionMapper {

	@Delete(value = "DELETE FROM sys_role_function WHERE id = #{id}")
	public int deleteRoleFunctionById(int id);

	@Update(value = "UPDATE sys_role_function SET role_id = #{role_id}, function_id = #{function_id} WHERE id = #{id}")
	public int updateRoleFunction(SysRoleFunction roleFunction);
	
	@Insert(value = "INSERT INTO sys_role_function(role_id,function_id) VALUES (#{role_id},#{function_id})")
	public int addRoleFunction(SysRoleFunction roleFunction);
	
	public int ifHasExist(SysRoleFunction roleFunction);
	
}
