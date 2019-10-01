/** 
 * Project Name:api-user 
 * File Name:UserDao.java 
 * Package Name:com.hanguilin.apiuser.dao 
 * Date:2019年8月11日下午2:14:55 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hanguilin.apiuser.entity.User;

/** 
* @author  Administrator
* @date 2019年8月11日 下午2:14:55 
* @version 1.0  
* @since   
*/
public interface UserDao extends JpaRepository<User, Long> {

	User findByName(String name);
}
