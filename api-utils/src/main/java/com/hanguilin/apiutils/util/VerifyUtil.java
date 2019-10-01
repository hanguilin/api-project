/** 
 * Project Name:api-user 
 * File Name:VerifyUtil.java 
 * Package Name:com.hanguilin.apiuser.util 
 * Date:2019年8月11日下午2:05:31 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiutils.util;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

/** 
* @author  Administrator
* @date 2019年8月11日 下午2:05:31 
* @version 1.0  
* @since   
*/
public class VerifyUtil {
	
	public static boolean isEmpty(Collection<?> collection) {
		return null == collection || collection.isEmpty();
	}
	
	public static boolean isEmpty(String value) {
		return StringUtils.isBlank(value);
	} 
	
	public static boolean isEmpty(Object obj) {
		return null == obj;
	}
}
