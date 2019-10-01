/** 
 * Project Name:api-rabbitmq 
 * File Name:RabbitmqConfig.java 
 * Package Name:com.hanguilin.apirabbitmq.config 
 * Date:2019年7月7日下午3:53:39 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apirabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 
* @author  Administrator
* @date 2019年7月7日 下午3:53:39 
* @version 1.0  
* @since   
*/
@Configuration
public class RabbitmqConfig {

	public static final String ORDER_QUEUE = "order_queue";
	public static final String ORDER_EXCHANGE = "order_exchange";
	public static final String ORDER_ROUTING_KEY = "order_key";
	
	
	@Bean
	public Queue queue() {
		return new Queue(ORDER_QUEUE, true);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange(ORDER_EXCHANGE);
	}
	
	@Bean
	public Binding binding(TopicExchange exchange, Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with(ORDER_ROUTING_KEY);
	}

}
