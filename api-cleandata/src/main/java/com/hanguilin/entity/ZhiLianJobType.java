/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianJobType.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月27日下午5:19:03 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import java.util.List;

/** 
* @author  Administrator
* @date 2019年7月27日 下午5:19:03 
* @version 1.0  
* @since   
*/
public class ZhiLianJobType {
	
	private Integer id;
	
	private List<ZhiLianJobTypeItem> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ZhiLianJobTypeItem> getItems() {
		return items;
	}

	public void setItems(List<ZhiLianJobTypeItem> items) {
		this.items = items;
	}
}
