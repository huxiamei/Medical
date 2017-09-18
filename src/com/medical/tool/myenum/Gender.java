package com.medical.tool.myenum;

public enum Gender implements GenericEnum {

	secret(0,"保密"),
	M(1,"男"),
	F(2,"女");

	private int code;  
	  
    private String name;
    
    Gender(int code, String name)
    {
    	this.code = code;
    	this.name = name;
    	
    }
	public int getCode() {
		
		return this.code;
	}

	public String getName() {
		
		return this.name;
	}
	
	public static Gender valueOfEnum(int code)
	{
		Gender[] genders = values();
		for(Gender gender : genders)
		{
			if(gender.getCode() == code)
			{
				return gender;
			}
		}
		return null;
	}
}
