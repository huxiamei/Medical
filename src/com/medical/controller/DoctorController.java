package com.medical.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.Doctor;
import com.medical.model.HospitalDepartment;
import com.medical.model.User;
import com.medical.service.DoctorService;
import com.medical.tool.JsonUtil;
import com.medical.tool.Page;


/**
 * 医生控制器
 *
 */
@Controller
@RequestMapping("/Doctor")
public class DoctorController {
	
	//Service对象，可以通过它对数据库进行访问
	@Autowired
	@Qualifier("DoctorService")
	DoctorService service;
	
	
	/**
	 * 载入医院页面
	 * 
	 * @param model
	 * @return  医院页面的url
	 */
	@RequestMapping(value="/index")
	public String index(Model model)
	{
		//医院页面有新增的弹框，为弹框表单绑定医生对象
		if(!model.containsAttribute("DoctorInfo"))
		{
			Doctor doctor = new Doctor();
			User user = new User();
			HospitalDepartment hd = new HospitalDepartment();
			doctor.setUser(user);
			doctor.setHospitalDepartment(hd);
			
			model.addAttribute("DoctorInfo", doctor);
		}	
		
		return "manage/doctor";
	}
	
	
	/**
	 * 新增医生时，点击提交所执行的函数
	 * @param doctor  绑定的表单对象，从前台传递过来，里面包含了新增的医生的所有信息
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @param bindingResult  错误验证参数，会自动验证表单对象的输入参数是否符合医生对象参数的要求
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/add", method = {RequestMethod.POST})
	@ResponseBody	
	public void add(@ModelAttribute(value="DoctorInfo")Doctor doctor,HttpServletResponse response,BindingResult bindingResult) throws IOException 
	{
		
		//json对象
		JSONObject json = new JSONObject();	
		
		//前台表单对象输入的参数不符合要求
		if(bindingResult.hasErrors())
		 {
			System.out.println("***************************************************************************************");
		    json.put("message", "新增医生信息失败");
		    
		    //将操作失败的信息通过json传递到前台，前台弹框显示
		    PrintWriter out = response.getWriter();  	
		    out.print(json.toString()); 
		 }	
		else
		{
			//调用service层函数保存医生信息
			service.addDoctor(doctor.getUser(), doctor.getHospitalDepartment().getId());	
			json.put("message", "新增医生信息成功");
			
			//将操作成功的信息通过json传递到前台，前台弹框显示
			PrintWriter out = response.getWriter();  	
		    out.print(json.toString()); 
		}		
	}
	/** 
	 * form表单提交 Date类型数据绑定 
	 * <功能详细描述> 
	 * @param binder 
	 * @see [类、类#方法、类#成员] 
	 */  
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	        dateFormat.setLenient(false);    
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	        
	}  
	
	
	/**
	 * 点击修改时调用的函数
	 * @param DoctorId  要修改的医生的ID
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{DoctorId}", method = {RequestMethod.GET})
	@ResponseBody
	public void toUpdate(@PathVariable(value="DoctorId") int DoctorId,HttpServletResponse response) throws IOException
	{
		
		//调用service层函数查找到指定id的医生对象
		Doctor doctor = service.selectDoctorById(DoctorId);		
		
		//转化医生的出生日期，以字符串的形式传递到前台，防止前台无法识别日期对象
		Date date = doctor.getUser().getBirthdate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startTime = sdf.format(date);
		
		JSONObject json = new JSONObject();
		json.put("Updatedoctor", doctor);  //将要修改的医生对象传递给前台，前台自动填充
		json.put("docbirthdate", startTime);  //医生的出生日期
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
	
	/**
	 * 修改医生信息
	 * @param doctor  要修改的医生的信息
	 * @param bindingResult  出错验证
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{DoctorId}", method = {RequestMethod.POST})
	@ResponseBody
	public void update(@ModelAttribute(value="DoctorInfo")Doctor doctor,BindingResult bindingResult ,HttpServletResponse response) throws IOException
	{				
		JSONObject json = new JSONObject();
		
		//验证表单字段是否有错，返回提示信息
		if(bindingResult.hasErrors())
		 {  
		    json.put("message", "update error");
		 }	
		else
		{
			System.out.println(doctor.getUser().getUser_name());
			//调用service层的函数，修改数据库医生信息
			service.updateDoctor(doctor.getUser(), doctor.getHospitalDepartment().getId());
			json.put("message", "修改成功");
		}
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());   //返回提示信息	
		
	}
	

	/**
	 * 删除医生信息
	 * @param DoctorId  要删除的医生的ID
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/delete/{DoctorId}")
	@ResponseBody
	public void delete(@PathVariable int DoctorId,HttpServletResponse response)throws IOException
	{
		//调用service层的函数删除医生
		service.deleteDoctorById(DoctorId);
	}
	
	/**
	 * 得到医生的总页数，用于前台分页
	 * @param response 和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/totalPage")
	@ResponseBody  
	public void getTotalPage(HttpServletResponse response) throws IOException
	{
		//新建一个page对象，将当前页数和医生总数，以及每页显示的数量传递进去
		Page page = new Page(1,service.selectCount(),10);	
		JSONObject json = new JSONObject();
		
		System.out.println(page.getPageCount());
		//将页对象传递给前台
		json.put("page", page.getPageCount());
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());
	}
	
	/**
	 * 分页查询医生信息
	 * @param session  暂时没有用，参数可以忽略
	 * @param pageNo  当前页数，与url参数绑定，可以通过url直接得到
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/list/{pageNo}")
	@ResponseBody  
	public void showList(HttpSession session,@PathVariable(value = "pageNo")int pageNo,HttpServletResponse response) throws IOException
	{
		int count = service.selectCount();
		List<Doctor> doctorList = new ArrayList<Doctor>();
		
		if(count>0)
		{
			//得到页对象
			Page page = new Page(pageNo,count,10);	
			
			//得到当前页数下的所有医生对象
			doctorList = service.findDoctor(page);					
		}

		JSONArray a = JsonUtil.toJSONArray(doctorList);
		JSONObject json = new JSONObject();
		//将医生对象传递给前台
		json.put("doctorList", a);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}

	

}
