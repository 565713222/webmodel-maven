package com.webmodel.utils;

import java.util.UUID;

import org.apache.commons.lang3.RandomUtils;

public class IdUtil {
	
	/**
	 * 获取时间+6位随机数字id
	 * @return
	 */
	public static String getId(){
		StringBuffer sb = new StringBuffer();
		sb.append(DateUtil.getNow().replace("-" , "").replace(":" , "").replace(" " , ""));
		for(int i = 0; i < 3; i++){
			sb.append(RandomUtils.nextInt(10, 100));
		}
		return sb.toString();
	}
	
	/**
	 * 获取uuid
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "").substring(0, 20);
	}
	
	/**
	 * 获取人工生成手机号
	 * @return
	 */
	public static String getTelephone(){
		StringBuffer sb = new StringBuffer();
		sb.append("1");
		while(sb.length() < 11){
			int n = RandomUtils.nextInt(0,10);
			if(sb.length() == 1){
				if(n == 3 || n == 5 || n == 7){
					sb.append(n);
				}else{
					continue;
				}
			}else{
				sb.append(n);
			}
		}
		return sb.toString();
	}
}
