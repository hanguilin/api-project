/** 
 * Project Name:api-user 
 * File Name:UserSevice.java 
 * Package Name:com.hanguilin.apiuser.service 
 * Date:2019年8月11日下午1:59:01 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.service;

import javax.servlet.http.HttpServletResponse;

import com.hanguilin.apiuser.vo.UserVo;
import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年8月11日 下午1:59:01 
* @version 1.0  
* @since   
*/
public interface UserService {
	
	SystemResult<String> login(HttpServletResponse response, UserVo userVo);
	
	SystemResult<String> register(UserVo userVo);
	
	SystemResult<String> isLogined(String token); 
}
