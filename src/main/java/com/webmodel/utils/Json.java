package com.webmodel.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 为null的字段不序列化
	 */
	static {
		objectMapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	private static Logger logger = LoggerFactory.getLogger(Json.class);
	
	public static String toJson(Object obj) {
		try {
			return obj == null ? "{}" : objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("convert object to json string failed. ", e);
		}
		return "{}";
	}
	
	public static <T> T fromJson(String json, Class<T> clazz) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("convert json string to object failed. ", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(Object obj) {
		return obj == null ? null : objectMapper.convertValue(obj, Map.class);
	}
	
}
