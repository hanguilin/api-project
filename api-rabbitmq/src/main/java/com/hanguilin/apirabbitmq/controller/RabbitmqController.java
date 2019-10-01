/** 
 * Project Name:api-rabbitmq 
 * File Name:RabbitmqController.java 
 * Package Name:com.hanguilin.apirabbitmq 
 * Date:2019年7月7日下午5:32:43 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apirabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanguilin.apirabbitmq.service.IRabbitmqService;
import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年7月7日 下午5:32:43 
* @version 1.0  
* @since   
*/
@RestController
@RequestMapping("/v1/api/rabbitmq")
public class RabbitmqController {

	@Autowired
	private IRabbitmqService rabbitmqService;
	
	@GetMapping("/put")
	public SystemResult<Void> doPut(@RequestParam("message") String message){
		return rabbitmqService.sendMessage(message);
	}
}
