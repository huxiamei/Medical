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

import com.medical.model.Hospital;
import com.medical.service.HospitalService;
import com.medical.tool.JsonUtil;
import com.medical.tool.Page;

@Controller
@RequestMapping("/Hospital")
public class HospitalController {
	
	@Autowired
	@Qualifier("HospitalService")
	HospitalService service;
	
	/**
	 * 载入医院页面
	 * @param model
	 * @return url
	 */
	@RequestMapping(value="/index")
	public String index(Model model)
	{
		//页面有新增弹框，为表单绑定对象
		if(!model.containsAttribute("hospitalInfo"))
		{
			Hospital hospital = new Hospital();
			model.addAttribute("hospitalInfo", hospital);
		}	
		return "manage/hospital";
	}
	
	/**
	 * 新增信息
	 * @param hospital 医院基本信息
	 * @param bindingResult 错误绑定
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/add", method = {RequestMethod.POST})
	@ResponseBody
	public void add(@ModelAttribute(value="hospitalInfo")Hospital hospital,BindingResult bindingResult,HttpServletResponse response) throws IOException
	{
		JSONObject json = new JSONObject();
		System.out.println(hospital.getHospital_name()+hospital.getDescription()+"dddddddddddddddddddddddddd");
		//如果输入的值域绑定的表单对象不一致，返回错误提示
		if(bindingResult.hasErrors())
		 {  
		    json.put("message", "新增医院信息失败");
		 }	
		else
		{
			service.addHospital(hospital);		
			json.put("message", "新增医院信息成功");
		}
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
		
	}
	
	/**
	 * 点击修改时执行的操作
	 * @param HospitalId 要修改的医院id
	 * @param response
	 * @throws IOException
	 */
	
	@RequestMapping(value = "/manage/update/{HospitalId}", method = {RequestMethod.GET})
	@ResponseBody
	public void toUpdate(@PathVariable(value="HospitalId") int HospitalId,HttpServletResponse response) throws IOException
	{
		//得到要修改的医院信息
		Hospital hospital = service.selectHospitalById(HospitalId);
		JSONObject json = new JSONObject();
		json.put("Updatehospital", hospital);  //返回给前台，填充到页面
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
	/**
	 * 修改医院信息
	 * @param hospital 要修改的医院
	 * @param bindingResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{HospitalId}", method = {RequestMethod.POST})
	@ResponseBody
	public void update(@ModelAttribute(value="hospitalInfo")Hospital hospital,BindingResult bindingResult ,HttpServletResponse response) throws IOException
	{		
		
		JSONObject json = new JSONObject();
		if(bindingResult.hasErrors())
		 {  
		    json.put("message", "update error");
		 }	
		else
		{
			service.updateHospital(hospital);	
			json.put("message", "修改成功");
		}
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 	
		
	}
	
	/**
	 * 删除医院
	 * @param HospitalId 要删除的医院id
	 * @param response 与前台交互的对象
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/delete/{HospitalId}")
	@ResponseBody
	public void delete(@PathVariable int HospitalId,HttpServletResponse response)throws IOException
	{
		service.deleteHospital(HospitalId);		
	}
	
	
	/**
	 * 得到页面数，用于分页
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/totalPage")
	@ResponseBody  
	public void getTotalPage(HttpServletResponse response) throws IOException
	{
		Page page = new Page(1,service.selectHospitalCount(),10);	
		JSONObject json = new JSONObject();
		json.put("page", page.getPageCount());
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());
	}
	
	/**
	 * 得打当前页面的所有医院信息，分页查询
	 * @param session  不需要的参数
	 * @param pageNo  当前页数
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/list/{pageNo}")
	@ResponseBody  
	public void showList(HttpSession session,@PathVariable(value = "pageNo")int pageNo,HttpServletResponse response) throws IOException
	{
		Page page = new Page(pageNo,service.selectHospitalCount(),10);	
		List<Hospital> hospitalList = service.findHospital(page);  //得到当前页的医院信息
		JSONArray a = JsonUtil.toJSONArray(hospitalList);
		JSONObject json = new JSONObject();
		json.put("hospitalList", a);  //传递给前台
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}

	/**
	 * 得到所有医院信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/Alldata")
	@ResponseBody 
	public void getAllData(HttpServletResponse response) throws IOException
	{
		List<Hospital> hospitalList = service.findHospital(new Page(1,service.selectHospitalCount(),Integer.MAX_VALUE)); //系统内所有医院信息
		
		JSONArray a = JsonUtil.toJSONArray(hospitalList);
		JSONObject json = new JSONObject();

        json.put("hospitalList", a);  //传递给前台
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}
}
