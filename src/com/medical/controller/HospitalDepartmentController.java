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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.Department;
import com.medical.model.Hospital;
import com.medical.model.HospitalDepartment;
import com.medical.service.DepartmentService;
import com.medical.service.HospitalDepartmentService;
import com.medical.service.HospitalService;
import com.medical.service.impl.DepartmentServiceImpl;
import com.medical.service.impl.HospitalServiceImpl;
import com.medical.tool.JsonUtil;
import com.medical.tool.Page;


@Controller
@RequestMapping("/HospitalDepartment")
public class HospitalDepartmentController {

	
	@Autowired
	@Qualifier("HospitalDepartmentService")
	HospitalDepartmentService service;
	
	/**
	 * 医院科室对应信息的页面
	 * @param model
	 * @return url
	 */
	@RequestMapping(value="/index")
	public String index(Model model)
	{
		
		if(!model.containsAttribute("hospitalDepartment"))
		{
			HospitalDepartment hd = new HospitalDepartment();
			model.addAttribute("hospitalDepartment", hd);
		}	
		return "manage/hospital_department";
	}
	
	
	/**
	 * 增加信息
	 * @param hospitalId 医院id
	 * @param departmentId 科室id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/add", method = {RequestMethod.POST})
	@ResponseBody
	public void add(String hospitalId,String departmentId,HttpServletResponse response) throws IOException
	{    
	    Hospital hospital = new Hospital();
		Department department = new Department();
		HospitalDepartment hd = new HospitalDepartment();
		
		int hosId = Integer.parseInt(hospitalId);
		int depId = Integer.parseInt(departmentId);
		
		String message = "";
			
		if(service.selectHDByHD(hosId, depId))  //判断这样的组合是否存在，如果存在，返回提示信息，不存在则新增信息
		{
			message = "该医院已经存在该科室，请重新选择";			
		}
		else
		{
			hospital.setId(hosId);			
			department.setId(depId);
			hd.setHospital(hospital);
			hd.setDepartment(department);
			service.addHospitalDepartment(hd);
			message = "医院新增科室信息成功";
		}	
		
		JSONObject json = new JSONObject();	
		json.put("message", message);
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
		
	}
	
	/**
	 * 点击修改时做的操作 
	 * @param HospitalDepartmentId 要修改的信息的id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{HospitalDepartmentId}", method = {RequestMethod.GET})
	@ResponseBody
	public void toUpdate(@PathVariable(value="HospitalDepartmentId") int HospitalDepartmentId,HttpServletResponse response) throws IOException
	{
		HospitalDepartment hd = service.selectHospitalDepartmentById(HospitalDepartmentId);
//		model.addAttribute("hospitalInfo", hospital);
		JSONObject json = new JSONObject();
		json.put("UpdatehospitalDeaprtment", hd);  //将信息传递到前台
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
		//return "manage/hospital";	
	}
	
	/**
	 * 修改医院科室对应信息
	 * @param HospitalDepartmentId  要修改的医院科室对应信息id
	 * @param hospitalId 医院id
	 * @param departmentId 科室id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{HospitalDepartmentId}", method = {RequestMethod.POST})
	@ResponseBody
	public void update(@PathVariable(value="HospitalDepartmentId") int HospitalDepartmentId,String hospitalId,String departmentId,HttpServletResponse response) throws IOException
	{
		//根据id找到要修改的对象
		HospitalDepartment hd = service.selectHospitalDepartmentById(HospitalDepartmentId);
		Hospital hospital = new Hospital();
		Department department = new Department();
		
		int hosId = Integer.parseInt(hospitalId); //将string转换为int
		int depId = Integer.parseInt(departmentId);
		
		JSONObject json = new JSONObject();		
		if(service.selectHDByHD(hosId, depId))  //如果数据库中已经存在这个组合，返回提示信息
		{
			json.put("message", "该医院已经存在该科室，请重新选择");
		}
		else
		{
			//不存在冲突，修改信息
			hospital.setId(hosId);			
			department.setId(depId);
			hd.setHospital(hospital);
			hd.setDepartment(department);
			service.updateHospitalDepartment(hd);
			json.put("message", "修改成功");
		}		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 			
	}
	
	/**
	 * 删除信息
	 * @param HospitalDepartmentId 要删除的信息id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/delete/{HospitalDepartmentId}")
	@ResponseBody
	public void delete(@PathVariable int HospitalDepartmentId,HttpServletResponse response)throws IOException
	{
		service.deleteHospitalDepartment(HospitalDepartmentId);	
	}
	
	/**
	 * 得到总页数，用于分页
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/totalPage")
	@ResponseBody  
	public void getTotalPage(HttpServletResponse response) throws IOException
	{
		Page page = new Page(1,service.selectHospitalDepartmentCount(),10);	
		JSONObject json = new JSONObject();
		json.put("page", page.getPageCount());
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());
	}
	
	/**
	 * 根据当前页面的到页面总数
	 * @param session
	 * @param pageNo 当前页面
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/list/{pageNo}")
	@ResponseBody  
	public void showList(HttpSession session,@PathVariable(value = "pageNo")int pageNo,HttpServletResponse response) throws IOException
	{
		Page page = new Page(pageNo,service.selectHospitalDepartmentCount(),10);	
		List<HospitalDepartment> hospitalDepartmentList = service.findHospitalDepartment(page); //得到当前页面的医院科室信息集
		JSONArray a = JsonUtil.toJSONArray(hospitalDepartmentList);  //将对象转化为json对象数组
		JSONObject json = new JSONObject();
		json.put("hospitalDepartmentList", a); //传递到前台
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}
	
	
	/**
	 * 得到所有医院科室信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/Alldata")
	@ResponseBody 
	public void getAllData(HttpServletResponse response) throws IOException
	{
		HospitalService hs = new HospitalServiceImpl();
		DepartmentService ds = new DepartmentServiceImpl();
		
		List<Department> departmentList = ds.findDepartment(new Page(1,ds.selectDepartmentCount(),Integer.MAX_VALUE)); //所有科室
		List<Hospital> hospitalList = hs.findHospital(new Page(1,hs.selectHospitalCount(),Integer.MAX_VALUE));  //所有医院
		
		JSONArray a = JsonUtil.toJSONArray(hospitalList);
		JSONArray b = JsonUtil.toJSONArray(departmentList);
		JSONObject json = new JSONObject();

        json.put("hospitalList", a);
        json.put("departmentList", b);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}

	/**
	 * 根据医院的id得到他的所有部门
	 * @param hospitalId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/getDepartment/{hospitalId}")
	@ResponseBody
	public void getDepartmentByHospitalId(@PathVariable(value = "hospitalId")int hospitalId,HttpServletResponse response) throws IOException
	{
		JSONObject json = new JSONObject();
		try
		{			
			 List<HospitalDepartment> departmentList = service.selectDepartmentByHospitalId(hospitalId);	
			
			 JSONArray b = JsonUtil.toJSONArray(departmentList);  		
			 json.put("departmentList", b); //返回部门信息到前台
			
			 PrintWriter out = response.getWriter();  	
			 out.print(json.toString());		
			 
		}catch(Exception e){
			
			json.put("message", "该医院下没有科室");
			PrintWriter out = response.getWriter();  	
			out.print(json.toString());
		}
	}
}
