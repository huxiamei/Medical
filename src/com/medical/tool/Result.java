package com.medical.tool;

import java.util.List;

import com.medical.model.Pager;

public class Result<T> {
		
	private boolean success = false;
	
	private String message = "";
	
	private List<T> resultList;
	
	private T result;
	
	private Pager<T> pageList;
	
	public Result() {
		
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public boolean isExecuted() {
		
		return false;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	public Pager<T> getPageList() {
		return pageList;
	}

	public void setPageList(Pager<T> pageList) {
		this.pageList = pageList;
	}

	public String toString() {
		return "Result [success=" + success + ", message=" + message + "]";
	}
}
