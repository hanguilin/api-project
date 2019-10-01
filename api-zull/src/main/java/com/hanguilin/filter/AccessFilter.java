/** 
 * Project Name:api-zull 
 * File Name:AccessFilter.java 
 * Package Name:com.hanguilin 
 * Date:2019年8月11日下午3:41:33 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.filter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.hanguilin.apiutils.constants.SystemConstants;
import com.hanguilin.apiutils.returns.SystemResult;
import com.hanguilin.apiutils.util.VerifyUtil;
import com.hanguilin.constants.ZuulStateConstants;
import com.hanguilin.feign.TokenAPI;
import com.hanguilin.vo.ResponseResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/** 
 * @author  Administrator
 * @date 2019年8月11日 下午3:41:33 
 * @version 1.0  
 * @since   
 */
@Component
public class AccessFilter extends ZuulFilter {

	private static final String LOGIN_URL = "/v1/user/login";

	private static final List<String> EXCLUDE_URLS = new ArrayList<String>();

	@Autowired
	private TokenAPI tokenAPI;

	static {
		EXCLUDE_URLS.add(LOGIN_URL);
	}

	/** 
	 * shouldFilter
	 *
	 * @return 
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/** 
	 * 
	 *
	 * @return 
	 */
	@Override
	public Object run() {
		RequestContext context = RequestContext.getCurrentContext();
		HttpServletRequest request = context.getRequest();
		String url = request.getRequestURL().toString();

		if(checkUrl(url)) {
			context.setSendZuulResponse(true);
			context.setResponseStatusCode(ZuulStateConstants.SUCCESS);
			context.set("isSuccess", true);
		}else {
			String token = request.getParameter(SystemConstants.COOKIE_TOKEN_PARAM);
			Cookie[] cookies = request.getCookies();
			if(null != cookies) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals(SystemConstants.COOKIE_TOKEN_PARAM)) {
						token = cookie.getValue();
					}
				}
			}
			if(VerifyUtil.isEmpty(token)) {
				context.setSendZuulResponse(false);
				context.setResponseStatusCode(ZuulStateConstants.FAIL);
				context.set("isSuccess", false);
				writeResponse(context, SystemConstants.FAIL, null);
			}else {
				SystemResult<String> isLogined = tokenAPI.doIsLogined(token);
				if(isLogined.getCode() == SystemConstants.SUCCESS) {
					String currentUser = token.contains("_")?token.split("_")[0]:null;
					context.addZuulRequestHeader("CURRENT_USER", currentUser);
					context.setSendZuulResponse(true);
					context.setResponseStatusCode(ZuulStateConstants.SUCCESS);
					context.set("isSuccess", true);
				}else {
					writeResponse(context, SystemConstants.FAIL, isLogined.getMsg());
				}
			}
		}
		
		return null;
	}

	/** 
	 * filterType
	 *
	 * @return 
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/** 
	 * 
	 *
	 * @return 
	 */
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	private boolean checkUrl(String url) {
		for (String excludeUrl : EXCLUDE_URLS) {
			if(url.contains(excludeUrl)) {
				return true;
			}
		}
		return false;
	}
	
	private void writeResponse(RequestContext context, int code, String msg) {
		context.getResponse().setContentType("application/json;charset=utf-8");
		ResponseResult response = new ResponseResult();
		response.setCode(code);
		String responseMsg = VerifyUtil.isEmpty(msg) ? "unAuthcationed" : msg;
		response.setMsg(responseMsg);
		context.setResponseBody(JSON.toJSONString(response));
	}
}
