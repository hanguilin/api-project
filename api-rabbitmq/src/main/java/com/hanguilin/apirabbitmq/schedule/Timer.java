/** 
 * Project Name:api-rabbitmq 
 * File Name:Timer.java 
 * Package Name:com.hanguilin.apirabbitmq.schedule 
 * Date:2019年7月7日下午6:43:53 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apirabbitmq.schedule;
/** 
* @author  Administrator
* @date 2019年7月7日 下午6:43:54 
* @version 1.0  
* @since   
*/

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hanguilin.apirabbitmq.service.IRabbitmqService;

@Component
public class Timer {
	
	@Autowired
	private IRabbitmqService rabbitmqService;
	
	@Scheduled(cron = "* 54 18 * * ?")
	public void sendMess() {
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
}
