/** 
 * Project Name:api-user 
 * File Name:User.java 
 * Package Name:com.hanguilin.apiuser.entity 
 * Date:2019年7月7日下午5:54:59 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.apiuser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @author  Administrator
* @date 2019年7月7日 下午5:54:59 
* @version 1.0  
* @since   
*/
@Entity
@Table(name="api_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true, columnDefinition = "varchar(10) comment'用户名'")
	private String name;
	
	@Column(columnDefinition = "varchar(20) comment'密码'")
	private String password;
	
	@Column(columnDefinition = "varchar(11) comment'电话'")
	private String telphone;
	
	@Column(columnDefinition = "varchar(30) comment'邮箱'")
	private String email;
	
	@Column(columnDefinition = "varchar(100) comment'地址'")
	private String address;
	
	@Column(columnDefinition = "varchar(1) comment'性别'")
	private String gender;
	
	@Column(columnDefinition = "int(3) comment'年龄'")
	private int age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
