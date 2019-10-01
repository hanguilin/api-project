/** 
 * Project Name:api-user 
 * File Name:IMailService.java 
 * Package Name:com.hanguilin.apiuser.service 
 * Date:2019年7月6日下午8:28:09 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.service;

import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年7月6日 下午8:28:09 
* @version 1.0  
* @since   
*/
public interface IMailService {
	
	SystemResult<String> sendEmail(String subject, String msg, String receiver);
}
