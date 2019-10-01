/** 
 * Project Name:api-cleandata 
 * File Name:ZhiLianJobDetail.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月28日下午12:29:54 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;
/** 
* @author  Administrator
* @date 2019年7月28日 下午12:29:54 
* @version 1.0  
* @since   
*/

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "zhilian_job_detail")
public class ZhiLianJobDetail {
	
	@Id
	private String number;
	
	private String subJobType;
	
	private Integer jobStatus;
	
	@Column(columnDefinition = "longtext")
	private String jobDescPC;
	
	private String workAddress;
	
	private String latitude;
	
	private String longitude;
	
	private String publishTime;
	
	private String education;
	
	private String emailListPc;
	
	private String applyType;
	
	private Boolean FutureJob;
	
	private String salary60;
	
	private String workingExp;
	
	private String cityId;
	
	private String workCity;
	
	private Integer recruitNumber;
	
	private String name;
	
	private String cityDistrict;
	
	private String companyName;
	
	private String url;
	
	@Column(columnDefinition = "longtext")
	private String jobDesc;

	@Transient
	private List<ZhiLianWelfareLabel> detailWelfareTab;
	
	@Transient
	private List<ZhiLianSkillLabel> skillLabel;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSubJobType() {
		return subJobType;
	}

	public void setSubJobType(String subJobType) {
		this.subJobType = subJobType;
	}

	public Integer getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getJobDescPC() {
		return jobDescPC;
	}

	public void setJobDescPC(String jobDescPC) {
		this.jobDescPC = jobDescPC;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEmailListPc() {
		return emailListPc;
	}

	public void setEmailListPc(String emailListPc) {
		this.emailListPc = emailListPc;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public Boolean getFutureJob() {
		return FutureJob;
	}

	public void setFutureJob(Boolean futureJob) {
		FutureJob = futureJob;
	}

	public String getSalary60() {
		return salary60;
	}

	public void setSalary60(String salary60) {
		this.salary60 = salary60;
	}

	public String getWorkingExp() {
		return workingExp;
	}

	public void setWorkingExp(String workingExp) {
		this.workingExp = workingExp;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public Integer getRecruitNumber() {
		return recruitNumber;
	}

	public void setRecruitNumber(Integer recruitNumber) {
		this.recruitNumber = recruitNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityDistrict() {
		return cityDistrict;
	}

	public void setCityDistrict(String cityDistrict) {
		this.cityDistrict = cityDistrict;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public List<ZhiLianWelfareLabel> getDetailWelfareTab() {
		return detailWelfareTab;
	}

	public void setDetailWelfareTab(List<ZhiLianWelfareLabel> detailWelfareTab) {
		this.detailWelfareTab = detailWelfareTab;
	}

	public List<ZhiLianSkillLabel> getSkillLabel() {
		return skillLabel;
	}

	public void setSkillLabel(List<ZhiLianSkillLabel> skillLabel) {
		this.skillLabel = skillLabel;
	}
	
}
