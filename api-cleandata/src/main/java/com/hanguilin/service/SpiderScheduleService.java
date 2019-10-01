/** 
 * Project Name:api-cleandata 
 * File Name:SpiderScheduleService.java 
 * Package Name:com.hanguilin.service 
 * Date:2019年7月27日下午4:19:04 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.service;
/** 
* @author  Administrator
* @date 2019年7月27日 下午4:19:04 
* @version 1.0  
* @since   
*/

import com.hanguilin.apiutils.returns.SystemResult;

public interface SpiderScheduleService {
	
	/** 
	 * 开启动态定时任务
	 *
	 * @param cron
	 * @return 
	 */
	SystemResult<String> start(String cron);
	
	/** 
	 * 关闭动态定时任务
	 *
	 * @return 
	 */
	SystemResult<String> stop();
}
