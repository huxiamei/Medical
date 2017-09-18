package com.medical.tool.myenum;


/**
 * 会诊状态
 * @author lyan
 *
 */
public enum ConStatus implements GenericEnum{
	
	ApplyConsulation(0,"主治医生提交会诊"),
	PatientAgree(1,"病人同意会诊"),
	PatientNotAgree(-1,"病人不同意会诊"),
	CheckDoctorAgree(2,"审核医生同意会诊"),
	CheckDoctorNotAgree(-2,"审核医生不同意会诊"),
	PatientPrePaySuccess(3,"病人预缴费成功"),
	Consulation(4,"会诊中"),
	WirteCurePlan(5,"主治医生填写解决方案"),
	PatientPay(6,"病人缴费中"),
	ConsulationOver(7,"会诊完成"),
	WriteEvaluate(8,"已填写评价");
	
	private int code;
	private String name;

	
	ConStatus(int code, String name)
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
	
	public static ConStatus valueOfEnum(int code)
	{
		ConStatus[] type = values();
		for(ConStatus t : type)
		{
			if(t.getCode() == code)
			{
				return t;
			}
		}
		return null;
	}


}
