/** 
 * Project Name:api-zull 
 * File Name:TokenFallback.java 
 * Package Name:com.hanguilin.feign.fallback 
 * Date:2019年8月11日下午3:37:29 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.feign.fallback;

import com.hanguilin.apiutils.returns.SystemResult;
import com.hanguilin.feign.TokenAPI;

/** 
* @author  Administrator
* @date 2019年8月11日 下午3:37:29 
* @version 1.0  
* @since   
*/
public class TokenFallback implements TokenAPI{

	/** 
	 * doIsLogined
	 *
	 * @param token
	 * @return 
	 */
	@Override
	public SystemResult<String> doIsLogined(String token) {
		return SystemResult.fail("用户服务调用失败，请稍后再试");
	}

}
