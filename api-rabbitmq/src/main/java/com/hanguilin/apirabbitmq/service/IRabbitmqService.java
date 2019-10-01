/** 
 * Project Name:api-rabbitmq 
 * File Name:IRabbitmqService.java 
 * Package Name:com.hanguilin.apirabbitmq 
 * Date:2019年7月7日下午4:14:50 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apirabbitmq.service;

import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年7月7日 下午4:14:50 
* @version 1.0  
* @since   
*/
public interface IRabbitmqService {
	
	SystemResult<Void> sendMessage(String message);
}
