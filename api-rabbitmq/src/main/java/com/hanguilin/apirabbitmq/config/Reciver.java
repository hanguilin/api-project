/** 
 * Project Name:api-rabbitmq 
 * File Name:Reciver.java 
 * Package Name:com.hanguilin.apirabbitmq.entity 
 * Date:2019年7月7日下午3:45:54 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apirabbitmq.config;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/** 
* @author  Administrator
* @date 2019年7月7日 下午3:45:54 
* @version 1.0  
* @since   
*/
@Component
@RabbitListener(queues = RabbitmqConfig.ORDER_QUEUE)
public class Reciver {
	private static final Logger LOGGER = LoggerFactory.getLogger(Reciver.class);
	private AtomicLong atomicLong = new AtomicLong();
	
	@RabbitHandler
	public void receiveMessage(String messages) {
		LOGGER.info("recive-"+atomicLong.getAndIncrement()+":"+messages);
	}
}
