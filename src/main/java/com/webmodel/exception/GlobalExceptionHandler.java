package com.webmodel.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webmodel.dto.CommonResponse;
import com.webmodel.dto.ResultCode;
import com.webmodel.utils.Json;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 500错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Object handle500Error(Exception e){
		return Json.toJson(new CommonResponse(ResultCode.ERROR, "服务器异常"));
	}
	
}
