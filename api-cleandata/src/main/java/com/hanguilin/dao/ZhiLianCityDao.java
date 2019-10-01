/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianCityDao.java 
 * Package Name:com.hanguilin.dao 
 * Date:2019年7月27日下午7:15:19 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hanguilin.entity.ZhiLianCity;

/** 
* @author  Administrator
* @date 2019年7月27日 下午7:15:19 
* @version 1.0  
* @since   
*/
public interface ZhiLianCityDao extends JpaRepository<ZhiLianCity, Integer> {

	@Query(value = "select item_ids from zhilian_city where job_number = ?1", nativeQuery = true)
	String findItemIdByNumber(String number);
}
