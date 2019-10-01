/** 
 * Project Name:api-rabbitmq 
 * File Name:Test.java 
 * Package Name: 
 * Date:2019年7月7日下午4:27:20 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
/** 
* @author  Administrator
* @date 2019年7月7日 下午4:27:20 
* @version 1.0  
* @since   
*/
package com.hanguilin.apirabbitmq.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hanguilin.apirabbitmq.service.IRabbitmqService;
import com.hanguilin.apirabbitmq.service.RabbitmqServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ServiceTest {

	@Autowired
	private IRabbitmqService rabbitmqService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqServiceImpl.class);
	
	@Before
	public void before() {
		LOGGER.info("===========start==============");
	}
	
	@Test
	public void test1() {
		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 3000; i++) {
			newCachedThreadPool.execute(new Detail(i));
		}
	}
	class Detail implements Runnable{
		int i;
		public Detail(int i) {
			this.i = i;
		}
		
		@Override
		public void run() {
			rabbitmqService.sendMessage("[{'name':'user"+i+"','productId':'10001','price':'9.9'}]");			
		}
		
	}
	
	@After
	public void after() {
		LOGGER.info("===========end==============");
	}
}
