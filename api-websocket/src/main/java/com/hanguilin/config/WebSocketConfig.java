/** 
 * Project Name:api-websocket 
 * File Name:WebSocketConfig.java 
 * Package Name: 
 * Date:2019年9月16日下午8:32:16 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/** 
* @author  Administrator
* @date 2019年9月16日 下午8:32:16 
* @version 1.0  
* @since   
*/
@Configuration
public class WebSocketConfig {
	@Bean  
    public ServerEndpointExporter serverEndpointExporter() {  
        return new ServerEndpointExporter();  
    }  
}
