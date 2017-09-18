package com.medical.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.mapper.RoleMapper;
import com.medical.model.SysRole;
import com.medical.service.RoleService;
import com.medical.tool.Result;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	Logger logger = Logger.getLogger(RoleServiceImpl.class);
	
	@Autowired
	private RoleMapper roleMapper;
	
	 /** 结果 **/
	Result<SysRole> result = new Result<SysRole>();
	
	public List<SysRole> getAllRoleList() {
		
		logger.info("后台管理：进入权限查询服务...");
		
		List<SysRole> roleList = new ArrayList<SysRole>();
		try{
			roleList = roleMapper.getAllRoleList();
			logger.info("后台管理：权限查询服务结束...");
			
		}catch(Exception e){
			logger.error("后台管理：权限查询服务失败...原因：" + e.getMessage());
		}
		return roleList;
	}

	public Result<SysRole> addRole(SysRole role) {
		
		logger.info("后台管理：进入权限增加服务...");
		
		//已存在角色名称或代号
		if(roleMapper.getHasExist(role) > 0){
			result.setSuccess(false);
			result.setMessage("添加失败：权限名称或代号已存在");
			logger.error("后台管理：权限增加服务失败...");
			return result;
		}
		
		//增加
		try{
			roleMapper.addRole(role);
			result.setSuccess(true);
			result.setMessage("添加权限成功!");
			logger.info("后台管理：权限增加服务结束...");
			
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常");
			logger.error("后台管理：权限增加服务失败...原因：" + e.getMessage());
		}
		
		return result;
	}

	public Result<SysRole> updateRole(SysRole role) {
		
		logger.info("后台管理：进入权限修改服务...");

		//已存在角色名称或代号
		if(roleMapper.getHasExist(role) > 0){
			result.setSuccess(false);
			result.setMessage("修改失败：权限名称或代号已存在");
			logger.error("后台管理：权限修改服务失败...");
			return result;
		}
		
		try{
			roleMapper.updateRole(role);
			result.setSuccess(true);
			result.setMessage("权限修改成功!");
			logger.info("后台管理：权限修改服务结束...");
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常");
			logger.error("后台管理：权限修改服务失败...原因：" + e.getMessage());
		}
		
		return result;
	}

	public Result<SysRole> deleteRole(int id) {
		
		logger.info("后台管理：进入权限删除服务...id=" + id);
		
		//id非法
		if(id <= 0){
			result.setSuccess(false);
			result.setMessage("删除失败：权限id非法");
			logger.error("后台管理：权限删除服务失败...");
			return result;
		}
		
		//TODO 是否还有用户使用该权限
		if(false){
			result.setSuccess(false);
			result.setMessage("还有用户使用该权限,删除权限失败");
			logger.error("后台管理：权限删除服务失败...");
			return result;
		}
		
		//删除
		try{
			roleMapper.deleteRoleById(id);
			result.setSuccess(true);
			result.setMessage("权限删除成功!");
			logger.info("后台管理：权限删除服务结束...");
		}catch(Exception e){
			result.setSuccess(false);
			result.setMessage("数据库异常");
			logger.error("后台管理：权限删除服务失败...原因：" + e.getMessage());
		}
		
		return result;
	}

}
