package com.drops.domain.job;

import com.drops.domain.job.SysJob;

import java.util.Date;


public class SysJob {
    private Integer jobId;
    private String beanName;
    private String methodName;
    private String methodParams;
    private String cronExpression;
    private Integer jobStatus;
    private String remark;
    private Date createTime;
    private Date updateTime;

    public Integer getJobId() {
        return this.jobId;
    }


    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }


    public String getBeanName() {
        return this.beanName;
    }


    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }


    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public String getMethodParams() {
        return this.methodParams;
    }


    public void setMethodParams(String methodParams) {
        this.methodParams = methodParams;
    }


    public String getCronExpression() {
        return this.cronExpression;
    }


    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }


    public Integer getJobStatus() {
        return this.jobStatus;
    }


    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }


    public String getRemark() {
        return this.remark;
    }


    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Date getUpdateTime() {
        return this.updateTime;
    }


    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}