package com.medical.tool.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.medical.model.SysAdmin;
import com.medical.model.User;

public class LoginInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		 //获取请求的URL  
        String url = request.getRequestURI();  
    
        if(url.indexOf("/jsp/frontPage/login.jsp")>=0){  
            return true;  
        }  
        //获取Session  
        HttpSession session = request.getSession();  
        int role = -1;
        if(session.getAttribute("role")!=null)
    	{   	      
        	role = (Integer) session.getAttribute("role");
    	}
        if(role == 0)  //管理员登录
        {
        	SysAdmin user = (SysAdmin) session.getAttribute("CurrentUser");
        	  if(user != null){  
        		  
        		  if(url.indexOf("/jsp/frontPage")>=0)  //管理员登录访问前台代码
        		  {
        			  request.getRequestDispatcher("/jsp/frontPage/login.jsp").forward(request, response); 
        			  return false;
        		  }
        		  else
        		  {
        			  return true;  
        		  }
                 
              } 
        } 
        else if(role>0)  //医生或者病人登录
        {
        	  User user= (User)session.getAttribute("CurrentUser");  
        	  if(user != null){  
        		  if(url.indexOf("/jsp/frontPage")>=0)  
        		  {
        			  return true;
        		  }
        		  else  //前台登录访问后台代码
        		  {
        			  request.getRequestDispatcher("/jsp/frontPage/login.jsp").forward(request, response); 
        			  return false;
        		  }     			          
              } 
        }
                
       
        //不符合条件的，跳转到登录界面  
        request.getRequestDispatcher("/jsp/frontPage/login.jsp").forward(request, response);           
        return false;  
	}

}
