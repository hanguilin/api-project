/** 
 * Project Name:api-user 
 * File Name:UserController.java 
 * Package Name:com.hanguilin.apiuser.controller 
 * Date:2019年8月11日下午3:13:52 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanguilin.apiuser.service.UserService;
import com.hanguilin.apiuser.vo.UserVo;
import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年8月11日 下午3:13:52 
* @version 1.0  
* @since   
*/
@RestController
@RequestMapping("/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public SystemResult<String> doLogin(HttpServletResponse response, @RequestBody UserVo userVo){
		return userService.login(response, userVo);
	}
	
	@PostMapping("/register")
	public SystemResult<String> doRegister(@RequestBody UserVo userVo){
		return userService.register(userVo);
	}
	
	@GetMapping("/isLogined")
	public SystemResult<String> doIsLogined(@RequestParam("token" )String token){
		return userService.isLogined(token);
	} 
}
