package com.medical.mapper;

import java.util.List;

import com.medical.model.MedicalSelectDepartment;

public interface MedicalSelectDepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MedicalSelectDepartment record);

    int insertSelective(MedicalSelectDepartment record);

    MedicalSelectDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MedicalSelectDepartment record);

    int updateByPrimaryKey(MedicalSelectDepartment record);

	List<MedicalSelectDepartment> selectByConsultationId(int id);

	List<Integer> selectByDoctorId(int doctorId);
}