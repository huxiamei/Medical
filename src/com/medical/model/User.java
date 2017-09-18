package com.medical.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import com.medical.tool.myenum.Gender;


/**
 * �û�
 * Table BAS_USERS
 * 
 * �û�id----id
 * �û�����----user_name
 * �û�����----user_password
 * �Ա�----gender
 * ���֤����----idCard
 * �绰����----tel
 * ����----email
 * Ȩ��---role_id
 * ��������----birthdate
 * ��¼����---login_time
 * 
 * �����Լ�ҽ���̳����û�
 * @author medical
 *
 */
public class User {

    @NotNull(message="�û�id����Ϊ��")
	public int id;
    
    @NotNull(message="�û����벻��Ϊ��")
    @Size(min=6,max=20,message="���������6��20λ��֮��")
    @Pattern(regexp="^[a-zA-Z0-9]+$",message="������������ֻ���ĸ��ɣ������пո�")
	public String user_password;
    
    @NotNull(message="�û�����Ϊ��")
    @Size(max=20,message="�û��������20���ַ�֮��")
	public String user_name;
    
    @NotNull(message="�Ա���Ϊ��")
	public Gender gender;
    
    @NotNull(message="���֤����Ϊ��")
    @Size(min=18,max=18,message="���֤����Ϊ18λ")
    @Pattern(regexp="^[0-9]{17}[0-9X]+$",message="���֤����ֻ�������ֺ�X���")
	public String idCard;
    
    @NotNull(message="�绰���벻��Ϊ��")
    @Size(min=11,max=11,message="�绰���������11λ")
    @Pattern(regexp="^1[0-9]{10}+$",message="�绰�������������")
	public String tel;
    
    @NotNull
    @Email
	public String email;
    
    @NotNull
	public int role_id;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	@Past
	public Date birthdate;
	
	@Min(0)
	public int login_time;
	
	public User()
	{
		
	}
	
	
	public User(String password, String name, Gender gender, String iDCard,
			String tel, String email, int roleId, Date birthdate, int loginTime) {
		super();
		this.user_password = password;
		this.user_name = name;
		this.gender = gender;
		idCard = iDCard;
		this.tel = tel;
		this.email = email;
		role_id = roleId;
		this.birthdate = birthdate;
		login_time = loginTime;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String password) {
		this.user_password = password;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String name) {
		this.user_name = name;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int roleId) {
		role_id = roleId;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public int getLogin_time() {
		return login_time;
	}
	public void setLogin_time(int loginTime) {
		login_time = loginTime;
	}
	
	
}
