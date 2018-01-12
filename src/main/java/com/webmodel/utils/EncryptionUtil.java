package com.webmodel.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptionUtil {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EncryptionUtil.class);
	
	/**
	 * sha256加密
	 * @param string
	 * @return
	 */
	public static String getSha256Encryption(final String string){
		return encrypt(string,"SHA-256");
	}
	
	/**
	 * md5加密
	 * @param string
	 * @return
	 */
	public static String getMd5Encryption(final String string){
		return encrypt(string, "MD5");
	}
	
	/**
	 * 加密算法
	 * @param string
	 * @param encryptType
	 * @return
	 */
	public static String encrypt(final String string,final String encryptType){
		String returnString = "";
		if(StringUtils.isBlank(encryptType) || StringUtils.isBlank(string)){
			return returnString;
		}
		try {
			MessageDigest md = MessageDigest.getInstance(encryptType);
			md.update(string.getBytes());
			byte[] bs = md.digest();
			StringBuffer shaString = new StringBuffer();
			for(byte b : bs){
				String sha = Integer.toHexString(0xff & b);
				if(sha.length() == 1){
					shaString.append("0");
				}
				shaString.append(sha);
			}
			returnString = shaString.toString();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("when encrypt string:{}, encryptType:{}, errors occurred.", string, encryptType, e);
		}
		return returnString;
	}
}
