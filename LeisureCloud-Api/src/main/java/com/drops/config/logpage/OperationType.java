package com.drops.config.logpage;

import com.drops.config.logpage.OperationType;


public enum OperationType {
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;


    public String getValue() {
        return this.value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    OperationType(String s) {
        this.value = s;
    }
}