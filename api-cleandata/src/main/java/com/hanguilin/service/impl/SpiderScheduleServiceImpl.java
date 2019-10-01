/** 
 * Project Name:api-cleandata 
 * File Name:SpriderScheduleServiceImpl.java 
 * Package Name:com.hanguilin.service.impl 
 * Date:2019年7月27日下午4:22:40 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.service.impl;

import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.hanguilin.apiutils.returns.SystemResult;
import com.hanguilin.service.SpiderScheduleService;
import com.hanguilin.service.SpiderService;

/** 
* @author  Administrator
* @date 2019年7月27日 下午4:22:40 
* @version 1.0  
* @since   
*/
@Service
public class SpiderScheduleServiceImpl implements SpiderScheduleService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpiderScheduleServiceImpl.class);
	
	@Autowired
	private ThreadPoolTaskScheduler threadPoolTaskScheduler;
	
	private ScheduledFuture<?> scheduledFuture;
	
	@Bean
	private ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
		return new ThreadPoolTaskScheduler();
	}
	
	@Autowired
	private SpiderService spiderService;
	
	/** 
	 * start
	 *
	 * @param cron
	 * @return 
	 */
	@Override
	public SystemResult<String> start(String cron) {
		stop();
		try {
			scheduledFuture = threadPoolTaskScheduler.schedule(new DataThread(), new CronTrigger(cron));
		} catch (Exception e) {
			LOGGER.error("开始定时器失败", e.getMessage());
			return SystemResult.fail("开始定时器失败");
		}
		return SystemResult.success("成功开始定时器");
	}

	/** 
	 * 
	 *
	 * @return 
	 */
	@Override
	public SystemResult<String> stop() {
		if(null != scheduledFuture) {
			scheduledFuture.cancel(true);
		}
		return SystemResult.success("成功停止定时器");
	}

	class DataThread implements Runnable{

		/** 
		 * 
		 * 
		 */
		@Override
		public void run() {
			spiderService.spiderZhiLian();
		}
		
	}
}
