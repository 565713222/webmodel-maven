package com.webmodel.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取注入的类
 */
@Component
public class BeanUtil implements ApplicationContextAware {
	
	private static ApplicationContext ctx;

	public static Object getBean(String beanName){
		return ctx.getBean(beanName);
	}
	
	public static <T> T getBean(Class<T> clazz) {
		return ctx.getBean(clazz);
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		BeanUtil.ctx = ctx;
	}

}
