/** 
 * Project Name:api-rabbitmq 
 * File Name:RabbitmqServiceImpl.java 
 * Package Name:com.hanguilin.apirabbitmq.service 
 * Date:2019年7月7日下午4:16:56 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apirabbitmq.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanguilin.apirabbitmq.config.RabbitmqConfig;
import com.hanguilin.apiutils.returns.SystemResult;

/** 
* @author  Administrator
* @date 2019年7月7日 下午4:16:56 
* @version 1.0  
* @since   
*/
@Service
public class RabbitmqServiceImpl implements IRabbitmqService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqServiceImpl.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/** 
	 * sendMessage
	 *
	 * @param message
	 * @return 
	 */
	@Override
	public SystemResult<Void> sendMessage(String message) {
		try {
			CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
			rabbitTemplate.convertAndSend(RabbitmqConfig.ORDER_EXCHANGE,RabbitmqConfig.ORDER_ROUTING_KEY, message,correlationId);
		} catch (Exception e) {
			LOGGER.error("发布信息失败", e.getMessage());
			return SystemResult.fail("发布信息失败", null);
		}
		return SystemResult.success("发送信息成功", null);
	}

}
