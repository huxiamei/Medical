package com.medical.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.medical.service.FunctionService;
import com.medical.tool.Result;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("background/function")
public class FunctionController {
	
	@Autowired
	@Qualifier("functionService")
	private FunctionService functionService;
	
	Logger logger = Logger.getLogger(FunctionController.class);
	
	@RequestMapping(value = "/functionList", method = RequestMethod.GET)
	public String functionList(Model model, HttpServletRequest request){
		
		logger.info("后台管理：请求调转到功能管理页面...");
		
		String currPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		int _currPage = Integer.parseInt(currPage);
		int _pageSize = Integer.parseInt(pageSize);
		
		//查询
		Pager<SysFunction> page = functionService.getFunctionList(_currPage, _pageSize);
		model.addAttribute("page", page);
		
		logger.info("后台管理：正在调转到功能管理页面...");
		
		return "manage/function";
	}
	
	
	@ResponseBody
	@RequestMapping("functionAddUpdate")
	public JSONObject functionUpdate(Model model, HttpServletRequest request){
		
		logger.info("后台管理：请求更新（增加）功能管理...");
		
		JSONObject json = new JSONObject();
		Result<SysFunction> res;
		
		//获取表单数据
		String id = request.getParameter("id");
		String function_name = request.getParameter("function_name");
		String description = request.getParameter("description");
		String call_back = request.getParameter("call_back");
		
		//创建角色对象
		SysFunction function = new SysFunction(call_back, function_name, description);
		
		//进行修改（添加）处理
		if(id != null && !id.equals("")){
			int _id = Integer.parseInt(id);
			function.setId(_id);
			res = functionService.updateFunction(function);
			
		}else{
			res = functionService.addFunction(function);
		}
		
		//结果处理
		if(res.isSuccess()){
			json.put("code", 1);
			json.put("message", res.getMessage());
		}else{
			json.put("code", 0);
			json.put("message", res.getMessage());
		}
		
		logger.info("后台管理：更新（增加）功能管理结束...");
		
		return json;
	}
	
	@ResponseBody
	@RequestMapping("functionDelete")
	public JSONObject functionDelete(Model model, HttpServletRequest request){
		
		logger.info("后台管理：请求删除功能管理...");
		
		JSONObject json = new JSONObject();
		String id = request.getParameter("id");
		
		//id不存在
		if(id==null || id.equals("")){
			json.put("code", 0);
			json.put("message", "删除失败：无法获取到id");
			return json;
		}
		
		int _id = Integer.parseInt(id);
		Result<SysFunction> res = functionService.deleteFunction(_id);
		
		//结果处理
		if(res.isSuccess()){
			json.put("code", 1);
			json.put("message", res.getMessage());
		}else{
			json.put("code", 0);
			json.put("message", res.getMessage());
		}
		
		logger.info("后台管理：删除功能管理结束...");
		
		return json;
	}
	
}
