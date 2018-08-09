package org.xfs.core.platform.job.model;

import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4095428005873733131L;
	/** 任务id */  
	private Long jobId;
	/** 任务名称 */  
	private String jobName;
	 /** 任务别名 */  
	private String aliasName;
	/** 触发器 */  
	private String jobTrigger;
	/** 任务分组 */
	private String jobGroup;
    /** 任务状态 */  
	private String status;
	/** 任务运行时间表达式 */  
	private String cronExpression;
    /** 是否异步 */  
	private boolean isSync;
    /** 任务描述 */  
	private String memo;
    /** 创建时间 */  
	private Date createdTime;
    /** 修改时间 */  
	private Date modifyTime;
	//定时发送的方式  
	private String sendStyle;
	
	//这里方便展示   
    private String  taskTime;

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getJobTrigger() {
		return jobTrigger;
	}

	public void setJobTrigger(String jobTrigger) {
		this.jobTrigger = jobTrigger;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public boolean isSync() {
		return isSync;
	}

	public void setSync(boolean isSync) {
		this.isSync = isSync;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSendStyle() {
		return sendStyle;
	}

	public void setSendStyle(String sendStyle) {
		this.sendStyle = sendStyle;
	}

	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + "]";
	}  
    
   
}
