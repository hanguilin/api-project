/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianJobDetailDao.java 
 * Package Name:com.hanguilin.dao 
 * Date:2019年7月28日下午12:51:38 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanguilin.entity.ZhiLianJobDetail;

/** 
* @author  Administrator
* @date 2019年7月28日 下午12:51:38 
* @version 1.0  
* @since   
*/
public interface ZhiLianJobDetailDao extends JpaRepository<ZhiLianJobDetail, String> {

}
