package com.medical.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.medical.tool.MsgDTO;

@RequestMapping("upload")
@Controller
public class UploadFile {

	/*
     *采用spring提供的上传文件的方法
     */
	@RequestMapping("springUpload")
	@ResponseBody 
    public JSONObject springUpload(HttpServletRequest request) throws IllegalStateException, IOException {
       
		  JSONObject jsonObject = new JSONObject();
		  request.setCharacterEncoding("utf-8");  
	      RequestContext requestContext = new ServletRequestContext(request);  
	      
	      String basePath = "c:/upload";
	      System.out.println(basePath);
	      
	      if(FileUpload.isMultipartContent(requestContext)){  
	      
	            DiskFileItemFactory factory = new DiskFileItemFactory();  
	            factory.setRepository(new File(basePath));  
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	            upload.setSizeMax(2000000);  
	            List items = new ArrayList();  
	            try {  
	               items = upload.parseRequest(request);  
	            } catch (FileUploadException e1) {  
	               System.out.println("文件上传发生错误" + e1.getMessage());  
	            }  
	  
	            Iterator it = items.iterator();  
	            while(it.hasNext()){  
	                FileItem fileItem = (FileItem) it.next();  
	                if(fileItem.isFormField()){        
	                       System.out.println(fileItem.getFieldName() + "   " + fileItem.getName() + "   " + new String  
	                                           (fileItem.getString().getBytes("iso8859-1"), "gbk"));  
	                }else{  
	                       System.out.println(fileItem.getFieldName() + "   " +   
	                               fileItem.getName() + "   " + fileItem.isInMemory() + "    " +   
	                               fileItem.getContentType() + "   " + fileItem.getSize());  
	        
	                       if(fileItem.getName()!=null && fileItem.getSize()!=0){  
	                               File fullFile = new File(fileItem.getName());  
	                               File newFile = new File(basePath + fullFile.getName());  
	                             
	                               try {  
	                                   fileItem.write(newFile);  
	                                   jsonObject.put("file", newFile.getPath());
	                               } catch (Exception e) {  
	                                      e.printStackTrace();  
	                               }  
	                       }else{  
	                               System.out.println("文件没有选择 或 文件内容为空");  
	                       }  
	                }  
	        
	            }  
	       }  

        return jsonObject; 
    }
}

