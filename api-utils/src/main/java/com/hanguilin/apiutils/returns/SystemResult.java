/** 
 * Project Name:api-utils 
 * File Name:SystemResult.java 
 * Package Name:com.hanguilin.api.returns 
 * Date:2019年7月6日下午6:30:29 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiutils.returns;

import com.hanguilin.apiutils.constants.SystemConstants;

/** 
* @author  Administrator
* @date 2019年7月6日 下午6:30:29 
* @version 1.0  
* @since   
*/
public class SystemResult<T> {

	private int code;
	
	private String msg;
	
	private T data;

	public SystemResult() {
		super();
	}
	
	public int getCode() {
		return code;
	}

	public SystemResult(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public static <T> SystemResult<T> success(String msg, T data){
		return new SystemResult<T>(SystemConstants.SUCCESS, msg, data);
	}
	
	public static <T> SystemResult<T> success(String msg){
		return new SystemResult<T>(SystemConstants.SUCCESS, msg, null);
	}
	
	public static <T> SystemResult<T> success(){
		return new SystemResult<T>(SystemConstants.SUCCESS, null, null);
	}
	
	public static <T> SystemResult<T> fail(String msg, T data){
		return new SystemResult<T>(SystemConstants.FAIL, msg, data);
	}
	
	public static <T> SystemResult<T> fail(String msg){
		return new SystemResult<T>(SystemConstants.FAIL, msg, null);
	}
	
	public static <T> SystemResult<T> fail(){
		return new SystemResult<T>(SystemConstants.FAIL, null, null);
	}
}
