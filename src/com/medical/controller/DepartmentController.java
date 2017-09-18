package com.medical.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.Department;
import com.medical.service.DepartmentService;
import com.medical.tool.JsonUtil;
import com.medical.tool.Page;

@Controller
@RequestMapping("/Department")
public class DepartmentController {  

	//操作科室数据库信息
	@Autowired
	@Qualifier("DepartmentService")
	DepartmentService service;
	
	/**
	 * 访问科室页面
	 * @param model
	 * @return 科室url
	 */
	@RequestMapping(value="/index")
	public String index(Model model)
	{
		//科室页面有新增科室的弹框，给弹框表单绑定科室对象
		if(!model.containsAttribute("departmentInfo"))
		{
			Department department = new Department();
			model.addAttribute("departmentInfo", department);
		}	
		return "manage/department";
	}
	
	
	/**
	 * 增加科室
	 * @param department  科室信息
	 * @param bindingResult 错误绑定
	 * @param response  与前台交互的
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/add", method = {RequestMethod.POST})
	@ResponseBody
	public void add(@ModelAttribute(value="departmentInfo")Department department,BindingResult bindingResult,HttpServletResponse response) throws IOException
	{
		JSONObject json = new JSONObject();	
		
		//如果输入的值不符合绑定表单对象的要求，返回提示信息
		if(bindingResult.hasErrors())
		 {
			System.out.println("***************************************************************************************");
		    json.put("message", "新增科室信息失败");
		 }	
		else
		{
			service.addDepartment(department);		
			json.put("message", "新增科室信息成功");
		}
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  //返回提示信息
		
	}
	
	/**
	 * 点击修改按钮，执行的操作
	 * @param DepartmentId  要修改的科室的id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{DepartmentId}", method = {RequestMethod.GET})
	@ResponseBody
	public void toUpdate(@PathVariable(value="DepartmentId") int DepartmentId,HttpServletResponse response) throws IOException
	{
		//调用service访问数据库得到要修改的科室对象
		Department department = service.selectDepartmentById(DepartmentId);
		JSONObject json = new JSONObject();
		json.put("Updatedepartment", department);  //将要修改的科室对象信息返回前台
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
	/**
	 * 修改科室信息
	 * @param department 科室
	 * @param bindingResult 错误绑定
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{DepartmentId}", method = {RequestMethod.POST})
	@ResponseBody
	public void update(@ModelAttribute(value="departmentInfo")Department department,BindingResult bindingResult ,HttpServletResponse response) throws IOException
	{		
		
		JSONObject json = new JSONObject();
		//输入值不符合要求
		if(bindingResult.hasErrors())
		 {  
		    json.put("message", "update error");
		 }	
		else
		{
			service.updateDepartment(department);
			json.put("message", "修改成功");
		}
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 	
		
	}
	
	/**
	 * 删除科室对象
	 * @param DepartmentId 要删除的科室id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/delete/{DepartmentId}")
	@ResponseBody
	public void delete(@PathVariable int DepartmentId,HttpServletResponse response)throws IOException
	{
		service.deleteDepartment(DepartmentId);
	}
	
	
	/**
	 * 得到页面总数
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/totalPage")
	@ResponseBody  
	public void getTotalPage(HttpServletResponse response) throws IOException
	{
		Page page = new Page(1,service.selectDepartmentCount(),10);	
		JSONObject json = new JSONObject();
		json.put("page", page.getPageCount());
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());
	}
	
	/**
	 * 得到当前页面的科室信息
	 * @param session  不管
	 * @param pageNo 当前页面
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/list/{pageNo}")
	@ResponseBody  
	public void showList(HttpSession session,@PathVariable(value = "pageNo")int pageNo,HttpServletResponse response) throws IOException
	{
		Page page = new Page(pageNo,service.selectDepartmentCount(),10);	
		List<Department> departmentList = service.findDepartment(page);  //得到当前页面的所有科室信息集
		JSONArray a = JsonUtil.toJSONArray(departmentList);
		JSONObject json = new JSONObject();
		json.put("departmentList", a);  //返回到前台
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}

	
	/**
	 * 得到所有医生信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/Alldata")
	@ResponseBody 
	public void getAllData(HttpServletResponse response) throws IOException
	{
		
		List<Department> departmentList = service.findDepartment(new Page(1,service.selectDepartmentCount(),Integer.MAX_VALUE)); //所有医生信息
		
		JSONArray b = JsonUtil.toJSONArray(departmentList);
		JSONObject json = new JSONObject();
        json.put("departmentList", b);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}

}
