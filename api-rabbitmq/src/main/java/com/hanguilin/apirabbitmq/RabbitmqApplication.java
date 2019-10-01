/** 
 * Project Name:api-rabbitmq 
 * File Name:RabbitmqApplication.java 
 * Package Name:com.hanguilin.apirabbitmq 
 * Date:2019年7月7日下午3:38:36 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apirabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/** 
* @author  Administrator
* @date 2019年7月7日 下午3:38:36 
* @version 1.0  
* @since   
*/
@SpringBootApplication
@EnableScheduling
public class RabbitmqApplication {

	/** 
	 * main
	 *
	 * @param args 
	 */
	public static void main(String[] args) {
		SpringApplication.run(RabbitmqApplication.class, args);
	}

}
