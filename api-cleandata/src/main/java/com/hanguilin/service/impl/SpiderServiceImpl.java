/** 
 * Project Name:api-cleandata 
 * File Name:SpiderServiceImpl.java 
 * Package Name:com.hanguilin.service.impl 
 * Date:2019年7月27日下午3:20:34 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.hanguilin.apiutils.returns.SystemResult;
import com.hanguilin.dao.IPProxyDao;
import com.hanguilin.dao.ZhiLianCityDao;
import com.hanguilin.dao.ZhiLianCityItemDao;
import com.hanguilin.dao.ZhiLianCompanyDao;
import com.hanguilin.dao.ZhiLianJobDao;
import com.hanguilin.dao.ZhiLianJobDetailDao;
import com.hanguilin.dao.ZhiLianJobTypeItemDao;
import com.hanguilin.entity.IPProxy;
import com.hanguilin.entity.ZhiLianCity;
import com.hanguilin.entity.ZhiLianCityItem;
import com.hanguilin.entity.ZhiLianCompany;
import com.hanguilin.entity.ZhiLianJob;
import com.hanguilin.entity.ZhiLianJobDetail;
import com.hanguilin.entity.ZhiLianJobTypeItem;
import com.hanguilin.service.SpiderService;
import com.hanguilin.vo.RequestZhiLianVo;
import com.hanguilin.vo.XEResult;
import com.hanguilin.vo.ZhiLianResult;
import com.hanguilin.vo.ZhiLianResultData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/** 
 * @author  Administrator
 * @date 2019年7月27日 下午3:20:34 
 * @version 1.0  
 * @since   
 */
@Service
public class SpiderServiceImpl implements SpiderService {

	private static final String URI = "https://fe-api.zhaopin.com/c/i/sou";

	private static final String HTML_URI = "https://sou.zhaopin.com";

	private static final String MESSAGE_INFO_URI_PREFIX = "https://jobs.zhaopin.com/";

	// 小二ip代理
	private static final String XEDL_URL = "http://api.xedl.321194.com/getip?num=100&type=2&port=1&pack=3563&ts=0&cs=1&lb=1";

	private static final String XEDL_URL2 = "http://www.xiaoerdaili.com/faq//7569.html";

	private static final String MOGU_URL = "http://piping.mogumiao.com/proxy/api/get_ip_al?appKey=9d896f209b0c41a2b3139815056062ed&count=100&expiryDate=0&format=1&newLine=2";

	private static final int SUCCESS_CODE = 200;

	private static final Logger LOGGER = LoggerFactory.getLogger(SpiderServiceImpl.class);

	private CountDownLatch countDown = new CountDownLatch(50);

	@Autowired 
	private ZhiLianCityDao cityDao;

	@Autowired 
	private ZhiLianCityItemDao cityItemDao;

	@Autowired 
	private ZhiLianCompanyDao companyDao;

	@Autowired 
	private ZhiLianJobDao jobDao;

	@Autowired 
	private ZhiLianJobTypeItemDao jobTypeItemDao;

	@Autowired
	private IPProxyDao ipProxyDao;

	@Autowired
	private ZhiLianJobDetailDao jobDetailDao;

	/** 
	 * 爬智联招聘数据
	 * 
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public SystemResult<String> spiderZhiLian() {
		//		clearData();
		// 获取城市信息
		//		spiderCityInfo();
		// 获取工作简介
		//		spiderJobInfo();
		// 获取工作详情
		spiderJobMeesageInfo();
		// 保存代理IP数据
		//		getIpProxyInfo();
		// 每日免费
		//		getIpProxyInfo2();
		// 蘑菇6元限量
		//		getIpProxy3();
		return SystemResult.success();
	}

	/** 
	 * 
	 * 
	 */
	private void spiderJobMeesageInfo() {
		List<ZhiLianJob> all = jobDao.findAll();
		List<String> jobNumberList = all.stream().map(o->o.getNumber()).collect(Collectors.toList());

		LOGGER.info("******开始获取智联招聘工作详情数据******");
		StopWatch watchJobMessages = new StopWatch();
		watchJobMessages.start();
		//				jobNumberList.stream().forEach(o->{
		//					String realUrl = String.format(MESSAGE_INFO_URI_PREFIX + "%s.htm", o);
		//					spiderJobMessageInfoMethod(realUrl, o);
		//				});

		/**
		 * test
		 */
		//		String o = jobNumberList.stream().findFirst().get();
		//		String itemIds = cityDao.findItemIdByNumber(o);
		//		String cityCode = itemIds.split(",")[0];
		//		String realUrl = String.format(MESSAGE_INFO_URI_PREFIX + "%s.htm", o);
		//		spiderJobMessageInfoMethod(realUrl, o, cityCode);

		watchJobMessages.stop();
		LOGGER.info("******完成获取智联招聘工作详情数据******");
		LOGGER.info(watchJobMessages.prettyPrint());
	}

	/** 
	 * 
	 *
	 * @param messageInfoUri 
	 * @param number 
	 */
	private void spiderJobMessageInfoMethod(String messageInfoUri, String number, String cityCode) {
		ZhiLianJobDetail detailList = null;
		try {
			LOGGER.info(String.format("******正在获取<%s>详情数据******", number));
			String json = getScriptData(messageInfoUri, cityCode);
			if(json.isEmpty()) {
				LOGGER.error(String.format("******获取<%s>script数据失败,提前结束******", number));
				return;
			}
			JSONObject result = JSONObject.fromObject(json);
			JSONObject jobInfo = result.getJSONObject("jobInfo");
			JSONObject jobDetail = jobInfo.getJSONObject("jobDetail");
			JSONObject detailedCompany = jobDetail.getJSONObject("detailedCompany");
			JSONObject detailedPosition = jobDetail.getJSONObject("detailedPosition");

			detailList = JSON.parseObject(detailedPosition.toString(), ZhiLianJobDetail.class);
			LOGGER.info(String.format("******获取<%s>详情数据成功******", number));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(String.format("******获取<%s>详情数据失败******", number), e.getMessage());
		}
		if(detailList != null) {
			jobDetailDao.save(detailList);
		}
	}

	private void spiderJobInfo() {
		List<ZhiLianCityItem> cityItems = cityItemDao.findAll();
		List<ZhiLianCityItem> parentCitys = cityItems.stream().filter(o->o.getParentCode() == null).collect(Collectors.toList());
		parentCitys.stream().forEach(o->{
			RequestZhiLianVo vo = new RequestZhiLianVo();
			vo.setStart("0");
			vo.setPageSize("100");
			vo.setCityId(o.getCode());
			vo.setSalary("0,0");
			vo.setWorkExperience("-1");
			vo.setEducation("-1");
			vo.setCompanyType("-1");
			vo.setEmploymentType("-1");
			vo.setJobWelfareTag("-1");
			vo.setKw("Java开发");
			vo.setKt("3");
			vo.setCityName(o.getName());

			LOGGER.info(String.format("******开始获取<%s>智联招聘数据******", vo.getCityName()));
			StopWatch watchJobs = new StopWatch();
			watchJobs.start();
			spiderJobMethod(vo);
			watchJobs.stop();
			LOGGER.info(String.format("******结束获取<%s>智联招聘数据******", vo.getCityName()));
			LOGGER.info(watchJobs.prettyPrint());
		});
	}

	private void clearData() {
		cityDao.deleteAll();
		cityItemDao.deleteAll();
		companyDao.deleteAll();
		jobDao.deleteAll();
		jobTypeItemDao.deleteAll();
	}

	/** 
	 * 
	 *
	 * @param vo
	 * @return 
	 */
	private SystemResult<String> spiderJobMethod(RequestZhiLianVo vo) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("start", vo.getStart()));
		nvps.add(new BasicNameValuePair("pageSize", vo.getPageSize()));
		nvps.add(new BasicNameValuePair("cityId", vo.getCityId()));
		nvps.add(new BasicNameValuePair("salary", vo.getSalary()));
		nvps.add(new BasicNameValuePair("workExperience", vo.getWorkExperience()));
		nvps.add(new BasicNameValuePair("education", vo.getEducation()));
		nvps.add(new BasicNameValuePair("companyType", vo.getCompanyType()));
		nvps.add(new BasicNameValuePair("employmentType", vo.getEmploymentType()));
		nvps.add(new BasicNameValuePair("jobWelfareTag", vo.getJobWelfareTag()));
		nvps.add(new BasicNameValuePair("kw", vo.getKw()));
		nvps.add(new BasicNameValuePair("kt", vo.getKt()));
		try {
			CloseableHttpResponse response = executeHttpGet(nvps, URI);
			if(response.getStatusLine().getStatusCode() == SUCCESS_CODE) {
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity, "utf-8");
				ZhiLianResult zhiLianResult = JSON.parseObject(result, ZhiLianResult.class);
				dealResponseData(zhiLianResult, vo);
			}else {
				LOGGER.error("GET 请求失败.....");
				return SystemResult.fail("GET 请求失败.....");
			}
		} catch (Exception e) {
			return SystemResult.fail("请求失败");
		}

		return SystemResult.success();
	}

	private CloseableHttpResponse executeHttpGet(List<NameValuePair> nvps, String url){
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			/**
			 * 创建HttpClient对象
			 */
			client = HttpClients.createDefault();
			/**
			 * 创建URIBuilder
			 */
			URIBuilder uriBuilder = new URIBuilder(url);
			/**
			 * 设置参数
			 */
			uriBuilder.addParameters(nvps);
			/**
			 * 创建get请求
			 */
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			/**
			 * 设置请求头部编码
			 */
			httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"));
			/**
			 * 设置返回编码
			 */
			httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=utf-8"));
			/**
			 * 执行请求
			 */
			response = client.execute(httpGet);
		} catch (Exception e) {
			LOGGER.error("GET请求失败", e.getMessage());
		} finally {
			if(client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return response;
	}

	/** 
	 * 
	 *
	 * @param zhiLianResult 
	 * @param vo 
	 */
	private void dealResponseData(ZhiLianResult zhiLianResult, RequestZhiLianVo vo) {
		ZhiLianResultData data = zhiLianResult.getData();
		if(data == null) {
			return;
		}
		Integer count = data.getCount();
		if(count == null) {
			return;
		}
		List<ZhiLianJob> jobs = data.getResults();
		List<ZhiLianCompany> companys = new ArrayList<ZhiLianCompany>();
		List<ZhiLianCity> citys = new ArrayList<ZhiLianCity>();
		List<ZhiLianJobTypeItem> jobTypeItems = new ArrayList<ZhiLianJobTypeItem>();

		jobs.stream().forEach(o->{
			o.setCompanyNumber(o.getCompany().getNumber());

			companys.add(o.getCompany());

			List<ZhiLianCityItem> cityItemsTemp = o.getCity().getItems();
			List<String> cityItemNames = cityItemsTemp.stream().map(k->k.getName()).collect(Collectors.toList());
			List<String> cityItemCodes = cityItemDao.findCodeByNamesIn(cityItemNames);
			String itemIds = String.join(",", cityItemCodes);
			String display = String.join("-", cityItemNames);
			citys.add(new ZhiLianCity(itemIds, display, o.getNumber()));

			List<ZhiLianJobTypeItem> jobTypeItemsTemp = o.getJobType().getItems();
			jobTypeItemsTemp.stream().forEach(k->k.setJobNumber(o.getNumber()));
			jobTypeItems.addAll(jobTypeItemsTemp);
		});

		cityDao.save(citys);
		companyDao.save(companys);
		jobTypeItemDao.save(jobTypeItems);
		jobDao.save(jobs);

		LOGGER.info(String.format("******当前城市:<%s>,数据量:<%s>,开始页:<%s>,每页数据条数:<%s>,此次获取到<%s>条******", vo.getCityName(), data.getCount(),  vo.getStart(), vo.getPageSize(), jobs == null ? 0 : jobs.size()));

		Integer start = Integer.valueOf(vo.getStart());
		Integer pageSize = Integer.valueOf(vo.getPageSize());
		if(start + pageSize < count) {
			vo.setStart(String.valueOf(start + pageSize));
			spiderJobMethod(vo);
		}
	}

	private void spiderCityInfo() {
		try {
			LOGGER.info("******开始获取智联招聘城市数据******");
			StopWatch watchcitys = new StopWatch();
			watchcitys.start();

			String json = getScriptData(HTML_URI, null);

			JSONObject result = JSONObject.fromObject(json);
			JSONObject basic = result.getJSONObject("basic");
			JSONObject dict = basic.getJSONObject("dict");
			JSONObject location = dict.getJSONObject("location");
			JSONArray province = location.getJSONArray("province");
			JSONArray all = location.getJSONArray("all");
			JSONArray other = location.getJSONArray("other");

			List<ZhiLianCityItem> provinceList = JSON.parseArray(province.toString(), ZhiLianCityItem.class);
			List<ZhiLianCityItem> allList = JSON.parseArray(all.toString(), ZhiLianCityItem.class);
			List<ZhiLianCityItem> otherList = JSON.parseArray(other.toString(), ZhiLianCityItem.class);

			List<ZhiLianCityItem> total = new ArrayList<ZhiLianCityItem>();
			List<ZhiLianCityItem> needSaveItems = new ArrayList<ZhiLianCityItem>();

			total.addAll(provinceList); 
			total.addAll(allList);
			total.addAll(otherList);

			dealItems(total, needSaveItems);

			cityItemDao.save(needSaveItems);
			watchcitys.stop();

			LOGGER.info(String.format("******<%s>条城市数据保存成功******", needSaveItems.size()));
			LOGGER.info(watchcitys.prettyPrint());
		} catch (Exception e) {
			LOGGER.error("******城市数据保存失败******", e.getMessage());
		}
	}

	private String getScriptData(String uri, String cityCode){
		countDown.countDown();
		String json = null;
		String host = null;
		Integer port = null;
		String refer = "https://sou.zhaopin.com/?jl="+cityCode+"&kw=Java开发&kt=3&sf=0&st=0";
		System.out.println(refer);
		try {
			Map<String, Integer> proxy = getIpProxy();
			Entry<String, Integer> entry = proxy.entrySet().stream().findFirst().get();
			host = entry.getKey();
			port = entry.getValue();
			LOGGER.info(String.format("******ip<%s>,port<%s>正在获取script数据******", host, port));
			Connection conn = Jsoup.connect(uri)
					.timeout(6000)
					.userAgent(getUserAgent())
					.ignoreContentType(true)
					.header("Referer", refer)
					.proxy(host, port);

			Document doc = conn.get();
			Elements elements = doc.getElementsByTag("script");
			Element element = elements.stream().filter(o->o.toString().contains("__INITIAL_STATE__")).findFirst().get();
			String text = element.toString();
			json = text.substring(text.indexOf("=") + 1, text.indexOf("</script>"));
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(String.format("******ip<%s>,port<%s>获取script数据失败******", host, port));
			if(countDown.getCount() != 0) {
				getScriptData(uri, cityCode);
			}
		}
		return json;
	}

	/** 
	 * 
	 *
	 * @return 
	 */
	private Map<String, Integer> getIpProxy() {
		Map<String, Integer> map = Maps.newHashMap();
		List<IPProxy> all = ipProxyDao.findAll();
		Random r = new Random();
		int i = r.nextInt(all.size());
		IPProxy ipProxy = all.get(i);
		map.put(ipProxy.getIp(), ipProxy.getPort());
		return map;
	}

	private String getUserAgent() {
		Random r = new Random();
		String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
				"Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
				"Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
				"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
				"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
		"Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};
		int i = r.nextInt(14);
		return ua[i];
	}

	/** 
	 * 
	 *
	 * @param total
	 * @param needSaveItems 
	 */
	private void dealItems(List<ZhiLianCityItem> total,
			List<ZhiLianCityItem> needSaveItems) {
		total.stream().forEach(o->{
			needSaveItems.add(o);
			List<ZhiLianCityItem> sublist = o.getSublist();
			sublist.stream().forEach(k->{
				k.setParentCode(o.getCode());
				needSaveItems.add(k);
				List<ZhiLianCityItem> secondSublist = k.getSublist();
				secondSublist.stream().forEach(j->{
					j.setParentCode(k.getCode());
					needSaveItems.add(j);
				});
			});
		});
	}

	private void getIpProxyInfo() {
		try {
			LOGGER.info("******开始保存IP代理信息******");
			List<NameValuePair> nvps = Collections.emptyList();
			CloseableHttpResponse response = executeHttpGet(nvps, XEDL_URL);
			if(response.getStatusLine().getStatusCode() == SUCCESS_CODE) {
				HttpEntity entity = response.getEntity();
				String result = EntityUtils.toString(entity, "utf-8");
				XEResult xeResult = JSON.parseObject(result, XEResult.class);
				List<IPProxy> data = xeResult.getData();
				int sum = 0;
				if(null != data) {
					data.forEach(o->o.setType("HTTP"));
					List<IPProxy> saved = ipProxyDao.save(data);
					sum = saved.size();
				}
				LOGGER.info(String.format("******成功保存<%s>条IP代理信息******", sum));
			}else {
				LOGGER.error("******GET请求失败******");
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("连接代理IP站点失败", e.getMessage());
		}
	}

	private void getIpProxyInfo2() {
		LOGGER.info("******开始保存IP代理信息******");
		try {
			Document doc = Jsoup.connect(XEDL_URL2).get();
			Element content = doc.getElementById("content");
			Elements pTags = content.getElementsByTag("p");
			List<IPProxy> ipProxys = pTags.stream().filter(o->{
				String text = o.text();
				return text.contains("[高匿]");
			}).map(o->{
				String text = o.text();
				String ip = text.substring(0, text.indexOf(":"));
				String port = text.substring(text.indexOf(":") + 1, text.indexOf("["));
				// 需保存type和address
				IPProxy ipProxy = new IPProxy();
				ipProxy.setIp(ip);
				ipProxy.setPort(Integer.valueOf(port));
				return ipProxy;
			}).collect(Collectors.toList());
			List<IPProxy> saved = ipProxyDao.save(ipProxys);
			LOGGER.info(String.format("******共保存<%s>条IP代理信息******", saved.size()));
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("******保存IP代理信息失败******");
		}
	}

	private void getIpProxy3() {
		LOGGER.info("******开始保存IP代理信息******");
		List<IPProxy> proxyList = null;
		try {
			Document doc = Jsoup.connect(MOGU_URL).get();
			JSONObject jsonObject = JSONObject.fromObject(doc.text());
			List<Map<String,String>> list = (List<Map<String,String>>) jsonObject.get("msg");
			proxyList = list.stream().map(o->{
				IPProxy proxy = new IPProxy();
				String ip = (String)o.get("ip");
				Integer port = Integer.valueOf(o.get("port"));
				proxy.setIp(ip);
				proxy.setPort(port);
				return proxy;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(proxyList != null) {
			List<IPProxy> saved = ipProxyDao.save(proxyList);
			LOGGER.info(String.format("******共保存<%s>条IP代理信息******", saved.size()));
		}
	}
}

