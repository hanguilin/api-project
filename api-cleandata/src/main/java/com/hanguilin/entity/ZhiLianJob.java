/** 
 * Project Name:api-cleandata 
 * File Name:JobEntity.java 
 * Package Name:com.hanguilin.entity 
 * Date:2019年7月27日下午5:00:07 
 * Copyright (c) 2019, hanguilin All Rights Reserved. 
 * 
 */
package com.hanguilin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/** 
* @author  Administrator
* @date 2019年7月27日 下午5:00:07 
* @version 1.0  
* @since   
*/
@Entity
@Table(name = "zhilian_job")
public class ZhiLianJob {
	
	@Id
	private String number;
	
	private Boolean applied;
	
	private Integer applyType;
	
	private String businessArea;
	
	private Integer chatWindow;
	
	private String companyLogo;
	
	private Integer distance;
	
	private String emplType;
	
	private Integer expandCount;
	
	private Integer feedbackRation;
	
	private Boolean futureJob;
	
	private String futureJobUrl;
	
	private String industry;
	
	private Boolean isShow; 
	
	private String jobName;
	
	@Column(columnDefinition = "longtext")
	private String positionLabel;
	
	private String positionURL;
	
	private String rate;
	
	private Integer rootOrgId;
	
	private String salary;
	
	private Boolean saleType;
	
	private Integer score;
	
	private Boolean selected;
	
	private Integer staffId;
	
	private Integer tagIntHighend;
	
	private String timeState;
	
	private String updateDate;
	
	private Integer vipLevel;
	
	private String welfare;
	
	private String workingExp;
	
	private String eduLevel;
	
	private String companyNumber;
	
	private Integer typeId;
	
	@Transient
	private ZhiLianJobType jobType;
	
	@Transient
	private ZhiLianCity city;
	
	@Transient
	private ZhiLianCompany company;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Boolean getApplied() {
		return applied;
	}

	public void setApplied(Boolean applied) {
		this.applied = applied;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	public Integer getChatWindow() {
		return chatWindow;
	}

	public void setChatWindow(Integer chatWindow) {
		this.chatWindow = chatWindow;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getEmplType() {
		return emplType;
	}

	public void setEmplType(String emplType) {
		this.emplType = emplType;
	}

	public Integer getExpandCount() {
		return expandCount;
	}

	public void setExpandCount(Integer expandCount) {
		this.expandCount = expandCount;
	}

	public Integer getFeedbackRation() {
		return feedbackRation;
	}

	public void setFeedbackRation(Integer feedbackRation) {
		this.feedbackRation = feedbackRation;
	}

	public Boolean getFutureJob() {
		return futureJob;
	}

	public void setFutureJob(Boolean futureJob) {
		this.futureJob = futureJob;
	}

	public String getFutureJobUrl() {
		return futureJobUrl;
	}

	public void setFutureJobUrl(String futureJobUrl) {
		this.futureJobUrl = futureJobUrl;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getPositionLabel() {
		return positionLabel;
	}

	public void setPositionLabel(String positionLabel) {
		this.positionLabel = positionLabel;
	}

	public String getPositionURL() {
		return positionURL;
	}

	public void setPositionURL(String positionURL) {
		this.positionURL = positionURL;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Integer getRootOrgId() {
		return rootOrgId;
	}

	public void setRootOrgId(Integer rootOrgId) {
		this.rootOrgId = rootOrgId;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Boolean getSaleType() {
		return saleType;
	}

	public void setSaleType(Boolean saleType) {
		this.saleType = saleType;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public Integer getTagIntHighend() {
		return tagIntHighend;
	}

	public void setTagIntHighend(Integer tagIntHighend) {
		this.tagIntHighend = tagIntHighend;
	}

	public String getTimeState() {
		return timeState;
	}

	public void setTimeState(String timeState) {
		this.timeState = timeState;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(Integer vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getWelfare() {
		return welfare;
	}

	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}

	public String getWorkingExp() {
		return workingExp;
	}

	public void setWorkingExp(String workingExp) {
		this.workingExp = workingExp;
	}

	public ZhiLianJobType getJobType() {
		return jobType;
	}

	public void setJobType(ZhiLianJobType jobType) {
		this.jobType = jobType;
	}

	public ZhiLianCity getCity() {
		return city;
	}

	public void setCity(ZhiLianCity city) {
		this.city = city;
	}

	public String getEduLevel() {
		return eduLevel;
	}

	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}

	public ZhiLianCompany getCompany() {
		return company;
	}

	public void setCompany(ZhiLianCompany company) {
		this.company = company;
	}

	public String getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		this.companyNumber = companyNumber;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

}
