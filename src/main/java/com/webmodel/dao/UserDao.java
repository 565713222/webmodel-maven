package com.webmodel.dao;

import org.apache.ibatis.annotations.Param;

import com.webmodel.domain.User;

public interface UserDao {
	
	
	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User getByUsername(String username);
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public int save(User user);
	
	/**
	 * 修改资料
	 * @param user
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @return
	 */
	public int login(@Param("username")String username, @Param("password")String password);
	
}
