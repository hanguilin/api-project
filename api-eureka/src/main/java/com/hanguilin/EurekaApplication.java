/** 
 * Project Name:api-eureka 
 * File Name:EurekaApplication.java 
 * Package Name:com.hanguilin 
 * Date:2019年7月6日下午6:01:59 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/** 
* @author  Administrator
* @date 2019年7月6日 下午6:01:59 
* @version 1.0  
* @since   
*/
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {

	/** 
	 * 
	 *
	 * @param args 
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaApplication.class, args); 
	}

}
