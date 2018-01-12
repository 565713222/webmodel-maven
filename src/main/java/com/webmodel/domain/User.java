package com.webmodel.domain;

import java.io.Serializable;

import com.webmodel.dao.UserDao;
import com.webmodel.utils.BeanUtil;

public class User implements Serializable {

	private static final long serialVersionUID = 5668003172224267295L;
	
	private static UserDao userDao = BeanUtil.getBean(UserDao.class);
	
	private String id;						//主键
	private String username;				//手机号或者邮箱号
	private String password;				//密码
	private String ctime;					//创建时间
	private String mtime;					//修改时间
	
	
	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public static User getByUsername(String username){
		return userDao.getByUsername(username);
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public static int save(User user){
		return userDao.save(user);
	}
	
	/**
	 * 登陆
	 * @param username
	 * @param password
	 * @return
	 */
	public static int login(String username, String password){
		return userDao.login(username, password);
	}
	
	/**
	 * 修改资料
	 * @param user
	 * @return
	 */
	public static int update(User user){
		return userDao.update(user);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

	public String getMtime() {
		return mtime;
	}

	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
}
