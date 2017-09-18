package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.MedicalConsultationMapper;
import com.medical.mapper.MedicalCureMapper;
import com.medical.mapper.MedicalSelectDepartmentMapper;
import com.medical.model.MedicalConsultation;
import com.medical.model.MedicalCure;
import com.medical.model.MedicalSelectDepartment;
import com.medical.service.MedicalCureService;
import com.medical.tool.Page;

/**
 * @Description: MedicalCureService 实体类
 * @author: linai
 * @date： 日期：Jan 6, 2017 时间：10:56:12 AM
 * @version 1.0
 */
@Service("MedicalCureService")
public class MedicalCureServiceImpl implements MedicalCureService {
    @Resource
    private MedicalCureMapper medicalCureMapper;
    @Resource
	private MedicalConsultationMapper medicalConsultationMapper;
    @Resource  
	private MedicalSelectDepartmentMapper medicalSelectDepartmentMapper;

	public List<MedicalCure> queryAll(Page page) {
		Map<String,Object> params = new HashMap<String ,Object>();
		//每一页显示多少条数据
		params.put("number", page.getRowNo());  
		//当前页
		params.put("start", (page.getPageNo()-1)*page.getRowNo());
		List<MedicalCure> medicalCureList=medicalCureMapper.queryAll(params);
		return medicalCureList;
	}

	public void deleteMedicalConsultationById(MedicalCure medicalCure) {
		//删除会诊
		medicalConsultationMapper.deleteByPrimaryKey(medicalCure.getConsultation_id());
		//删除治疗方案
	    medicalCureMapper.deleteByPrimaryKeyAndConsultationId(medicalCure);
	   
	}

	public MedicalConsultation editCure(int id, String plan) {
		//修改治疗方案
		medicalCureMapper.editCure(id,plan);
		//得到修改后的治疗方案
		MedicalConsultation medicalConsultation=medicalConsultationMapper.selectByPrimaryKey(id);
		List<MedicalSelectDepartment> list=medicalSelectDepartmentMapper.selectByConsultationId(id);
		medicalConsultation.setSelect_department(list);
		return medicalConsultation;
	}

	public int selectCount() {
		return medicalCureMapper.selectCount();
	}

}
