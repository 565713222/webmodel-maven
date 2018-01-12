package com.webmodel.dto;

public class CommonResponse {
	private int code;			//状态码
	private String msg;			//提示信息
	private Integer total;		//总数
	private Object data;		//数据
	
	public CommonResponse(){
		
	}
	
	public CommonResponse(ResultCode code, String msg){
		this.code = code.getCode();
		this.msg = msg;
	}
	
	public CommonResponse(ResultCode code, Object data){
		this.code = code.getCode();
		this.data = data;
	}
	
	public CommonResponse(ResultCode code, Integer total, Object data){
		this.code = code.getCode();
		this.total = total;
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
