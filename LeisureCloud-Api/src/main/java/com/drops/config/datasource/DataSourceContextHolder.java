package com.drops.config.datasource;

import com.drops.config.datasource.DataSourceContextHolder;
import org.apache.log4j.Logger;


public class DataSourceContextHolder {
    static Logger logger = Logger.getLogger(DataSourceContextHolder.class);

    public static final String DEFAULT_DS = "datasource1";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();


    public static void setDB(String dbType) {
        contextHolder.set(dbType);
    }


    public static String getDB() {
        return contextHolder.get();
    }


    public static void clearDB() {
        contextHolder.remove();
    }
}