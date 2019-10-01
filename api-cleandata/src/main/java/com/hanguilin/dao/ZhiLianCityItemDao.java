/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianCityItemDao.java 
 * Package Name:com.hanguilin.dao 
 * Date:2019年7月27日下午6:22:12 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hanguilin.entity.ZhiLianCityItem;

/** 
* @author  Administrator
* @date 2019年7月27日 下午6:22:12 
* @version 1.0  
* @since   
*/
public interface ZhiLianCityItemDao extends JpaRepository<ZhiLianCityItem, Integer> {

	@Query(value = "select code from zhilian_city_item where name in (?1)", nativeQuery = true)
	List<String> findCodeByNamesIn(List<String> names);
}
