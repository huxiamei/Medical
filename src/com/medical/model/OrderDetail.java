package com.medical.model;

public class OrderDetail {
    private Integer id;

    private Integer order_id;

    private Integer hos_id;

    private Integer con_id;

    private Double acount;

    private Integer order_status;

	private String hospital_name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getHos_id() {
		return hos_id;
	}

	public void setHos_id(Integer hos_id) {
		this.hos_id = hos_id;
	}

	public Integer getCon_id() {
		return con_id;
	}

	public void setCon_id(Integer con_id) {
		this.con_id = con_id;
	}

	public Double getAcount() {
		return acount;
	}

	public void setAcount(Double acount) {
		this.acount = acount;
	}

	public Integer getOrder_status() {
		return order_status;
	}

	public void setOrder_status(Integer order_status) {
		this.order_status = order_status;
	}
    
    public String getHospital_name() {
		return hospital_name;
	}

	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
}