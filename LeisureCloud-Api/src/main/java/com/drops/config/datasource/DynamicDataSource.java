package com.drops.config.datasource;

import com.drops.config.datasource.DataSourceContextHolder;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.LogManager;


public class DynamicDataSource extends AbstractRoutingDataSource {
    static Logger logger = Logger.getLogger(DataSourceContextHolder.class);

    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDB();
    }
}