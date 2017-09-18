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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.MedicalNotification;
import com.medical.model.User;
import com.medical.service.MedicalNotificationService;
import com.medical.tool.JsonUtil;
import com.medical.tool.Page;

@Controller
@RequestMapping("/Message")
public class MedicalNotificationController {

	@Autowired
	@Qualifier("MedicalNotificationService")
	MedicalNotificationService service ;
	
	/**
	 * 删除信息
	 * @param messageId 要删除的信息的id
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/delete/{messageId}")
	@ResponseBody  
	public void deleteMessage(@PathVariable int messageId,HttpServletResponse response) throws IOException
	{
		service.deleteMessage(messageId); //调用service删除信息
		JSONObject json = new JSONObject();
		json.put("message", "删除成功");  //返回到前台
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}
	
	@RequestMapping(value = "/show/{pageNo}/{send}")
	@ResponseBody  
	public void showList(HttpSession session,@PathVariable(value = "pageNo")int pageNo,@PathVariable(value = "send")String send,HttpServletResponse response) throws IOException
	{
		boolean s = true;
		if(send.equals("R"))  //接收到的信息
		{
			s = false;		
		}
		User user = (User) session.getAttribute("CurrentUser");  //得到当前登录用户
		Page page = new Page(pageNo,service.selectMessageCount(user.getId(), s),5);	 
		List<MedicalNotification> messageList = service.findMessage(page, user.getId(), s);  //得到当前页面的所有发送信息集
		
		List<String> sendTimeList = new ArrayList<String>();  //信息发送时间
		List<String> messageType =  new ArrayList<String>();   //信息类型
		for(int i=0;i<messageList.size();i++)  //转化时间
		{
			messageType.add(messageList.get(i).getInfor_type().getName());
			Date date = messageList.get(i).getSend_time();		
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			String startTime = sdf.format(date);
			sendTimeList.add(startTime);
		}
		JSONArray a = JsonUtil.toJSONArray(messageList);
		JSONObject json = new JSONObject();
		json.put("MessageList", a);  //返回到前台
		
		JSONArray b = JsonUtil.toJSONArray(sendTimeList);
		json.put("sendTimeList", b);  //返回到前台
		
		JSONArray c = JsonUtil.toJSONArray(messageType);
		json.put("messageType", c);  //返回到前台
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());  
	}
	
	
	/**
	 * 得到页面总数
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/totalPage")
	@ResponseBody  
	public void getTotalPage(HttpSession session,HttpServletResponse response) throws IOException
	{
		User user = (User) session.getAttribute("CurrentUser");
			
		Page sendpage = new Page(1,service.selectMessageCount(user.getId(), true),5);	
		Page receivepage = new Page(1,service.selectMessageCount(user.getId(), false),5);	
		JSONObject json = new JSONObject();
		
		json.put("sendpage", sendpage.getPageCount());
		json.put("receivepage", receivepage.getPageCount());
		
		PrintWriter out = response.getWriter();  	
	    out.print(json.toString());
	}	

}
