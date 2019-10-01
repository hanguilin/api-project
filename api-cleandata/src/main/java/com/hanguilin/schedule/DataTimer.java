/** 
 * Project Name:api-cleandata 
 * File Name:DataTimer.java 
 * Package Name:com.hanguilin.schedule 
 * Date:2019年7月27日下午3:17:14 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hanguilin.service.SpiderService;

/** 
* @author  Administrator
* @date 2019年7月27日 下午3:17:14 
* @version 1.0  
* @since   
*/
@Service
public class DataTimer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataTimer.class);
	
	@Autowired
	private SpiderService spiderService;
	
	@Scheduled(cron = "0 27 1 * * *")
	public void doSpider() {
		LOGGER.info("******开始执行定时任务******");
		spiderService.spiderZhiLian();
		LOGGER.info("******执行定时任务结束******");
	}
}
