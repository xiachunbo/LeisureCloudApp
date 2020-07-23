package com.drops.config.logpage;

import com.drops.config.logpage.OperationUnit;


public enum OperationUnit {
    UNKNOWN("unknown"),
    USER("user"),
    EMPLOYEE("employee"),
    TABLE("employee"),
    Redis("redis");

    private String value;


    OperationUnit(String value) {
        this.value = value;
    }


    public String getValue() {
        return this.value;
    }


    public void setValue(String value) {
        this.value = value;
    }
}