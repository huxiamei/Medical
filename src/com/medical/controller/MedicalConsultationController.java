package com.medical.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.Doctor;
import com.medical.model.Hospital;
import com.medical.model.HospitalDepartment;
import com.medical.model.MedicalConsultation;
import com.medical.model.ResultDict;
import com.medical.model.User;
import com.medical.service.DoctorService;
import com.medical.service.HospitalDepartmentService;
import com.medical.service.HospitalService;
import com.medical.service.MedicalConsultationService;
import com.medical.tool.JsonUtil;
import com.medical.tool.MsgDTO;
import com.medical.tool.Page;
import com.medical.tool.myenum.ConStatus;

/**
 * @Description: MedicalConsultation Controller
 * @author: linai
 * @date： 日期：Dec 20, 2016 时间：5:21:17 PM
 * @version 1.0
 */

@Controller
@RequestMapping("/MedicalConsultation")
public class MedicalConsultationController {

	// MedicalConsultation Service
	@Autowired
	@Qualifier("MedicalConsultationService")
	MedicalConsultationService medicalConsultationService;
	@Autowired
	@Qualifier("HospitalService")
	HospitalService hospitalService;
	
	@Autowired
	@Qualifier("DoctorService")
	DoctorService service;
	
	@Autowired
	@Qualifier("HospitalDepartmentService")
	HospitalDepartmentService departmentService;

	

	/**
	 * @Description:添加会诊表
	 * @param medicalConsultation
	 * @param bindingResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ResponseBody
	public void add(
			@ModelAttribute(value = "medicalConsultation") MedicalConsultation medicalConsultation,
			BindingResult bindingResult, HttpServletResponse response)
			throws IOException {
		System.out.println(medicalConsultation.getDoctor()
				+ "dddddddddddddddddddddddddd");

		medicalConsultationService.addMedicalConsultation(medicalConsultation);
	}

	/**
	 * @Description:查看所有会诊表单
	 * @param medicalConsultation
	 * @param bindingResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/viewAll")
	@ResponseBody
	@GET
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO viewAll() throws IOException {
		MsgDTO msgDTO = new MsgDTO();
		try {
			List<MedicalConsultation> medicalConsultationList = medicalConsultationService
					.queryAll();
			System.out.println(medicalConsultationList);
			msgDTO.setData(medicalConsultationList);
		} catch (Exception e) {
			e.printStackTrace();
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}

	/**
	 * @Description:查看所有医院
	 * @param medicalConsultation
	 * @param bindingResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getHospital")
	@ResponseBody
	@GET
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO getHospital() throws IOException {
		MsgDTO msgDTO = new MsgDTO();
		try {
			List<Hospital> hospitalList = hospitalService.findHospital(new Page(1,hospitalService.selectHospitalCount(),Integer.MAX_VALUE));  //所有医院
			System.out.println(hospitalList);
			msgDTO.setData(hospitalList);
		} catch (Exception e) {
			e.printStackTrace();
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}
	/**
	 * @Description:显示某张表的详情
	 * @param id
	 * @param bindingResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/viewDetail")
	@ResponseBody
	@GET
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO viewDetail(@QueryParam("id") int id) throws IOException {
		System.out.println(id + "dddddddddddddddddddddddddd");
		MsgDTO msgDTO = new MsgDTO();
		try {
			MedicalConsultation medicalConsultation = medicalConsultationService.selectMedicalConsultationById(id);
			msgDTO.setData(medicalConsultation);
		} catch (Exception e) {
			e.printStackTrace();
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}

	/**
	 * @Description:修改某张表的详情
	 * @param id
	 * @param bindingResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/update", method = { RequestMethod.POST })
	@ResponseBody
	@POST
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO updateDetail(@RequestBody MedicalConsultation consultation)
			throws IOException {
		System.out.println(consultation);
		MsgDTO msgDTO = new MsgDTO();
		try {
			medicalConsultationService.updateConsultationStatus(consultation.getId(),consultation.getCon_status());
		} catch (Exception e) {
			e.printStackTrace();
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}

	/**
	 * @Description:删除某张表
	 * @param id
	 * @param bindingResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.POST })
	@ResponseBody
	@POST
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO delete(@RequestBody MedicalConsultation consultation)
			throws IOException {
		System.out.println(consultation);
		MsgDTO msgDTO = new MsgDTO();
		try {
			medicalConsultationService.deleteMedicalConsultationById(consultation.getId());
		} catch (Exception e) {
			e.printStackTrace();
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}
		
	@RequestMapping(value = "/viewByDoctorIdAndChoose")
	@ResponseBody
	@GET
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO viewByDoctorIdAndChoose(@QueryParam("choose") int choose,HttpSession session) throws IOException {
		MsgDTO msgDTO = new MsgDTO();
		try {
			User u = (User) session.getAttribute("CurrentUser");
			List<MedicalConsultation> medicalConsultationList = medicalConsultationService.viewByDoctorIdAndChoose(u.getId(),choose);
			msgDTO.setData(medicalConsultationList);
		} catch (Exception e) {
			e.printStackTrace();
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}
	
	/**
	 * @Description: 获取结果类型
	 * @return msgDTO
	 */
	@RequestMapping(value = "/getResultDict")
	@ResponseBody
	@GET
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO getResultDict() {
		MsgDTO msgDTO = new MsgDTO();
		try {
			List<ResultDict> resultDictList = medicalConsultationService.getResultDict();
			msgDTO.setData(resultDictList);
		} catch (Exception e) {
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}
	/**
	 * @Description: 获取总页数
	 * @return msgDTO
	 */
	@RequestMapping(value = "/getTotalPage")
	@ResponseBody
	@GET 
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO getTotalPage() throws IOException
	{
		//新建一个page对象，将当前页数和医生总数，以及每页显示的数量传递进去	
		MsgDTO msgDTO = new MsgDTO();
		try {
			Page page = new Page(1,medicalConsultationService.selectCount(),10);
			msgDTO.setData(page.getPageCount());
		} catch (Exception e) {
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}
	
	/**
	 * @Description:查看所有会诊表单
	 * @param medicalConsultation
	 * @param bindingResult
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/viewAllByPage")
	@ResponseBody
	@GET
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO viewAllByPage(@QueryParam("pageNo") int pageNo) throws IOException {
		MsgDTO msgDTO = new MsgDTO();
		try {
			int count = medicalConsultationService.selectCount();
			List<MedicalConsultation> medicalConsultationList=null;
			if(count>0)
			{
				//得到页对象
				Page page = new Page(pageNo,count,10);					
				//得到当前页数下的所有医生对象
				medicalConsultationList = medicalConsultationService.queryAllByPage(page);					
			}
			msgDTO.setData(medicalConsultationList);
		} catch (Exception e) {
			e.printStackTrace();
			msgDTO.setStatus(MsgDTO.STATUS_FAIL);
			msgDTO.setMessage(e.getMessage());
		}
		return msgDTO;
	}
	
	/**
	 * @author linai
	 * 根据departmentid得到他的所有医生
	 * @param depId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/doctorDict")
	@ResponseBody
	@GET
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO doctorDict(@QueryParam("depId") int depId) throws IOException
	{
		MsgDTO msgDTO = new MsgDTO();
	try {
		 List<Doctor> doctorList = service.selectDoctorByDepartmentId(depId);	
		 msgDTO.setData(doctorList);
	} catch (Exception e) {
		e.printStackTrace();
		msgDTO.setStatus(MsgDTO.STATUS_FAIL);
		msgDTO.setMessage(e.getMessage());
	}
	return msgDTO;
  }
	/**
	 * @author linai
	 * 根据医院的id得到他的所有部门
	 * @param hospitalId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/departmentDict")
	@ResponseBody
	@GET
	@Consumes({ "application/json", "application/xml" })
	public MsgDTO departmentDict(@QueryParam("id") int id) throws IOException
	{
		MsgDTO msgDTO = new MsgDTO();
	try {
		 List<HospitalDepartment> departmentList = departmentService.selectDepartmentByHospitalId(id);	
		msgDTO.setData(departmentList);
	} catch (Exception e) {
		e.printStackTrace();
		msgDTO.setStatus(MsgDTO.STATUS_FAIL);
		msgDTO.setMessage(e.getMessage());
	}
	return msgDTO;
  }	
	@RequestMapping(value = "/getConsultationDetail/{con_id}", method = {RequestMethod.GET})
	@ResponseBody
	public void getConsultationDetail(@PathVariable int con_id,HttpServletResponse response) throws IOException
	{
		MedicalConsultation detail = medicalConsultationService.selectMedicalConsultationById(con_id);
		Date date = detail.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String conTime = sdf.format(date);
		JSONObject json = new JSONObject();
		json.put("medicalConsultationDetail", detail);
		json.put("con_time", conTime);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
	
	/**
	 * 得到该病人所有会诊记录
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("null")
	@RequestMapping(value = "/getMedicalConsultationList/{PatientId}", method = {RequestMethod.GET})
	@ResponseBody
	public void getMedicalConsultationList(@PathVariable int PatientId,HttpServletResponse response) throws IOException
	{
		List<MedicalConsultation> medicalConsultationList = medicalConsultationService.selectMedicalConsultationsByPatientId(PatientId);
		List<String> conTimeList=new ArrayList<String>();
		String conTime=null;
		for(int i=0;i<medicalConsultationList.size();i++){
			Date date=medicalConsultationList.get(i).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			conTime = sdf.format(date);
			conTimeList.add(conTime);
		}
		
		JSONArray a = JsonUtil.toJSONArray(medicalConsultationList);
		JSONArray b = JsonUtil.toJSONArray(conTimeList);
		JSONObject json = new JSONObject();
		json.put("medicalConsultationList", a);
		json.put("conTimeList", b);
		
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
	@RequestMapping(value = "/show/{pageNo}")
	@ResponseBody  
	public void showList(HttpSession session,@PathVariable(value = "pageNo")int pageNo,HttpServletResponse response) throws IOException
	{
		
		User user = (User) session.getAttribute("CurrentUser");  //得到当前登录用户
		Page page = new Page(pageNo,medicalConsultationService.selectMedicalConsultationCount(user.getId()),5);	 
		List<MedicalConsultation> medicalConsultationList = medicalConsultationService.findMedicalConsultation(page, user.getId());  //得到当前页面的所有会诊
		List<String> conTimeList=new ArrayList<String>();
		List<String> statusType =  new ArrayList<String>();   //会诊类型
		String conTime=null;
		for(int i=0;i<medicalConsultationList.size();i++){
			Date date=medicalConsultationList.get(i).getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			conTime = sdf.format(date);
			conTimeList.add(conTime);
			ConStatus c = ConStatus.valueOfEnum(medicalConsultationList.get(i).getCon_status());
			statusType.add(c.getName());
		}
		
		JSONArray a = JsonUtil.toJSONArray(medicalConsultationList);
		JSONArray b = JsonUtil.toJSONArray(conTimeList);
		JSONArray c= JsonUtil.toJSONArray(statusType);
		
		JSONObject json = new JSONObject();
		json.put("medicalConsultationList", a);
		json.put("conTimeList", b);
		json.put("statusType", c);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}

	/**
	 * 得到页面总数
	 * @param response
	 * @throws IOException
	 * 
	 * summer
	 */
	@RequestMapping(value = "/totalPage")
	@ResponseBody  
	public void getTotalPage(HttpSession session,HttpServletResponse response) throws IOException
	{
		User user = (User) session.getAttribute("CurrentUser");
			
		Page totalpage = new Page(1,medicalConsultationService.selectMedicalConsultationCount(user.getId()),5);	
		JSONObject json = new JSONObject();
		
		json.put("totalpage", totalpage.getPageCount());
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());
	}	
}
