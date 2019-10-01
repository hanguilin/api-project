/** 
 * Project Name:api-utils 
 * File Name:PrivateCache.java 
 * Package Name:com.hanguilin.api.utils 
 * Date:2019年7月6日上午11:41:33 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiutils.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/** 
* @author  Administrator
* @date 2019年7月6日 上午11:41:33 
* @version 1.0  
* @since   
*/
public class PrivateCache<T> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PrivateCache.class);
	private static final int THOUSAND = 1000;
	private int second;
	private static final String SPLITOR = "-";
	private AtomicLong atomicLong = new AtomicLong();
	private Map<String, T> data = Maps.newHashMap();

	public PrivateCache() {
		super();
	}

	public PrivateCache(int second) {
		super();
		this.second = second;
	}
	
	public String setData(T t) {
		long time = System.currentTimeMillis();
		cleaData(time);
		String tag = time + SPLITOR + atomicLong.getAndIncrement();
		data.put(tag, t);
		return tag;
	}

	public T getData(String tag) {
		return data.get(tag);
	}
	/** 
	 * 
	 *
	 * @param time 
	 */
	private void cleaData(long time) {
		Set<String> keySet = data.keySet();
		for (String key : keySet) {
			try {
				if(time - Long.valueOf(key.split(SPLITOR)[0]) > second * THOUSAND) {
					data.remove(key);
				}
			} catch (Exception e) {
				LOGGER.error(key+" is invalid",e.getMessage());
			}
		}
	}
}
