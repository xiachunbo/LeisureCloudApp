package com.drops.lock;

import java.util.Date;

/**
 *

 * 数据库分布式锁表
 *
 * @Author lst
 * @Date 2020-05-20 18:00
 */
public class DataBaseLock {

    private String id;
    private Date UpdateDate;
    /**
     *锁名称
     */
    private String lockKey;

    /**
     *锁存活时间（单位：秒）  避免死锁
     */
    private int timeOut;

    public String getLockKey() {
        return lockKey;
    }

    public void setLockKey(String lockKey) {
        this.lockKey = lockKey;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Date updateDate) {
        UpdateDate = updateDate;
    }
}
