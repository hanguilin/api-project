/** 
 * Project Name:api-cleandata 
 * File Name:DataApplication.java 
 * Package Name:com.hanguilin 
 * Date:2019年7月27日下午3:17:37 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/** 
* @author  Administrator
* @date 2019年7月27日 下午3:17:37 
* @version 1.0  
* @since   
*/
@SpringBootApplication
@EnableScheduling
public class DataApplication {

	/** 
	 * 
	 *
	 * @param args 
	 */
	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}

}
