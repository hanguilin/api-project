/** 
 * Project Name:api-utils 
 * File Name:PrivateUtils.java 
 * Package Name: 
 * Date:2019年7月6日上午11:39:39 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
/** 
 * @author  Administrator
 * @date 2019年7月6日 上午11:39:39 
 * @version 1.0  
 * @since   
 */
package com.hanguilin.apiutils.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.common.collect.Maps;

/** 
 * @author  Administrator
 * @date 2019年7月6日 上午11:41:33 
 * @version 1.0  
 * @since   
 */
public class PrivateUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrivateUtils.class);

	/** 
	 * 获取非空字段的值
	 *
	 * @param object
	 * @return 
	 */
	public static Map<String, Object> getNotNullFieldValue(Object object){
		return getFieldValue(object, (o) -> null != o);
	}

	/** 
	 * 获取全部字段的值
	 *
	 * @param object
	 * @return 
	 */
	public static Map<String, Object> getAllFieldValue(Object object){
		return getFieldValue(object, (o) -> true);
	}

	private static Map<String, Object> getFieldValue(Object object, Predicate<Object> predicate){
		Map<String, Object> dataMap = Maps.newHashMap();
		Arrays.asList(BeanUtils.getPropertyDescriptors(object.getClass())).stream().filter(o -> !"class".equals(o.getName())).forEach(o->{
			try {
				Object value = o.getReadMethod().invoke(object);
				dataMap.put(o.getName(), value);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				LOGGER.error(e.getMessage());
			}
		});
		return dataMap;
	}

	/** 
	 * 复制不为空的字段值到另一个实体
	 *
	 * @param dest
	 * @param src 
	 */
	public static <D,S> void copyNotNullFieldValue(Collection<D> dest, S src) {
		Map<String, Object> keyValue = getNotNullFieldValue(src);
		D d = dest.stream().findFirst().get();
		Map<String, Method> keyMethod = Arrays.asList(BeanUtils.getPropertyDescriptors(d.getClass())).stream().collect(Collectors.toMap(PropertyDescriptor::getName, PropertyDescriptor::getWriteMethod));
		try {
			for (D d2 : dest) {
				for(Map.Entry<String, Method> entry : keyMethod.entrySet()) {
					if(keyValue.containsKey(entry.getKey())) {
						entry.getValue().invoke(d2, keyValue.get(entry.getKey()));
					}
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			LOGGER.error(e.getMessage());
		}
	}
}
