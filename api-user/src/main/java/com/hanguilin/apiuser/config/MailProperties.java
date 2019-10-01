/** 
 * Project Name:api-user 
 * File Name:MailProperties.java 
 * Package Name:com.hanguilin.apiuser.config 
 * Date:2019年7月6日下午8:33:08 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.config;
/** 
* @author  Administrator
* @date 2019年7月6日 下午8:33:08 
* @version 1.0  
* @since   
*/

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailProperties {

	private String from;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
}
