package com.medical.tool.myenum;

/**
 * 消息类型
 * @author lyan
 *
 */
public enum InforType implements GenericEnum{
	
	Apply(0,"主治医生申请会诊中"),
	PatientAgree(1,"病人同意会诊申请"),
	PatientNotAgree(2,"病人不同意会诊"),
	CheckDoctorAgree(3,"审核医生同意会诊"),
	CheckDoctorNotAgree(4,"审核医生不同意会诊"),
	PreviousPay(5,"请病人预缴费"),
	PreviousPayOver(6,"病人预缴费完成"),
	InviteJoinConsulation(7,"邀请医师参与会诊"),
	StartConsulation(8,"会诊进行中"),
	ConsulationResultWrite(9,"会诊结果填写完毕"),
	Pay(10,"请病人付全款"),
	ConsulationOver(11,"会诊完毕"),
	WriteEvaluateOver(12,"病人填写会诊评价完毕");
	
	

	private int code;
	public String name;

	
	InforType(int code, String name)
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
	
	public static InforType valueOfEnum(int code)
	{
		InforType[] type = values();
		for(InforType t : type)
		{
			if(t.getCode() == code)
			{
				return t;
			}
		}
		return null;
	}

}
