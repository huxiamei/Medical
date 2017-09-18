package com.medical.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.medical.model.MedicalCure;

public interface MedicalCureMapper {
    int deleteByPrimaryKeyAndConsultationId(MedicalCure medicalCure);

    int insert(MedicalCure record);

    int insertSelective(MedicalCure record);

    MedicalCure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicalCure record);

    int updateByPrimaryKey(MedicalCure record);

	List<MedicalCure> queryAll(Map<String, Object> params);

	void editCure(@Param("id")int id,@Param("plan") String plan);

	int selectCount();
}