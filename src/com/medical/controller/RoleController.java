package com.medical.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.SysRole;
import com.medical.service.RoleService;
import com.medical.tool.Result;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("background/role")
public class RoleController {
	
	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping(value = "/roleList", method = RequestMethod.GET)
	public String roleList(Model model){
		
		logger.info("后台管理：请求调转到权限管理页面...");
		
		List<SysRole> roleResult = roleService.getAllRoleList();
		model.addAttribute("roleList", roleResult);
		
		logger.info("后台管理：正在调转到权限管理页面...");
		return "manage/roleType";
	}
	
	
	@ResponseBody
	@RequestMapping("roleAddUpdate")
	public JSONObject roleUpdate(Model model, HttpServletRequest request){
		
		logger.info("后台管理：请求更新（增加）权限管理...");
		
		JSONObject json = new JSONObject();
		Result<SysRole> res;
		
		//获取表单数据
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String role_name = request.getParameter("role_name");
		String description = request.getParameter("description");
		
		//创建角色对象
		SysRole role = new SysRole(role_name, code, description);
		
		//进行修改（添加）处理
		if(id != null && !id.equals("")){
			int _id = Integer.parseInt(id);
			role.setId(_id);
			res = roleService.updateRole(role);
			
		}else{
			res = roleService.addRole(role);
		}
		
		//结果处理
		if(res.isSuccess()){
			json.put("code", 1);
			json.put("message", res.getMessage());
		}else{
			json.put("code", 0);
			json.put("message", res.getMessage());
		}
		
		logger.info("后台管理：更新（增加）权限管理结束...");
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping("roleDelete")
	public JSONObject roleDelete(Model model, HttpServletRequest request){
		
		logger.info("后台管理：请求删除权限管理...");
		
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		
		//id不存在
		if(id==null || id.equals("")){
			json.put("code", 0);
			json.put("message", "删除失败：无法获取到id");
			return json;
		}
		
		int _id = Integer.parseInt(id);
		Result<SysRole> res = roleService.deleteRole(_id);
		
		//结果处理
		if(res.isSuccess()){
			json.put("code", 1);
			json.put("message", res.getMessage());
		}else{
			json.put("code", 0);
			json.put("message", res.getMessage());
		}
		
		logger.info("后台管理：删除权限管理结束...");
		
		return json;
	}
	
}
