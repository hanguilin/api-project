/** 
 * Project Name:api-cleandata 
 * File Name:IPProxy.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月28日上午11:36:07 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/** 
* @author  Administrator
* @date 2019年7月28日 上午11:36:07 
* @version 1.0  
* @since   
*/
@Entity
@Table(name = "xe_ip_proxy")
@IdClass(IPProxyId.class)
public class IPProxy {
	
	@Id
	private String ip;
	
	@Id 
	private Integer port;
	
	private String city;
	
	private String type;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
