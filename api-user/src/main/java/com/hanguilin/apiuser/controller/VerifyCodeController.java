/** 
 * Project Name:api-user 
 * File Name:VerifyCodeController.java 
 * Package Name:com.hanguilin.controller 
 * Date:2019年7月6日下午6:56:17 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanguilin.apiuser.service.IVerifyCodeService;
import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年7月6日 下午6:56:17 
* @version 1.0  
* @since   
*/
@RestController
@RequestMapping("/v1/api/user")
public class VerifyCodeController {

	@Autowired
	private IVerifyCodeService verifyCodeService;
	
	/** 
	 * 获取验证码
	 *
	 * @param email 邮箱地址
	 * @return SystemResult<String>
	 */
	@GetMapping("/verifycode/get")
	public SystemResult<String> doGetVerifyCode(@RequestParam("email") String email){
		return verifyCodeService.getCode(email);
	}
	
	/** 
	 * 验证输入验证码
	 *
	 * @param email 邮箱地址
	 * @param code 验证码
	 * @return SystemResult<Boolean>
	 */
	@GetMapping("/verifycode/verify")
	public SystemResult<Boolean> doVerifyCode(@RequestParam("email") String email, @RequestParam("code") String code){
		return verifyCodeService.verifyInputCode(email, code);
	}
}
