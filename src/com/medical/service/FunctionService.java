package com.medical.service;

import com.medical.model.Pager;
import com.medical.model.SysFunction;
import com.medical.tool.Result;

public interface FunctionService {

	public Pager<SysFunction> getFunctionList(int currPage, int pageSize);
	
	public Result<SysFunction> addFunction(SysFunction function);
	
	public Result<SysFunction> updateFunction(SysFunction function);
	
	public Result<SysFunction> deleteFunction(int id);
}
