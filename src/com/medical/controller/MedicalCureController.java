package com.medical.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medical.model.MedicalConsultation;
import com.medical.model.MedicalCure;
import com.medical.model.User;
import com.medical.service.MedicalConsultationService;
import com.medical.service.MedicalCureService;
import com.medical.tool.MsgDTO;
import com.medical.tool.Page;
import com.medical.tool.SendMessage;
import com.medical.tool.myenum.ConStatus;

@Controller
@RequestMapping("/MedicalCure")
public class MedicalCureController {
		// MedicalCure Service
		@Autowired
		@Qualifier("MedicalCureService")
		MedicalCureService medicalCureService;

		@Autowired
		@Qualifier("MedicalConsultationService")
		MedicalConsultationService medicalConsultationService;
		
		@Autowired
		@Qualifier("SendMessage")
		SendMessage send;
		/**
		 * @Description:查看所有治疗方案
		 * @param medicalCureService
		 * @param bindingResult
		 * @param response
		 * @throws IOException
		 */
		@RequestMapping(value = "/viewAll")
		@ResponseBody
		@GET
		@Consumes({ "application/json", "application/xml" })
		public MsgDTO viewAll(@QueryParam("pageNo") int pageNo) throws IOException {
			MsgDTO msgDTO = new MsgDTO();
			try {
				int count = medicalCureService.selectCount();
				List<MedicalCure> medicalCureServiceList=null;
				if(count>0)
				{
					//得到页对象
					Page page = new Page(pageNo,count,10);					
					//得到当前页数下的所有治疗方案
				 medicalCureServiceList = medicalCureService.queryAll(page);
				System.out.println(medicalCureServiceList);
				}
				msgDTO.setData(medicalCureServiceList);
				
			} catch (Exception e) {
				e.printStackTrace();
				msgDTO.setStatus(MsgDTO.STATUS_FAIL);
				msgDTO.setMessage(e.getMessage());
			}
			return msgDTO;
		}
		
		/**
		 * 编写治疗方案
		 */
		@RequestMapping(value = "/editCure")
		@ResponseBody
		@POST
		@Consumes({ "application/json", "application/xml" })
		public MsgDTO editCure(@RequestBody MedicalCure cure,HttpSession session) throws IOException {
			MsgDTO msgDTO = new MsgDTO();
			try {
				User user = (User) session.getAttribute("CurrentUser");
				MedicalConsultation medicalConsultation = medicalCureService.editCure(cure.getConsultation_id(),cure.getPlan());
				//修改会诊表状态
				medicalConsultationService.updateConsultationStatus(cure.getConsultation_id(),ConStatus.WirteCurePlan.getCode());
				//发送消息
				send.SendWriteResultOverMessage(user,cure.getConsultation_id());
		        send.SendPayMessage(user,cure.getConsultation_id());

				msgDTO.setData(medicalConsultation);
			} catch (Exception e) {
				e.printStackTrace();
				msgDTO.setStatus(MsgDTO.STATUS_FAIL);
				msgDTO.setMessage(e.getMessage());
			}
			return msgDTO;
		}
//		/**
//		 * @Description:显示某张表的详情
//		 * @param id
//		 * @param bindingResult
//		 * @param response
//		 * @throws IOException
//		 */
//		@RequestMapping(value = "/viewDetail", method = { RequestMethod.GET })
//		@ResponseBody
//		public void viewDetail(
//				@ModelAttribute(value = "medicalConsultationId") int id,
//				BindingResult bindingResult, HttpServletResponse response)
//				throws IOException {
//			System.out.println(id + "dddddddddddddddddddddddddd");
//
//			medicalConsultationService.selectMedicalConsultationById(id);
//		}
//
//		/**
//		 * @Description:修改某张表的详情
//		 * @param id
//		 * @param bindingResult
//		 * @param response
//		 * @throws IOException
//		 */
//		@RequestMapping(value = "/manage/update/viewDetail", method = { RequestMethod.POST })
//		@ResponseBody
//		public void updateDetail(
//				@ModelAttribute(value = "medicalConsultation") MedicalConsultation medicalConsultation,
//				BindingResult bindingResult, HttpServletResponse response)
//				throws IOException {
//			System.out.println(medicalConsultation + "dddddddddddddddddddddddddd");
//
//			medicalConsultationService
//					.updateMedicalConsultationById(medicalConsultation);
//		}
//
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
		public MsgDTO delete(@RequestBody MedicalCure medicalCure)
				throws IOException {
			System.out.println(medicalCure);
			MsgDTO msgDTO = new MsgDTO();
			try {
				medicalCureService.deleteMedicalConsultationById(medicalCure);
			} catch (Exception e) {
				e.printStackTrace();
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
				Page page = new Page(1,medicalCureService.selectCount(),10);
				msgDTO.setData(page.getPageCount());
			} catch (Exception e) {
				msgDTO.setStatus(MsgDTO.STATUS_FAIL);
				msgDTO.setMessage(e.getMessage());
			}
			return msgDTO;
		}

}