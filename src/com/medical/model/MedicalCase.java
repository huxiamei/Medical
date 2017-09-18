package com.medical.model;

import javax.validation.constraints.NotNull;

/**
 * 病历表
 * Table MED_CASE
 * 
 * 病例ID
 * 病人ID
 * 家族史
 * 过往病史
 * 病情描述
 * 文件路径
 * @author medical
 *
 */
public class MedicalCase {

	@NotNull
	private int id;
	@NotNull
	private Patient patient;
	private String family_sick;
	private String before_sick;
	private String description;
	private String file_path;
	
	public MedicalCase()
	{
		
	}
	
	
	public MedicalCase(int id, Patient patient, String familySick,
			String beforeSick, String description, String filePath) {
		super();
		this.id = id;
		this.patient = patient;
		family_sick = familySick;
		before_sick = beforeSick;
		this.description = description;
		file_path = filePath;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getFamily_sick() {
		return family_sick;
	}
	public void setFamily_sick(String familySick) {
		family_sick = familySick;
	}
	public String getBefore_sick() {
		return before_sick;
	}
	public void setBefore_sick(String beforeSick) {
		before_sick = beforeSick;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String filePath) {
		file_path = filePath;
	}
	
	
}
