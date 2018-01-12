package com.webmodel.dto;

public enum ResultCode {
	SUCCESS(200),
	ERROR(500);
	
	private int code;

	ResultCode(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
