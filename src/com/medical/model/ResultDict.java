package com.medical.model;

public class ResultDict {
	String result;
	int value;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ResultDict [result=" + result + ", value=" + value + "]";
	}

}
