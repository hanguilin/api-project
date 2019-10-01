/** 
 * Project Name:api-user 
 * File Name:WebMvcConfig.java 
 * Package Name:com.hanguilin.config 
 * Date:2019年7月6日下午5:48:12 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.config;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/** 
* @author  Administrator
* @date 2019年7月6日 下午5:48:12 
* @version 1.0  
* @since   
*/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
		.addMapping("/**")
		.allowCredentials(true)
		.allowedHeaders("*")
		.allowedMethods("*")
		.allowedOrigins("*");
	}

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(convertResponseBody());
	}
	
	public StringHttpMessageConverter convertResponseBody() {
		StringHttpMessageConverter convert = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return convert;
	}
}
