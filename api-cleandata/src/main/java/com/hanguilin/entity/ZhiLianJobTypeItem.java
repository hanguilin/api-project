/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianJobTypeItem.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月27日下午5:19:20 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author  Administrator
* @date 2019年7月27日 下午5:19:20 
* @version 1.0  
* @since   
*/
@Entity
@Table(name = "zhilian_job_type_item")
public class ZhiLianJobTypeItem {
	
	@Id
	private String jobNumber;
	
	private String name;

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
