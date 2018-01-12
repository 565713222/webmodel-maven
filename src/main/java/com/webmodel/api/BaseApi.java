package com.webmodel.api;

import com.webmodel.dto.CommonResponse;
import com.webmodel.dto.ResultCode;

public class BaseApi {
	
	protected CommonResponse succ(){
		return new CommonResponse(ResultCode.SUCCESS, null);
	}
	
	protected CommonResponse succ(ResultCode code){
		return new CommonResponse(code, null);
	}
	
	protected CommonResponse succ(Object data){
		return new CommonResponse(ResultCode.SUCCESS, data);
	}
	
	protected CommonResponse succ(ResultCode code, Object data){
		return new CommonResponse(code, data);
	}
	
	protected CommonResponse succ(ResultCode code, Integer total, Object data){
		return new CommonResponse(code, total, data);
	}
	
	protected CommonResponse error(ResultCode code, String msg){
		return new CommonResponse(code,msg);
	}
}
