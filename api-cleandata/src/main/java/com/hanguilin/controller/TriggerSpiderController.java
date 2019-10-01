/** 
 * Project Name:api-cleandata 
 * File Name:TriggerSpiderController.java 
 * Package Name:com.hanguilin.controller 
 * Date:2019年7月27日下午4:17:49 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hanguilin.apiutils.returns.SystemResult;
import com.hanguilin.service.SpiderScheduleService;

/** 
* @author  Administrator
* @date 2019年7月27日 下午4:17:49 
* @version 1.0  
* @since   
*/
@RestController
@RequestMapping("/v1/api/spider")
public class TriggerSpiderController {
	
	@Autowired
	private SpiderScheduleService spiderScheduleService;
	
	/** 
	 * 开启动态定时任务
	 *
	 * @param cron
	 * @return 
	 */
	@GetMapping("/start")
	public SystemResult<String> doStart(@RequestParam("cron") String cron){
		return spiderScheduleService.start(cron);
	}
	
	/** 
	 * 关闭定时任务
	 *
	 * @return 
	 */
	@GetMapping("/stop")
	public SystemResult<String> doStop(){
		return spiderScheduleService.stop();
	}
}
