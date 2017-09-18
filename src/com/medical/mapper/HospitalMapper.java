package com.medical.mapper;

import java.util.List;
import java.util.Map;

import com.medical.model.Hospital;

/**
 * ҽԺ
 * @author medical
 *
 */
public interface HospitalMapper {

	
	public void addHospital(Hospital hospital);
	
	public void deleteHospital(int id);
	
	public void updateHospital(Hospital hospital);
	
	public Hospital selectHospitalById(int id);
	
	public List<Hospital> findHospital(Map<String,Object> param);
	
	public int selectHospitalCount();
	
}
