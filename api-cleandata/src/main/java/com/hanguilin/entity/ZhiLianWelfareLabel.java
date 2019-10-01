/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianWelfareLabel.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月28日下午12:33:22 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/** 
* @author  Administrator
* @date 2019年7月28日 下午12:33:22 
* @version 1.0  
* @since   
*/
@Entity
@Table(name = "zhilian_welfare_label")
public class ZhiLianWelfareLabel {
	
	@Id
	@JSONField(name = "key")
	private String code;
	
	private Integer state;
	
	@JSONField(name = "value")
	private String name;
	
	@JSONField(name = "desc")
	private String description;
	
	private String imgurl;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

}
