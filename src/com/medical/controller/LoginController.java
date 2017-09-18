package com.medical.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medical.model.SysAdmin;
import com.medical.model.User;
import com.medical.service.AdminService;
import com.medical.service.UserService;

@Controller
@RequestMapping("/Login")
public class LoginController {
	
	
	@Autowired
	@Qualifier("UserService") 
	UserService userservice;
	
	@Autowired
	@Qualifier("AdminService") 
	AdminService adminservice;
	
	
	@RequestMapping(value="/index")
	public String ToLogin()
	{
		return "frontPage/login";
	}
	
	@RequestMapping(value="/AdminLogin")
	public void adminLogin(HttpSession session,String userId,String password,HttpServletResponse response) throws IOException
	{
		System.out.println("admin   login");
		System.out.println(userId+password);
		int Id = Integer.parseInt(userId);
		String message = "";
		int success = 1;
		String url = "";
		if(adminservice.adminLogin(Id, password))
		{
			message = "登录成功";
			
			//将用户放入session中
		    SysAdmin admin = adminservice.selectAdmin(Id);
		    session.setAttribute("CurrentUser", admin);
		    session.setAttribute("role", admin.getRole_id());
		    
			url = "jsp/manage/index.jsp";
		}else
		{
			success = -1;
			message = "没有这个用户或者密码错误";
		}
		JSONObject json = new JSONObject();	
		json.put("message", message);
		json.put("success", success);
		json.put("url", url);
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	    		
	}
	
	@RequestMapping(value="/UserLogin")
	public void userLogin(HttpSession session,String userId,String password,HttpServletResponse response) throws IOException
	{
		int Id = Integer.parseInt(userId);
		String message = "";
		int success = 1;
		String url = "";
		if(userservice.userLogin(Id, password))
		{
			User user = userservice.selectUser(Id);
			session.setAttribute("CurrentUser", user);
			session.setAttribute("role", user.getRole_id());
			System.out.println("login in");
			if(user.getRole_id() == 3) //病人登录
			{
				System.out.println("login in  patient");
				url = "jsp/frontPage/patient_info.jsp";
			}
			else  //医生登录
			{
				System.out.println("login in doctor");
				url = "jsp/frontPage/doctor_info.jsp";
			}
			
			message = "登录成功";			
		}
		else
		{
			success = -1;
			message = "没有这个用户或者密码错误";
		}
		JSONObject json = new JSONObject();	
		json.put("message", message);
		json.put("success", success);
		json.put("url", url);
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
		
	}
	
	@RequestMapping(value="/ChangePassword")
	public void changePassword(HttpSession session,String oldPassword,String newPassword,HttpServletResponse response) throws IOException
	{
		User user = (User) session.getAttribute("CurrentUser");
		String message = "";
		int success = -1;
		if(user.getUser_password().equals(oldPassword))
		{
			success = 1;
			user.setUser_password(newPassword);
			message = "修改成功,请重新登录系统";
			userservice.updateUserPassword(user.getId(), newPassword);
		}
		else
		{
			message = "输入的旧密码错误，请重新输入";
		}
			
		JSONObject json = new JSONObject();	
		json.put("message", message);
		json.put("success", success);
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString()); 
	}
	
}
