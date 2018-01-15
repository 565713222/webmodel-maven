package com.webmodel.api;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webmodel.domain.User;
import com.webmodel.dto.ResultCode;
import com.webmodel.utils.DateUtil;
import com.webmodel.utils.EncryptionUtil;
import com.webmodel.utils.IdUtil;
import com.webmodel.utils.RedisUtil;

@Controller
@RequestMapping("/user")
public class UserApi extends BaseApi {
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ResponseBody
	public Object getByUsername(String username) throws Exception{
		return succ(User.getByUsername(username));
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public Object register() throws Exception{
		User user = new User();
		user.setId(IdUtil.getId());
		user.setUsername("test");
		user.setPassword(EncryptionUtil.getMd5Encryption("123456"));
		String ctime = DateUtil.getNow();
		user.setCtime(ctime);
		user.setMtime(ctime);
		User.save(user);
		return succ();
	}
	
	@RequestMapping(value = "/load", method = {RequestMethod.GET})
	public void download(HttpServletResponse res) throws Exception{
		String fileName = "test.csv";
		res.setContentType("APPLICATION/OCTET-STREAM");
		res.setCharacterEncoding("utf-8");
		res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		String title = "用户名,创建时间";
		PrintWriter writer = res.getWriter();
		writer.println(title);
		User user = User.getByUsername("test");
		writer.println(String.format("\"%s\",\"%s\"", user.getUsername(),user.getCtime()));
	}
	
	@RequestMapping(value = "/testRedis", method = {RequestMethod.GET})
	@ResponseBody
	public Object test() {
		try {
			User user = new User();
			user.setId(IdUtil.getId());
			user.setUsername("test");
			user.setPassword(EncryptionUtil.getMd5Encryption("123456"));
			String ctime = DateUtil.getNow();
			user.setCtime(ctime);
			user.setMtime(ctime);
			RedisUtil.setCache("user", user);
			User u = (User) RedisUtil.getCache("user") ;
			System.out.println(u.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			return error(ResultCode.ERROR,"测试失败");
		}
		return succ();
	}
}
