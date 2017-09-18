package com.medical.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.Doctor;
import com.medical.model.MedicalConsultation;
import com.medical.model.Patient;
import com.medical.model.User;
import com.medical.service.HospitalService;
import com.medical.service.MedicalConsultationService;
import com.medical.service.PatientService;
import com.medical.tool.JsonUtil;
import com.medical.tool.Page;
import com.medical.tool.SendMessage;
import com.medical.tool.myenum.Blood;
import com.medical.tool.myenum.ConStatus;


@Controller
@RequestMapping("/Patient")
public class PatientController {
	
	@Autowired
	@Qualifier("PatientService")
	PatientService service;
	
	@Autowired
	@Qualifier("MedicalConsultationService")
	MedicalConsultationService cs;
	
	@Autowired
	@Qualifier("SendMessage")
	SendMessage send;
	
	@Autowired
	@Qualifier("HospitalService")
	HospitalService hospitalservice;
	
	
	@RequestMapping(value="/index")
	public String index(Model model)
	{
		if(!model.containsAttribute("PatientInfo"))
		{
			Doctor doctor = new Doctor();
			Patient patient = new Patient();
			User user = new User();
			double account=0;
			Blood blood = null;
			
			patient.setUser(user);
			patient.setBlood(blood);
			patient.setDoctor(doctor);
			patient.setAccount(account);
			
			model.addAttribute("PatientInfo", patient);
		}		
		return "manage/patient";
	}
	
	/**
	 * 修改会诊评价
	 * @param con_id 会诊ID
	 * @param evaluate 评价
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/WriteEvaluate/{con_id}/{evaluate}", method = {RequestMethod.GET})
	@ResponseBody
	public void WriteEvaluate(@PathVariable int con_id,@PathVariable String evaluate,HttpSession session,HttpServletResponse response) throws IOException
	{
		MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id);//会诊对象
		//修改评价
		cs.updateEvaluate(con_id, evaluate);
		//修改会诊状态（已填写评价）
		cs.updateConsultationStatus(consultation.getId(),ConStatus.WriteEvaluate.getCode());
		//发送消息
		send.SendConsultationEvaluate(con_id);//已经填写完毕评价
	}
	
	/**
	 * 病人同意会诊
	 * @param con_id 相关会诊id
	 * @param agree  是否同意会诊
	 * @param session  
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/CheckConsultation/{con_id}/{agree}", method = {RequestMethod.GET})
	@ResponseBody
	public void CheckConsultation(@PathVariable int con_id,@PathVariable int agree,HttpSession session,HttpServletResponse response) throws IOException
	{
		try
		{
			MedicalConsultation consultation = cs.selectMedicalConsultationById(con_id);//会诊对象
			User user = (User) session.getAttribute("CurrentUser");
			if(agree==1)//同意会诊
			{			
				//修改会诊状态
				cs.updateConsultationStatus(consultation.getId(),ConStatus.PatientAgree.getCode());
				//发送消息
				send.SendAgreeOrNotMessage(user, con_id, true); //同意会诊信息
			}
			else
			{				
				//修改会诊状态
				cs.updateConsultationStatus(consultation.getId(),ConStatus.PatientNotAgree.getCode());
				//发送消息
				send.SendAgreeOrNotMessage(user, con_id, false);
			}
		} catch (Exception ex)
        {
			ex.printStackTrace();
		}
		
	}
	/**
	 * 添加病人
	 * @param patient
	 * @param response
	 * @param bindingResult
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/add", method = {RequestMethod.POST})
	@ResponseBody	
	public void add(@ModelAttribute(value="PatientInfo")Patient patient,HttpServletResponse response,BindingResult bindingResult) throws IOException 
	{
		//json对象
		JSONObject json = new JSONObject();	
		
		//前台表单对象输入的参数不符合要求
		if(bindingResult.hasErrors()){
			System.out.println("***************************************************************************************");
		    json.put("message", "新增病人信息失败");
		    
		    //将操作失败的信息通过json传递到前台，前台弹框显示
		    PrintWriter out = response.getWriter();  	
		    out.print(json.toString()); 
		 }else{
			//调用service层函数保存病人信息
			 patient.getUser().setRole_id(3);
			service.addPatient(patient.getUser(), patient.getBlood(),patient.getDoctor().getUser().getId(), patient.getAccount());	
			json.put("message", "新增病人信息成功");
			
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
	 * 跳转到修改病人信息
	 * @param PatientId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{PatientId}", method = {RequestMethod.GET})
	@ResponseBody
	public void toUpdate(@PathVariable(value="PatientId") int PatientId,HttpServletResponse response) throws IOException
	{
		Patient patient = service.selectPatientById(PatientId);		
		
		Date date = patient.getUser().getBirthdate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthdate = sdf.format(date);
		
		JSONObject json = new JSONObject();
		json.put("updatePatient", patient);
		json.put("patbirthdate", birthdate);
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
	/**
	 * 刪除病人
	 * @param PatientId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/manage/delete/{PatientId}")
	@ResponseBody
	public void deletePatientById(@PathVariable int PatientId,HttpServletResponse response)throws IOException
	{
		service.deletePatientById(PatientId);
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
	 * 得到分页病人信息
	 * @param session
	 * @param pageNo
	 * @param response
	 * @throws IOException
	 */
	//修改返回参数等信息，和json，ajax一起使用，传递给前台
	@RequestMapping(value = "/manage/list/{pageNo}")
	@ResponseBody  
	public void showList(HttpSession session,@PathVariable(value = "pageNo")int pageNo,HttpServletResponse response) throws IOException
	{
		int count = service.selectCount();
		List<Patient> patientList = new ArrayList<Patient>();
		
		if(count>0){
			Page page = new Page(pageNo,service.selectCount(),2);	
			patientList = service.findPatient(page);
		}
		JSONArray a = JsonUtil.toJSONArray(patientList);
		JSONObject json = new JSONObject();
		json.put("patientList", a);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}
	
	/**
	 * 根据病人id获得病人信息
	 * @param PatientId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getPatient/{PatientId}", method = {RequestMethod.GET})
	@ResponseBody  
	public void selectPatientById(@PathVariable int PatientId,HttpServletResponse response) throws IOException
	{
		//调用service层函数查找到指定id的病人对象
		Patient patient = service.selectPatientById(PatientId);
		
		//转化病人的出生日期，以字符串的形式传递到前台，防止前台无法识别日期对象
		Date date = patient.getUser().getBirthdate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String birthdate = sdf.format(date);
		
		JSONObject json = new JSONObject();
		json.put("patient",patient);
		json.put("birthdate",birthdate);		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}
	
	/**
	 * 前台修改病人信息
	 * @param user  要修改的病人的信息
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/updatePatient", method = {RequestMethod.POST})
	@ResponseBody
	public void updatePatient(HttpSession session,User user,HttpServletResponse response) throws IOException
	{	
		Patient patient = service.selectPatientById(user.getId());
		user.setUser_password(patient.getUser().getUser_password());
		user.setIdCard(patient.getUser().getIdCard());
		user.setRole_id(patient.getUser().getRole_id());
		
		JSONObject json = new JSONObject();			
		//调用service层的函数，修改数据库病人信息
		
		service.updatePatient(user);
		json.put("message", "修改成功");
		session.setAttribute("CurrentUser", user);
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());   //返回提示信息	
	}
	
	/**
	 * 修改病人信息
	 * @param patient  要修改的病人的信息
	 * @param bindingResult  出错验证
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/manage/update/{PatientId}", method = {RequestMethod.POST})
	@ResponseBody
	public void update(@ModelAttribute(value="PatientInfo")Patient patient,BindingResult bindingResult ,HttpServletResponse response) throws IOException
	{				
		JSONObject json = new JSONObject();
		
		//验证表单字段是否有错，返回提示信息
		if(bindingResult.hasErrors())
		 {  
		    json.put("message", "update error");
		 }	
		else
		{
			System.out.println(patient.getUser().getUser_name());
			//调用service层的函数，修改数据库病人信息
			patient.getUser().setRole_id(3);
			service.updatePatient(patient);
			json.put("message", "修改成功");
		}
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());   //返回提示信息	
		
	}
	
	/**
	 * 充值
	 * @param session
	 * @param PatientId
	 * @param account
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/recharge/{PatientId}", method = {RequestMethod.POST})
	@ResponseBody
	public void recharge(HttpSession session,@PathVariable int PatientId,double account ,HttpServletResponse response) throws IOException
	{	
		Patient patient=service.selectPatientById(PatientId);
		double pay = patient.getAccount() - account;
		patient.setAccount(account);
		double hosMoney = patient.getDoctor().getHospitalDepartment().getHospital().getAccount();
		patient.getDoctor().getHospitalDepartment().getHospital().setAccount(hosMoney+pay);
				
		//修改病人余额
		service.updatePatient(patient);	
	
	
		JSONObject json = new JSONObject();	
		json.put("message", "修改成功");
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());   //返回提示信息	
		
	}
	
	
	/**
	 * 病人支付
	 * @param user  要修改的病人的信息
	 * @param response  和前台进行交互的参数，用来传递json对象回前台
	 * @throws IOException
	 */
	@RequestMapping(value = "/goToPay/{PatientId}/{con_id}", method = {RequestMethod.POST})
	@ResponseBody
	public void goToPay(HttpSession session,@PathVariable int PatientId,@PathVariable int con_id,double account ,HttpServletResponse response) throws IOException
	{	
		Patient patient=service.selectPatientById(PatientId);
		double pay = patient.getAccount() - account;
		patient.setAccount(account);
		double hosMoney = patient.getDoctor().getHospitalDepartment().getHospital().getAccount();
		patient.getDoctor().getHospitalDepartment().getHospital().setAccount(hosMoney+pay);
		
		//修改医院金额
		hospitalservice.updateHospital(patient.getDoctor().getHospitalDepartment().getHospital());
		//修改病人余额
		service.updatePatient(patient);	
		//修改会诊状态
		
		cs.updateConsultationStatus(con_id,ConStatus.ConsulationOver.getCode());
		//发送消息
		send.SendConsultationOverMessage(con_id); //病人缴费完毕，会诊完成			
	
		JSONObject json = new JSONObject();			
		json.put("message", "修改成功");
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());   //返回提示信息	
		
	}
}
