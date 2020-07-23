package com.drops.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.drops.config.datasource.DataSourceConfig;
import com.drops.config.datasource.DataSourceContextHolder;
import com.drops.config.datasource.DynamicDataSource;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class DataSourceConfig {
    private final Map<Object, Object> dsMap = new HashMap<>();


    @Bean(name = {"datasource1"})
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = {"datasource2"})
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }


    @Primary
    @Bean(name = {"dynamicDataSource"})
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        dynamicDataSource.setDefaultTargetDataSource(dataSource1());
        DataSourceContextHolder.setDB("datasource1");

        this.dsMap.put("datasource1", dataSource1());
        this.dsMap.put("datasource2", dataSource2());
        dynamicDataSource.setTargetDataSources(this.dsMap);
        return (DataSource) dynamicDataSource;
    }


    @Bean
    public PlatformTransactionManager transactionManager() {
        return (PlatformTransactionManager) new DataSourceTransactionManager(dynamicDataSource());
    }


    public Boolean reload(String dbName) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(dataSource2());
        dynamicDataSource.setTargetDataSources(this.dsMap);
        DataSourceContextHolder.setDB(dbName);
        dynamicDataSource.afterPropertiesSet();
        return Boolean.TRUE;
    }
}