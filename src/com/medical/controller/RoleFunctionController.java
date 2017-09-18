package com.medical.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.Pager;
import com.medical.model.SysFunction;
import com.medical.model.SysRole;
import com.medical.model.SysRoleFunction;
import com.medical.model.SysRoleFunctionVO;
import com.medical.service.FunctionService;
import com.medical.service.RoleFunctionService;
import com.medical.service.RoleService;
import com.medical.tool.Result;

@Controller
@RequestMapping("background/roleFunction")
public class RoleFunctionController {

	@Autowired
	@Qualifier("roleService")
	private RoleService roleService;
	
	@Autowired
	@Qualifier("functionService")
	private FunctionService functionService;
	
	@Autowired
	@Qualifier("roleFunctionService")
	private RoleFunctionService roleFunctionService;
	
	Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping(value = "/roleFunctionList", method = RequestMethod.GET)
	public String roleFunctionList(Model model, HttpServletRequest request) throws UnsupportedEncodingException{
		
		logger.info("后台管理：请求调转角色功能管理页面...");
		
		String role_name = request.getParameter("role_name");
		//TODO 不知道为什么乱码
		if(role_name != null){
			role_name = new String(role_name.getBytes("ISO-8859-1"),"UTF-8");
		}
		String currPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		int _currPage = Integer.parseInt(currPage);
		int _pageSize = Integer.parseInt(pageSize);

		//查询
		Pager<SysRoleFunctionVO> pager = roleFunctionService.queryRoleFunctionByCondition(role_name, _currPage, _pageSize);
		//所有角色
		List<SysRole> roleList = roleService.getAllRoleList();
		//所有方法
		Pager<SysFunction> functionList = functionService.getFunctionList(0, 0);
		
		model.addAttribute("roleList", roleList);
		model.addAttribute("functionList", functionList);
		model.addAttribute("page", pager);
		model.addAttribute("role_name", role_name);
		
		logger.info("后台管理：正在调转到角色功能管理页面...");
		
		return "manage/role_function";
	}
	
	@ResponseBody
	@RequestMapping("roleFunctionAddUpdate")
	public JSONObject roleFunctionAddUpdate(Model model, HttpServletRequest request){
		
		logger.info("后台管理：请求更新（增加）权限管理...");
		
		JSONObject json = new JSONObject();
		Result<SysRoleFunction> res;
		
		//获取表单数据
		String id = request.getParameter("id");
		String role_id = request.getParameter("role_id");
		String function_id = request.getParameter("function_id");
		int _role_id = Integer.parseInt(role_id);
		int _function_id = Integer.parseInt(function_id);
		
		//创建角色对象
		SysRoleFunction roleFunction = new SysRoleFunction(_role_id, _function_id);
		
		//进行修改（添加）处理
		if(id != null && !id.equals("")){
			int _id = Integer.parseInt(id);
			roleFunction.setId(_id);
			res = roleFunctionService.updateRoleFunction(roleFunction);
			
		}else{
			res = roleFunctionService.addRoleFunction(roleFunction);
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
	@RequestMapping("roleFunctionDelete")
	public JSONObject roleFunctionDelete(Model model, HttpServletRequest request){
		
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
		Result<SysRoleFunction> res = roleFunctionService.deleteRoleFunction(_id);
		
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
