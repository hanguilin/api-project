/** 
 * Project Name:api-zull 
 * File Name:ZuulApplication.java 
 * Package Name:com.hanguilin 
 * Date:2019年8月11日下午2:44:54 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/** 
* @author  Administrator
* @date 2019年8月11日 下午2:44:54 
* @version 1.0  
* @since   
*/
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableFeignClients
@EnableHystrix
public class ZuulApplication {

	/** 
	 * main
	 *
	 * @param args 
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

}
