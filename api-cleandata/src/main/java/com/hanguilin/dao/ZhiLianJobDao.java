/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianJobDao.java 
 * Package Name:com.hanguilin.dao 
 * Date:2019年7月27日下午6:20:49 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanguilin.entity.ZhiLianJob;

/** 
* @author  Administrator
* @date 2019年7月27日 下午6:20:49 
* @version 1.0  
* @since   
*/
public interface ZhiLianJobDao extends JpaRepository<ZhiLianJob, String> {

}
