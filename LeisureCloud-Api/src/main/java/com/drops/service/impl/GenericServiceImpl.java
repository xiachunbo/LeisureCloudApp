package com.drops.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.drops.config.datasource.SwitchDataSource;
import com.drops.config.logpage.OperationLogDetail;
import com.drops.config.logpage.OperationType;
import com.drops.config.logpage.OperationUnit;
import com.drops.mapper.GenericDao;
import com.drops.service.GenericService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class GenericServiceImpl implements GenericService {

    public static final String CACHE_NAME = "deptCache";

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    @Override
    @Cacheable(value = CACHE_NAME)
    @OperationLogDetail(detail = "查询API",level = 3,operationUnit = OperationUnit.TABLE,operationType = OperationType.SELECT)
    @SwitchDataSource("datasource2")
    public List<LinkedHashMap<String, Object>> selectByParem(String tableName, String fields, Map<String, Object> map) {
        // 显示所有的Cache空间
        System.out.println(StringUtils.join(cacheManager.getCacheNames(), ","));
        return genericDao.selectByParem(tableName,fields,map);
    }
    @Override
    @SwitchDataSource("datasource1")
    @OperationLogDetail(detail = "插入API",level = 3,operationUnit = OperationUnit.TABLE,operationType = OperationType.INSERT)
    public void insert(String tableName,Map<String, Object> map)throws RuntimeException{
        genericDao.insert(tableName,map);
    }

    @Override
    @CachePut(value = CACHE_NAME)
    @CacheEvict(value = CACHE_NAME)
    @SwitchDataSource("datasource1")
    @OperationLogDetail(detail = "修改API",level = 3,operationUnit = OperationUnit.TABLE,operationType = OperationType.UPDATE)
    public void updateByParem(String tableName, Map<String, Object> parem,Map<String, Object> map){
        genericDao.updateByParem(tableName,parem,map);
    }
    @Override
    @CacheEvict(value = CACHE_NAME)
    @SwitchDataSource("datasource1")
    @OperationLogDetail(detail = "删除API",level = 3,operationUnit = OperationUnit.TABLE,operationType = OperationType.DELETE)
    public void deleteByParem(String tableName,Map<String, Object> map){
        genericDao.deleteByParem(tableName,map);
    }

}
