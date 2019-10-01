/** 
 * Project Name:api-cleandata 
 * File Name:XEResult.java 
 * Package Name:com.hanguilin.vo 
 * Date:2019年7月28日上午11:38:30 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.vo;

import java.util.List;

import com.hanguilin.entity.IPProxy;

/** 
* @author  Administrator
* @date 2019年7月28日 上午11:38:30 
* @version 1.0  
* @since   
*/
public class XEResult {
	
	private Integer code;
	
	private Boolean success;
	
	private String msg;
	
	private List<IPProxy> data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<IPProxy> getData() {
		return data;
	}

	public void setData(List<IPProxy> data) {
		this.data = data;
	}
	
}
