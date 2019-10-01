/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianCity.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月27日下午5:06:12 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/** 
* @author  Administrator
* @date 2019年7月27日 下午5:06:12 
* @version 1.0  
* @since   
*/
@Entity
@Table(name = "zhilian_city")
public class ZhiLianCity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String jobNumber;
	
	private String itemIds;
	
	private String display;
	
	public ZhiLianCity() {
		super();
	}

	public ZhiLianCity(String itemIds, String display, String jobNumber) {
		super();
		this.itemIds = itemIds;
		this.display = display;
		this.jobNumber = jobNumber;
	}

	@Transient
	private List<ZhiLianCityItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public List<ZhiLianCityItem> getItems() {
		return items;
	}

	public void setItems(List<ZhiLianCityItem> items) {
		this.items = items;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

}
