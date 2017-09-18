package com.medical.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.medical.model.SysRole;

public interface RoleMapper {
	
	@Select(value = "SELECT id, role_name, code, description FROM sys_role")
	public List<SysRole> getAllRoleList();

	@Delete(value = "DELETE FROM sys_role WHERE id = #{roleId}")
	public int deleteRoleById(int roleId);

	@Update(value = "UPDATE sys_role SET role_name = #{role_name}, code = #{code}, description = #{description} WHERE id = #{id}")
	public int updateRole(SysRole role);
	
	@Insert(value = "INSERT INTO sys_role(role_name,code,description) VALUES (#{role_name},#{code},#{description})")
	public int addRole(SysRole role);
	
	public int getHasExist(SysRole role);
}
