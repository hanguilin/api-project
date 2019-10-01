/** 
 * Project Name:api-user 
 * File Name:VerifyCodeServiceImpl.java 
 * Package Name:com.hanguilin.service.impl 
 * Date:2019年7月6日下午6:40:04 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.service.impl;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanguilin.apiuser.service.IMailService;
import com.hanguilin.apiuser.service.IVerifyCodeService;
import com.hanguilin.apiuser.util.RedisUtil;
import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年7月6日 下午6:40:04 
* @version 1.0  
* @since   
*/
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

	private static final String BASECODE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	/** 有效时长，3分钟 */
	private static final long EFFECTIVE_DURATION = 1800l;
	
	@Autowired
	private RedisUtil redisUtil;
	
	@Autowired
	private IMailService mailService;
	
	/** 
	 * 
	 *
	 * @param email
	 * @return 
	 */
	@Override
	public SystemResult<String> getCode(String email) {
		if(!isValidMail(email)) {
			return SystemResult.fail("邮箱格式非法", null);
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			stringBuilder.append(BASECODE.charAt(new Random().nextInt(BASECODE.length())));
		}
		String verifyCode = stringBuilder.toString();
		boolean flag = redisUtil.set(email, verifyCode, EFFECTIVE_DURATION);
		if(!flag) {
			return SystemResult.fail("存库失败", null);
		}
		SystemResult<String> sendEmail = mailService.sendEmail("验证码", verifyCode, email);
		if(sendEmail.getCode() == -1) {
			return SystemResult.fail(sendEmail.getMsg(), null);
		}
		return SystemResult.success("发送成功", null);
	}

	/** 
	 * 
	 *
	 * @param email
	 * @param code
	 * @return 
	 */
	@Override
	public SystemResult<Boolean> verifyInputCode(String email, String code) {
		if(StringUtils.isEmpty(email) || StringUtils.isEmpty(code)) {
			return SystemResult.fail("参数不能为空", false);
		}
		if(!isValidMail(email)) {
			return SystemResult.fail("邮箱格式非法", null);
		}
		Object value = redisUtil.get(email);
		if(null == value) {
			return SystemResult.fail("验证码已失效", false);
		}
		if(!String.valueOf(value).equals(code)) {
			return SystemResult.fail("验证码输入错误", false);
		}
		
		return SystemResult.success("验证码输入正确", true);
	}

	private boolean isValidMail(String email) {
		String reg = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
		return email.matches(reg);
	}
}
