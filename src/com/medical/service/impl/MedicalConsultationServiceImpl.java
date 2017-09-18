package com.medical.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.DoctorMapper;
import com.medical.mapper.MedicalConsultationMapper;
import com.medical.mapper.MedicalSelectDepartmentMapper;
import com.medical.mapper.ResultDictMapper;
import com.medical.model.Doctor;
import com.medical.model.MedicalConsultation;
import com.medical.model.MedicalSelectDepartment;
import com.medical.model.ResultDict;
import com.medical.service.MedicalConsultationService;
import com.medical.tool.Page;

/**
 * @Description: MedicalConsultation Service 实现类
 * @author: linai
 * @date： 日期：Dec 20, 2016 时间：5:33:48 PM
 * @version 1.0
 */
@Service("MedicalConsultationService")
public class MedicalConsultationServiceImpl implements
		MedicalConsultationService {
	@Resource
	private MedicalConsultationMapper medicalConsultationMapper;
	@Resource  
	private MedicalSelectDepartmentMapper medicalSelectDepartmentMapper;
	@Resource
	private DoctorMapper doctorMapper;
	@Resource
	private ResultDictMapper resultDictMapper;

	// 添加会诊表
	public int addMedicalConsultation(MedicalConsultation medicalConsultation) {
		int num = medicalConsultationMapper.insertSelective(medicalConsultation);
		return num;
	}

	// 查看某张会诊表详情
	public MedicalConsultation selectMedicalConsultationById(int id) {
		MedicalConsultation medicalConsultation=medicalConsultationMapper.selectByPrimaryKey(id);
		List<MedicalSelectDepartment> list=medicalSelectDepartmentMapper.selectByConsultationId(medicalConsultation.getId());
		medicalConsultation.setSelect_department(list);
		return medicalConsultation;
	}
	
	// 查看某张会诊表详情
	public int updateMedicalConsultationById(MedicalConsultation medicalConsultation) {
		int num=medicalConsultationMapper.updateByPrimaryKey(medicalConsultation);
		return num;
	}

	//删除会诊表
	public int deleteMedicalConsultationById(int id) {
		int num=medicalConsultationMapper.deleteByPrimaryKey(id);
		return num;	
	}
	
	//查询所有会诊表
	public List<MedicalConsultation> queryAll() {
		List<MedicalConsultation> medicalConsultationList=medicalConsultationMapper.queryAll();
			for (MedicalConsultation medicalConsultation : medicalConsultationList) {
				List<MedicalSelectDepartment> list=medicalSelectDepartmentMapper.selectByConsultationId(medicalConsultation.getId());
				medicalConsultation.setSelect_department(list);
		}
		return medicalConsultationList;
	}

	//根据医生选择对应的会诊信息
	public List<MedicalConsultation> viewByDoctorIdAndChoose(int doctorId,int choose) {
		List<MedicalConsultation> medicalConsultationlist=new ArrayList<MedicalConsultation>() ;
		if (choose == 1) {
			// 查询我参与的会诊
			List<Integer> consultationIdList = medicalSelectDepartmentMapper.selectByDoctorId(doctorId);
			System.out.println(consultationIdList.size());
			if(consultationIdList.size()!=0){
				for (Integer consultationId : consultationIdList) {
					MedicalConsultation medicalConsultation = medicalConsultationMapper.selectByPrimaryKey(consultationId);
					if(medicalConsultation!=null)
					{
						List<MedicalSelectDepartment> list = medicalSelectDepartmentMapper.selectByConsultationId(medicalConsultation.getId());
						medicalConsultation.setSelect_department(list);
						medicalConsultationlist.add(medicalConsultation);
					}
				}
			}
		} else if (choose == 2) {
			// 查询我审核的会诊
			Doctor checkDoctor = doctorMapper.selectDoctorById(doctorId);
			if(checkDoctor.getUser().getRole_id()==1)
			{
				medicalConsultationlist= medicalConsultationMapper.selectByDepId(checkDoctor.getHospitalDepartment().getId());
				for (MedicalConsultation medicalConsultation : medicalConsultationlist) {
					System.out.println(medicalConsultation.getDoctor().getUser().getUser_name());
					List<MedicalSelectDepartment> list=medicalSelectDepartmentMapper.selectByConsultationId(medicalConsultation.getId());
					medicalConsultation.setSelect_department(list);
				}
			}

			
		} else if (choose == 3) {
			// 查询我申请的会诊
			medicalConsultationlist = medicalConsultationMapper.selectByDoctorId(doctorId);
			for (MedicalConsultation medicalConsultation : medicalConsultationlist) {
				System.out.println(medicalConsultation.getDoctor().getUser().getUser_name());
				List<MedicalSelectDepartment> list=medicalSelectDepartmentMapper.selectByConsultationId(medicalConsultation.getId());
				medicalConsultation.setSelect_department(list);
			}
		} else {

		}
		return medicalConsultationlist;	
	}
	
	// 获取状态结果字典
	public List<ResultDict> getResultDict() {
		List<ResultDict>list = resultDictMapper.getResultDict();
		return list;
	}

	public int selectCount() {
		return medicalConsultationMapper.selectCount();
	}

	public List<MedicalConsultation> queryAllByPage(Page page) {
		Map<String,Object> params = new HashMap<String ,Object>();
		//每一页显示多少条数据
		params.put("number", page.getRowNo());  
		//当前页
		params.put("start", (page.getPageNo()-1)*page.getRowNo());
		//按照页数查找数据
		List<MedicalConsultation> medicalConsultationList=medicalConsultationMapper.queryAllByPage(params);
		for (MedicalConsultation medicalConsultation : medicalConsultationList) {
			List<MedicalSelectDepartment> list=medicalSelectDepartmentMapper.selectByConsultationId(medicalConsultation.getId());
			medicalConsultation.setSelect_department(list);
	    }
	return medicalConsultationList;
	}
public List<MedicalConsultation> selectMedicalConsultationsByPatientId(int patientId) {

		return medicalConsultationMapper.selectMedicalConsultationsByPatientId(patientId);
	}


public void updateConsultationStatus(int con_id, int con_status) {
	
	medicalConsultationMapper.updateConsultationStatus(con_id, con_status);
}

/**
 * 修改会诊评级
 */
public void updateEvaluate(int con_id, String evaluate) {
	
	Map<String,Object> params = new HashMap<String ,Object>();
	params.put("con_id", con_id);
	params.put("evaluate", evaluate);
	medicalConsultationMapper.updateEvaluate(params);
}

public int selectMedicalConsultationCount(int userId) {
	return medicalConsultationMapper.selectMedicalConsultationCount(userId);
}

public List<MedicalConsultation> findMedicalConsultation(Page page,
		int userId) {
	Map<String,Object> params = new HashMap<String ,Object>();
	params.put("number", page.getRowNo());
	params.put("start", (page.getPageNo()-1)*page.getRowNo());
	params.put("userId", userId);
		return medicalConsultationMapper.findMedicalConsultation(params); 
}





}
