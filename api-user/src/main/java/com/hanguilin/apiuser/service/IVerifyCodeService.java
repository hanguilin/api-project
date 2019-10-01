/** 
 * Project Name:api-user 
 * File Name:IVerifyCodeService.java 
 * Package Name:com.hanguilin 
 * Date:2019年7月6日下午6:29:10 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.service;
/** 
* @author  Administrator
* @date 2019年7月6日 下午6:29:10 
* @version 1.0  
* @since   
*/

import com.hanguilin.apiutils.returns.SystemResult;

public interface IVerifyCodeService {
	
	public SystemResult<String> getCode(String email);
	
	public SystemResult<Boolean> verifyInputCode(String email, String code);
}
