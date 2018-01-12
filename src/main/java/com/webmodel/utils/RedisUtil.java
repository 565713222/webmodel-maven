package com.webmodel.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
	
	private static RedisTemplate<String, Object> redisTemplate;
	
	@Resource(name = "redisTemplate")
	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		RedisUtil.redisTemplate = redisTemplate;
	}

	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 */
	public static void setCache(String key, Object value){
		redisTemplate.opsForValue().set(key, value);
	}
	
	/**
	 * 设置缓存并设置时间
	 * @param key
	 * @param value
	 * @param time
	 */
	public static void setCache(String key, Object value, long time){
		redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
	}
	
	/**
	 * 设置自增的缓存
	 * @param key
	 * @param length
	 */
	public static long setIncCache(String key, long length){
		return redisTemplate.opsForValue().increment(key, length);
	}
	
	/**
	 * 设置过期时间
	 * @param key
	 * @param timeout
	 */
	public static void setExpire(String key, long timeout){
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}
	
	/**
	 * 获取过期时间
	 * @param key
	 * @return
	 */
	public static long getExpire(String key){
		return redisTemplate.getExpire(key);
	}
	
	/**
	 * 获取过期时间并转化为对应的时间格式
	 * @param key
	 * @param type
	 * @return
	 */
	public static long getExpire(String key, TimeUnit type) {
		return redisTemplate.getExpire(key, type);
	}
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	public static Object getCache(String key){
		return redisTemplate.opsForValue().get(key);
	}
	
	/**
	 * 删除缓存
	 * @param key
	 */
	public static void delCache(String key){
		redisTemplate.delete(key);
	}
}
