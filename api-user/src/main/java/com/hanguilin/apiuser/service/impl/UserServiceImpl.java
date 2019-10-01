/** 
 * Project Name:api-user 
 * File Name:UserServiceImpl.java 
 * Package Name:com.hanguilin.apiuser.service.impl 
 * Date:2019年8月11日下午2:03:39 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanguilin.apiuser.dao.UserDao;
import com.hanguilin.apiuser.entity.User;
import com.hanguilin.apiuser.service.UserService;
import com.hanguilin.apiuser.util.RedisUtil;
import com.hanguilin.apiuser.vo.UserVo;
import com.hanguilin.apiutils.constants.SystemConstants;
import com.hanguilin.apiutils.returns.SystemResult;
import com.hanguilin.apiutils.util.VerifyUtil;

/** 
* @author  Administrator
* @date 2019年8月11日 下午2:03:39 
* @version 1.0  
* @since   
*/
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/** 
	 * login
	 *
	 * @param response
	 * @param userVo
	 * @return 
	 */
	@Override
	public SystemResult<String> login(HttpServletResponse response,
			UserVo userVo) {
		if(VerifyUtil.isEmpty(userVo)) {
			return SystemResult.fail("参数对象不能为空。");
		}
		String name = userVo.getName();
		String password = userVo.getPassword();
		StringBuilder stringBuilder = new StringBuilder();
		if(VerifyUtil.isEmpty(name)) {
			stringBuilder.append("账户名不能为空。");
		}
		if(VerifyUtil.isEmpty(password)) {
			stringBuilder.append("密码不能为空。");
		}
		if(stringBuilder.length() != 0) {
			return SystemResult.fail(stringBuilder.toString());
		}
		User user = userDao.findByName(name);
		String passwordDB = user.getPassword();
		String passwordDecode = new String(Base64.getDecoder().decode(passwordDB));
		if(password.equals(passwordDecode)) {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String params = name + "_" + uuid;
			String token = Base64.getEncoder().encodeToString(params.getBytes(StandardCharsets.UTF_8));
			Cookie cookie = new Cookie(SystemConstants.COOKIE_TOKEN_PARAM, token);
			cookie.setPath("/");
			cookie.setMaxAge(SystemConstants.TOKEN_MAX_TIME);
			response.addCookie(cookie);
			redisUtil.set(token, "1", SystemConstants.TOKEN_MAX_TIME);
			return SystemResult.success("登录成功");
		}
		
		return SystemResult.fail("密码错误,登录失败");
	}

	/** 
	 * 
	 *
	 * @param userVo
	 * @return 
	 */
	@Override
	public SystemResult<String> register(UserVo userVo) {
		if(VerifyUtil.isEmpty(userVo)) {
			return SystemResult.fail("参数对象不能为空。");
		}
		String name = userVo.getName();
		String password = userVo.getPassword();
		StringBuilder stringBuilder = new StringBuilder();
		if(VerifyUtil.isEmpty(name)) {
			stringBuilder.append("账户名不能为空。");
		}
		if(VerifyUtil.isEmpty(password)) {
			stringBuilder.append("密码不能为空。");
		}
		if(stringBuilder.length() != 0) {
			return SystemResult.fail(stringBuilder.toString());
		}
		User user = userDao.findByName(name);
		if(!VerifyUtil.isEmpty(user)) {
			return SystemResult.fail("用户名已被占用");
		}
		String passwordEncode = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_8));
		user = new User();
		user.setName(userVo.getName());
		user.setPassword(passwordEncode);
		userDao.save(user);
		
		return SystemResult.success("注册成功");
	}

	/** 
	 * 
	 *
	 * @param token
	 * @return 
	 */
	@Override
	public SystemResult<String> isLogined(String token) {
		if(VerifyUtil.isEmpty(token)) {
			return SystemResult.fail("参数为空");
		}
		boolean hasKey = redisUtil.hasKey(token);
		if(hasKey) {
			return SystemResult.success();
		}
		
		return SystemResult.fail();
	}

}
