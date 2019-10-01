/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianCompany.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月27日下午5:11:31 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author  Administrator
* @date 2019年7月27日 下午5:11:31 
* @version 1.0  
* @since   
*/
@Entity
@Table(name = "zhilian_company")
public class ZhiLianCompany {

	@Id
	private String number;
	
	private String name;
	
	private String url;
	
	private String size;
	
	private String type;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
