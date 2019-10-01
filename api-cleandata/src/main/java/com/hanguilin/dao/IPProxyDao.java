/** 
 * Project Name:api-cleandata 
 * File Name:IPProxyDao.java 
 * Package Name:com.hanguilin.dao 
 * Date:2019年7月28日上午11:37:53 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanguilin.entity.IPProxy;

/** 
* @author  Administrator
* @date 2019年7月28日 上午11:37:53 
* @version 1.0  
* @since   
*/
public interface IPProxyDao extends JpaRepository<IPProxy, String>{

}
