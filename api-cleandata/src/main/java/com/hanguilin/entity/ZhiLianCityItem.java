/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianCityItem.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月27日下午5:08:17 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/** 
* @author  Administrator
* @date 2019年7月27日 下午5:08:17 
* @version 1.0  
* @since   
*/
@Entity
@Table(name = "zhilian_city_item")
public class ZhiLianCityItem {
	
	@Id
	private String code;
	
	private String name;
	
	private String enName;
	
	private String parentCode;
	
	@Transient
	private List<ZhiLianCityItem> sublist;
	
	public String getCode() { 
		return code;
	}

	public void setCode(String code) { 
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public List<ZhiLianCityItem> getSublist() {
		return sublist;
	}

	public void setSublist(List<ZhiLianCityItem> sublist) {
		this.sublist = sublist;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
}
