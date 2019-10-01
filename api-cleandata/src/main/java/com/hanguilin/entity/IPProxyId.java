/** 
 * Project Name:api-cleandata 
 * File Name:IPProxyId.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月28日下午12:16:51 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import java.io.Serializable;

/** 
* @author  Administrator
* @date 2019年7月28日 下午12:16:51 
* @version 1.0  
* @since   
*/
public class IPProxyId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String ip;
	
	private Integer port;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
}
