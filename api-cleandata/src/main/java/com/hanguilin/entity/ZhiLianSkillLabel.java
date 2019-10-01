/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianSkillLabel.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月28日下午12:35:42 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author  Administrator
* @date 2019年7月28日 下午12:35:42 
* @version 1.0  
* @since   
*/
@Entity
@Table(name = "zhilian_skill_label")
public class ZhiLianSkillLabel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer state;
	
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
