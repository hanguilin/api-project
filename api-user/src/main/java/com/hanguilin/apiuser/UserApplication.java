/** 
 * Project Name:api-user 
 * File Name:UserApplication.java 
 * Package Name:com.hanguilin 
 * Date:2019年7月6日下午5:40:32 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/** 
* @author  Administrator
* @date 2019年7月6日 下午5:40:32 
* @version 1.0  
* @since   
*/
@SpringBootApplication
@EnableEurekaClient
public class UserApplication extends SpringBootServletInitializer{

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UserApplication.class);
	}

	/** 
	 * 
	 *
	 * @param args 
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
