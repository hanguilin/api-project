/** 
 * Project Name:api-user 
 * File Name:MailServiceImpl.java 
 * Package Name:com.hanguilin.apiuser.service.impl 
 * Date:2019年7月6日下午8:29:18 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hanguilin.apiuser.config.MailProperties;
import com.hanguilin.apiuser.service.IMailService;
import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年7月6日 下午8:29:18 
* @version 1.0  
* @since   
*/
@Service
public class MailServiceImpl implements IMailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VerifyCodeServiceImpl.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private MailProperties mailProperties;
	
	/** 
	 * 发送邮件
	 *
	 * @param msg
	 * @param receiver
	 * @return 
	 */
	@Override
	public SystemResult<String> sendEmail(String subject, String msg, String receiver) {
		if(!isValidMail(receiver)) {
			return SystemResult.fail("邮箱格式非法", null);
		}
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
		    mailMessage.setFrom(mailProperties.getFrom());
		    mailMessage.setTo(receiver);
		 
		    mailMessage.setSubject(subject);
		    mailMessage.setText(msg);
		 
		    javaMailSender.send(mailMessage);
		} catch (Exception e) {
			LOGGER.error("邮件发送失败", e.getMessage());
			return SystemResult.fail("发送失败", null);
		}
		return SystemResult.success("发送成功", null);
	}
	private boolean isValidMail(String email) {
		String reg = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
		return email.matches(reg);
	}
}
