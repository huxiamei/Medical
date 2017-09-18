package com.medical.service;

import java.util.List;

import com.medical.model.MedicalConsultation;
import com.medical.model.MedicalCure;
import com.medical.tool.Page;

public interface MedicalCureService {
    
	//查看所有治疗方案
	List<MedicalCure> queryAll(Page page);

	//删除改治疗方案
	void deleteMedicalConsultationById(MedicalCure medicalCure);

	//修改治疗方案
	MedicalConsultation editCure(int id, String plan);

	//获取总数
	int selectCount();

}
