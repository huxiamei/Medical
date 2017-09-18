package com.medical.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.medical.model.SysFunction;

public interface FunctionMapper {
	
	public List<SysFunction> getFunctionList(@Param("limitStart")int limitStart, @Param("pageSize")int pageSize);

	public int ifHasExist(SysFunction function);
	
	@Delete(value = "DELETE FROM sys_function WHERE id = #{FunctionId}")
	public int deleteFunctionById(int functionId);

	@Update(value = "UPDATE sys_function SET function_name = #{function_name}, description = #{description},  call_back = #{call_back} WHERE id = #{id}")
	public int updateFunction(SysFunction Function);
	
	@Insert(value = "INSERT INTO sys_function(function_name,description,call_back) VALUES (#{function_name},#{description},#{call_back})")
	public int addFunction(SysFunction function);
	
	@Select(value = "SELECT COUNT(1) AS roleCount FROM sys_function")
	public int getcount();
}
