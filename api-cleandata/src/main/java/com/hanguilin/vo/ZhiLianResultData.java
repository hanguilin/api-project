/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianResultData.java 
 * Package Name:com.hanguilin.vo 
 * Date:2019年7月27日下午4:56:19 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.vo;

import java.util.List;

import com.hanguilin.entity.ZhiLianJob;

/** 
* @author  Administrator
* @date 2019年7月27日 下午4:56:19 
* @version 1.0  
* @since   
*/
public class ZhiLianResultData {
	
	private Integer chatTotal;
	
	private Integer chatWindowState;
	
	private Integer count;
	
	private String informationStream;
	
	private String jobLabel;
	
	private String method;
	
	private Integer numFound;
	
	private Integer numTotal;
	
	private String taskId;
	
	private Integer typeSearch;
	
	private List<ZhiLianJob> results;

	public Integer getChatTotal() {
		return chatTotal;
	}

	public void setChatTotal(Integer chatTotal) {
		this.chatTotal = chatTotal;
	}

	public Integer getChatWindowState() {
		return chatWindowState;
	}

	public void setChatWindowState(Integer chatWindowState) {
		this.chatWindowState = chatWindowState;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getInformationStream() {
		return informationStream;
	}

	public void setInformationStream(String informationStream) {
		this.informationStream = informationStream;
	}

	public String getJobLabel() {
		return jobLabel;
	}

	public void setJobLabel(String jobLabel) {
		this.jobLabel = jobLabel;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Integer getNumFound() {
		return numFound;
	}

	public void setNumFound(Integer numFound) {
		this.numFound = numFound;
	}

	public Integer getNumTotal() {
		return numTotal;
	}

	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getTypeSearch() {
		return typeSearch;
	}

	public void setTypeSearch(Integer typeSearch) {
		this.typeSearch = typeSearch;
	}

	public List<ZhiLianJob> getResults() {
		return results;
	}

	public void setResults(List<ZhiLianJob> results) {
		this.results = results;
	}
	
}
