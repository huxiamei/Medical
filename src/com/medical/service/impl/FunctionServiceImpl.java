package com.medical.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.mapper.FunctionMapper;
import com.medical.model.Pager;
import com.medical.model.SysFunction;
import com.medical.service.FunctionService;
import com.medical.tool.Result;


@Service("functionService")
public class FunctionServiceImpl implements FunctionService{
	
	Logger logger = Logger.getLogger(FunctionServiceImpl.class);
	
	@Autowired
	private FunctionMapper functionMapper;
	
	Result<SysFunction> result = new Result<SysFunction>();
	
	public Pager<SysFunction> getFunctionList(int currPage, int pageSize) {
		
		logger.info("后台管理：进入功能查询服务...");
		
		Pager<SysFunction> pager = new Pager<SysFunction>();
		
		//总记录、起始数据
		int total = functionMapper.getcount();
		int limitStart = (currPage - 1) * pageSize;
	
		//起始数据不得大于总数
		if(limitStart > total){
			logger.error("后台管理：功能查询服务失败...原因：" + "起始数据不得大于总数");
			return pager;
		}
		
		//查询链表
		try{
			List<SysFunction> pageList = functionMapper.getFunctionList(limitStart, pageSize);
			pager = new Pager<SysFunction>(pageSize, currPage, total, pageList);
			
			logger.info("后台管理：功能查询服务结束...");
			
		}catch(Exception e){
			logger.error("后台管理：功能查询服务失败，原因：" + e.getMessage());
		}
		
		return pager;
	}

	public Result<SysFunction> addFunction(SysFunction function) {
		
		logger.info("后台管理：进入功能添加服务...");
		
		//方法名已存在
		if(functionMapper.ifHasExist(function) > 0){
			result.setSuccess(false);
			result.setMessage("参数非法:已存在该数据");
			logger.error("后台管理：功能添加服务失败...");
			return result;
		}
		
		//添加方法
		try{
			functionMapper.addFunction(function);
			result.setSuccess(true);
			result.setMessage("功能添加成功");
			
			logger.info("后台管理：功能添加服务成功...");
			
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常！");
			logger.error("后台管理：功能添加服务失败,原因：" + e.getMessage());
		}
		
		return result;
	}

	public Result<SysFunction> updateFunction(SysFunction function) {
		
		logger.info("后台管理：进入功能修改服务...");
		
		//方法名已存在
		if(functionMapper.ifHasExist(function) > 0){
			result.setSuccess(false);
			result.setMessage("参数非法:已存在该数据");
			logger.error("后台管理：功能修改服务失败...");
			return result;
		}
				
		try{
			functionMapper.updateFunction(function);
			result.setSuccess(true);
			result.setMessage("功能修改成功");
			
			logger.info("后台管理：功能修改服务开始...");
			
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常！");
			logger.error("后台管理：功能修改服务失败,原因：" + e.getMessage());
			
		}
		
		return result;
	}

	public Result<SysFunction> deleteFunction(int id) {
		
		logger.info("后台管理：进入功能修改服务...");
		
		//id不合法
		if(id <= 0){
			result.setSuccess(false);
			result.setMessage("参数非法:id不得小于0");
			logger.error("后台管理：功能删除服务失败...");
		}
		
		try{
			functionMapper.deleteFunctionById(id);
			result.setSuccess(true);
			result.setMessage("删除功能成功");
			
			logger.info("后台管理：删除功能成功...");
			
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常！");
			logger.error("后台管理：功能删除服务失败,原因：" + e.getMessage());
		}
		
		
		return result;
	}

	
}
