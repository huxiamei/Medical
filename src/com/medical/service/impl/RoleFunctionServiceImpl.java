package com.medical.service.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.mapper.RoleFunctionExtroMapper;
import com.medical.mapper.RoleFunctionMapper;
import com.medical.model.Pager;
import com.medical.model.SysRoleFunction;
import com.medical.model.SysRoleFunctionVO;
import com.medical.service.RoleFunctionService;
import com.medical.tool.Result;


@Service("roleFunctionService")
public class RoleFunctionServiceImpl implements RoleFunctionService{
	
	Logger logger = Logger.getLogger(RoleFunctionServiceImpl.class);
	
	@Autowired
	private RoleFunctionMapper roleFunctionMapper;
	
	@Autowired
	private RoleFunctionExtroMapper roleFunctionExtroMapper;
	
	Result<SysRoleFunction> result = new Result<SysRoleFunction>();

	public Result<SysRoleFunction> addRoleFunction(SysRoleFunction roleFunction) {
		
		logger.info("后台管理：进入角色功能添加服务...");
		
		if(roleFunctionMapper.ifHasExist(roleFunction) > 0){
			result.setSuccess(false);
			result.setMessage("不可插入重复数据！");
			logger.error("后台管理：角色功能添加服务失败,原因： 不可插入重复数据");
			
			return result;
		}
		
		//添加方法
		try{
			roleFunctionMapper.addRoleFunction(roleFunction);
			result.setSuccess(true);
			result.setMessage("角色功能添加成功");
			
			logger.info("后台管理：角色功能添加服务成功...");
			
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常！");
			logger.error("后台管理：角色功能添加服务失败,原因：" + e.getMessage());
		}
		
		return result;
		
	}

	public Result<SysRoleFunction> updateRoleFunction(SysRoleFunction roleFunction) {
		
		logger.info("后台管理：进入角色功能修改服务...");
		
		if(roleFunctionMapper.ifHasExist(roleFunction) > 0){
			result.setSuccess(false);
			result.setMessage("不可插入重复数据！");
			logger.error("后台管理：角色功能修改服务失败,原因： 不可插入重复数据");
			
			return result;
		}
		
		try{
			roleFunctionMapper.updateRoleFunction(roleFunction);
			result.setSuccess(true);
			result.setMessage("角色功能修改成功");
			
			logger.info("后台管理：角色功能修改服务开始...");
			
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常！");
			logger.error("后台管理：角色功能修改服务失败,原因：" + e.getMessage());
			
		}
		
		return result;
	}

	public Result<SysRoleFunction> deleteRoleFunction(int id) {
		
		logger.info("后台管理：进入角色功能修改服务...");
		
		//id不合法
		if(id <= 0){
			result.setSuccess(false);
			result.setMessage("参数非法:id不得小于0");
			logger.error("后台管理：角色功能删除服务失败...");
		}
		
		try{
			roleFunctionMapper.deleteRoleFunctionById(id);
			result.setSuccess(true);
			result.setMessage("删除角色功能成功");
			
			logger.info("后台管理：删除角色功能成功...");
			
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常！");
			logger.error("后台管理：角色功能删除服务失败,原因：" + e.getMessage());
		}
		
		
		return result;
	}

	public Pager<SysRoleFunctionVO> queryRoleFunctionByCondition(
			String role_name, int currPage, int pageSize) {
		
		logger.info("后台管理：进入角色功能查询服务...");
		
		Pager<SysRoleFunctionVO> pager = new Pager<SysRoleFunctionVO>();
		
		//总记录、起始数据
		int total = roleFunctionExtroMapper.getRoleFunctionCountByCondition(role_name);
		int limitStart = (currPage - 1) * pageSize;
	
		//起始数据不得大于总数
		if(limitStart > total){
			logger.error("后台管理：角色功能查询服务失败...原因：" + "起始数据不得大于总数");
			return pager;
		}
		
		//查询链表
		try{
			List<SysRoleFunctionVO> pageList = roleFunctionExtroMapper.getRoleFunctionExtroListByCondition(role_name, limitStart, pageSize);
			pager = new Pager<SysRoleFunctionVO>(pageSize, currPage, total, pageList);
			
			logger.info("后台管理：角色功能查询服务结束...");
			
		}catch(Exception e){
			logger.error("后台管理：角色功能查询服务失败，原因：" + e.getMessage());
		}
		
		return pager;
	}

	
}
