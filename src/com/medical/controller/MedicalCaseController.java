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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.MedicalCase;
import com.medical.service.MedicalCaseService;
import com.medical.tool.JsonUtil;
import com.medical.tool.Page;

@Controller
@RequestMapping("/MedicalCase")
public class MedicalCaseController {
	
	@Autowired
	@Qualifier("MedicalCaseService")
	MedicalCaseService service;
	
	/**
	 * 根据病例id查看病例
	 * @param medicalCaseId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getMedicalCase/{MedicalCaseId}", method = {RequestMethod.GET})
	@ResponseBody
	public void getMedicalCase(@PathVariable int medicalCaseId,HttpServletResponse response) throws IOException
	{
		//调用service层函数查找到指定id的医生对象
		List<MedicalCase> medicalCase = service.selectCaseByCaseId(medicalCaseId);		
		
		JSONObject json = new JSONObject();
		json.put("medicalCase", medicalCase.get(0));
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
	/**
	 * 根据病例id和病人id查看病例
	 * @param medicalCaseId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/search", method = {RequestMethod.POST})
	@ResponseBody
	public void searchMedicalCase(int searchCaseId,int searchPatientId, HttpServletResponse response) throws IOException
	{
		List<MedicalCase> medicalCaseList=new ArrayList<MedicalCase>();
		if(!"".equals(searchCaseId) && "".equals(searchPatientId))
			medicalCaseList= service.selectCaseByCaseId(searchCaseId);		
		else if("".equals(searchCaseId) && !"".equals(searchPatientId))
			medicalCaseList = service.selectCaseByPatient_id(searchPatientId);
		else
			medicalCaseList = service.selectCaseById(searchCaseId,searchPatientId);
		
		if(medicalCaseList.size()>0){
			JSONArray a = JsonUtil.toJSONArray(medicalCaseList);
			JSONObject json = new JSONObject();
			json.put("medicalCaseList", a);
			json.put("message", "查询成功");
			PrintWriter out = response.getWriter();  	
		    out.print(json.toString());  
		}else{
			JSONObject json = new JSONObject();
			json.put("message", "没有符合条件的查询结果");
			PrintWriter out = response.getWriter();  	
			out.print(json.toString()); 
		}
		
	}
	
	/**
	 * 修改病例信息
	 * @param medicalCase  要修改的病人的信息
	 * @param bindingResult  出错验证
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{caseId}", method = {RequestMethod.POST})
	@ResponseBody
	public void update(@ModelAttribute(value="MedicalCaseInfo")MedicalCase medicalCase,BindingResult bindingResult ,HttpServletResponse response) throws IOException
	{				
		JSONObject json = new JSONObject();
		
		//验证表单字段是否有错，返回提示信息
		if(bindingResult.hasErrors())
		 {  
		    json.put("message", "update error");
		 }	
		else
		{
			System.out.println(medicalCase.getPatient().getUser().getUser_name());
			//调用service层的函数，修改数据库病人信息
			service.updateCase(medicalCase);
			json.put("message", "修改成功");
		}
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());   //返回提示信息	
		
	}
	
	/**
	 * 跳转到修改病人信息
	 * @param caseId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{caseId}", method = {RequestMethod.GET})
	@ResponseBody
	public void toUpdate(@PathVariable(value="caseId") int caseId,HttpServletResponse response) throws IOException
	{
		List<MedicalCase> medicalCase = service.selectCaseByCaseId(caseId);		
		
		Date date = medicalCase.get(0).getPatient().getUser().getBirthdate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthdate = sdf.format(date);
		
		JSONObject json = new JSONObject();
		json.put("updateMedicalCase", medicalCase.get(0));
		json.put("patbirthdate", birthdate);
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
	/**
	 * 得到该病人所有病历表
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/getMedicalCaseList/{PatientId}", method = {RequestMethod.GET})
	@ResponseBody
	public void getMedicalCaseList(@PathVariable int PatientId,HttpServletResponse response) throws IOException
	{
		List<MedicalCase> medicalCaseList = service.selectCaseByPatient_id(PatientId);
		
		JSONArray a = JsonUtil.toJSONArray(medicalCaseList);
		JSONObject json = new JSONObject();
		json.put("medicalCaseList", a);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}
	
	/**
	 * 得到分页
	 * @param session
	 * @param pageNo
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/list/{pageNo}")
	@ResponseBody  
	public void showList(HttpSession session,@PathVariable(value = "pageNo")int pageNo,HttpServletResponse response) throws IOException
	{
		
		int count = service.selectCount();
		List<MedicalCase> medicalCaseList = new ArrayList<MedicalCase>();
		
		if(count>0){
			Page page = new Page(pageNo,count,2);	
			medicalCaseList = service.findMedicalCase(page);
		}
		JSONArray a = JsonUtil.toJSONArray(medicalCaseList);
		JSONObject json = new JSONObject();
		json.put("medicalCaseList", a);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}

	/**
	 * 得到病人的总页数
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/totalPage")
	@ResponseBody  
	public void getTotalPage(HttpServletResponse response) throws IOException
	{
		//新建一个page对象，将当前页数和医生总数，以及每页显示的数量传递进去
		Page page = new Page(1,service.selectCount(),2);	
		JSONObject json = new JSONObject();
		json.put("page", page.getPageCount());
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());
	}
	
	/**
	 * 刪除病例
	 * @param PatientId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/delete/{CaseId}")
	@ResponseBody
	public void deleteCaseById(@PathVariable int CaseId,HttpServletResponse response)throws IOException
	{
		service.deleteCaseById(CaseId);
	}
}
