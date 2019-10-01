/** 
 * Project Name:api-cleandata 
 * File Name:SpiderController.java 
 * Package Name:com.hanguilin.controller 
 * Date:2019年7月27日下午4:47:53 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanguilin.apiutils.returns.SystemResult;
import com.hanguilin.service.SpiderService;

/** 
* @author  Administrator
* @date 2019年7月27日 下午4:47:53 
* @version 1.0  
* @since   
*/
@RestController
@RequestMapping("/v1/api/data")
public class SpiderController {

	@Autowired
	private SpiderService spiderService;
	
	@GetMapping("/get")
	public SystemResult<String> doGet(){
		return spiderService.spiderZhiLian();
	}
}
