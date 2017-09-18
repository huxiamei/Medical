package com.medical.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medical.mapper.HospitalMapper;
import com.medical.model.Hospital;
import com.medical.service.HospitalService;
import com.medical.tool.Page;

@Service("HospitalService")
public class HospitalServiceImpl implements HospitalService {

   @Resource
   private HospitalMapper hospitalmapper;
	
	public void addHospital(Hospital hospital) {
		hospitalmapper.addHospital(hospital);

	}

	public void deleteHospital(int id) {
		
		hospitalmapper.deleteHospital(id);

	}

	public void updateHospital(Hospital hospital) {

		hospitalmapper.updateHospital(hospital);

	}

	public Hospital selectHospitalById(int id) {
		
		return hospitalmapper.selectHospitalById(id);
	}

	public List<Hospital> findHospital(Page page) {
		
		Map<String,Object> params = new HashMap<String ,Object>();
		params.put("number", page.getRowNo());
		params.put("start", (page.getPageNo()-1)*page.getRowNo());
		return hospitalmapper.findHospital(params);
	}

	public int selectHospitalCount() {
		
		return hospitalmapper.selectHospitalCount();
	}

}
