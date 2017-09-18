package com.medical.tool.myenum;

public enum Blood implements GenericEnum{

	N(0,"未知"),
	A(1,"A型"),
	B(2,"B型"),
	O(3,"O型"),
	AB(4,"AB型"),
	RH(5,"RH型");
	
	private int code;
	private String name;

	
	Blood(int code, String name)
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
	
	public static Blood valueOfEnum(int code)
	{
		Blood[] bloods = values();
		for(Blood blood : bloods)
		{
			if(blood.getCode() == code)
			{
				return blood;
			}
		}
		return null;
	}
	
		
}
