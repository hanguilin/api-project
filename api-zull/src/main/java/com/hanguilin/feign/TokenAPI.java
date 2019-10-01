/** 
 * Project Name:api-zull 
 * File Name:TokenAPI.java 
 * Package Name:com.hanguilin.feign 
 * Date:2019年8月11日下午3:36:24 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.feign;
/** 
* @author  Administrator
* @date 2019年8月11日 下午3:36:24 
* @version 1.0  
* @since   
*/

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hanguilin.apiutils.returns.SystemResult;
import com.hanguilin.feign.fallback.TokenFallback;

@Component
@FeignClient(name = "api-user", path = "/", fallback = TokenFallback.class)
public interface TokenAPI {
	
	@GetMapping("/v1/user/isLogined")
	public SystemResult<String> doIsLogined(@RequestParam("token" )String token);
}
