package com.drops.config.logpage;

import com.drops.config.logpage.OperationLog;

import java.util.Date;


public class OperationLog {
    private String id;
    private Date createTime;
    private Integer level;
    private String operationUnit;
    private String method;
    private String args;
    private String userId;
    private String userName;
    private String describe;
    private String operationType;
    private Long runTime;
    private String returnValue;

    public String toString() {
        return "OperationLog{id='" + this.id + '\'' + ", createTime=" + this.createTime + ", level=" + this.level + ", operationUnit='" + this.operationUnit + '\'' + ", method='" + this.method + '\'' + ", args='" + this.args + '\'' + ", userId='" + this.userId + '\'' + ", userName='" + this.userName + '\'' + ", describe='" + this.describe + '\'' + ", operationType='" + this.operationType + '\'' + ", runTime=" + this.runTime + ", returnValue='" + this.returnValue + '\'' + '}';
    }


    public Long getRunTime() {
        return this.runTime;
    }


    public void setRunTime(Long runTime) {
        this.runTime = runTime;
    }


    public String getReturnValue() {
        return this.returnValue;
    }


    public void setReturnValue(String returnValue) {
        this.returnValue = returnValue;
    }


    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public Date getCreateTime() {
        return this.createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Integer getLevel() {
        return this.level;
    }


    public void setLevel(Integer level) {
        this.level = level;
    }


    public String getOperationUnit() {
        return this.operationUnit;
    }


    public void setOperationUnit(String operationUnit) {
        this.operationUnit = operationUnit;
    }


    public String getMethod() {
        return this.method;
    }


    public void setMethod(String method) {
        this.method = method;
    }


    public String getArgs() {
        return this.args;
    }


    public void setArgs(String args) {
        this.args = args;
    }


    public String getUserId() {
        return this.userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return this.userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescribe() {
        return this.describe;
    }


    public void setDescribe(String describe) {
        this.describe = describe;
    }


    public String getOperationType() {
        return this.operationType;
    }


    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}