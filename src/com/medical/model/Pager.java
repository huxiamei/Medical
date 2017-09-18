package com.medical.model;

import java.util.List;

public class Pager<T> {
	
	/*
	 * 每页显示多少条记录
	 */
	private int pageSize; 
	
	/*
	 * 当前第几页数据
	 */
	private int currentPage;
	
	/*
	 * 一共多少条记录
	 */
	private int totalRecord;
	
	/*
	 * 一共多少页记录
	 */
	private int totalPage; 
	
	/*
	 * 要显示的数据
	 */
	private List<T> dataList;	
	
	/**
	 * 构造函数1
	 */
	public Pager(){
		
	}
	
	/**
	 * 构造函数2
	 * @param pageSize
	 * @param currentPage
	 * @param totalRecord
	 * @param totalPage
	 * @param dataList
	 */
	public Pager(int pageSize, int currentPage, int totalRecord,
			List<T> dataList) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalRecord = totalRecord;
		this.dataList = dataList;
		if(pageSize == 0){
			totalPage = 1;
			currentPage = 1;
		}else{
			//总页数
			this.totalPage = this.totalRecord / this.pageSize;
			if(this.totalRecord % this.pageSize !=0){
				this.totalPage = this.totalPage + 1;
			}
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
}
