package com.webmodel.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webmodel.domain.User;
import com.webmodel.utils.DateUtil;
import com.webmodel.utils.EncryptionUtil;
import com.webmodel.utils.IdUtil;


public class AddUserTask {
	
	private static final Logger logger = LoggerFactory.getLogger(AddUserTask.class);
	
	public void addData(){
		String username = IdUtil.getTelephone();
		try {
			User user = new User();
			user.setId(IdUtil.getId());
			user.setUsername(username);
			user.setPassword(EncryptionUtil.getMd5Encryption("123456"));
			String ctime = DateUtil.getNow();
			user.setCtime(ctime);
			user.setMtime(ctime);
			User.save(user);
			logger.info("add user succ. telephone={}",username);
		} catch (Exception e) {
			logger.error("add user error. telephone={}", username, e);
		}
	}
}
