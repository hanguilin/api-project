/** 
 * Project Name:api-websocket 
 * File Name:WebSocketController.java 
 * Package Name:com.hanguilin.controller 
 * Date:2019年9月16日下午8:45:52 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hanguilin.service.WebSocketServer;

/** 
* @author  Administrator
* @date 2019年9月16日 下午8:45:52 
* @version 1.0  
* @since   
*/
@Controller
@RequestMapping("/checkcenter")
public class WebSocketController {

	//页面请求
	@GetMapping("/socket/{cid}")
	public ModelAndView socket(@PathVariable String cid) {
		ModelAndView mav=new ModelAndView("/socket");
		mav.addObject("cid", cid);
		return mav;
	}
	//推送数据接口
	@ResponseBody
	@RequestMapping("/socket/push/{cid}")
	public String pushToWeb(@PathVariable String cid, @RequestParam("Message") String message) {  
		try {
			WebSocketServer.sendInfo(message,cid);
		} catch (IOException e) {
			e.printStackTrace();
			return cid+"#"+e.getMessage();
		}  
		return cid;
	} 
}
