package com.drops.service.impl;

import com.drops.mapper.TableMapper;
import com.drops.service.TableService;
import com.drops.service.impl.TableServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl implements TableService {
    public static final String CACHE_NAME = "deptCache";
    @Autowired
    public TableMapper tableMapper;

    public List<Map<String, Object>> listTable() {
        return this.tableMapper.listTable();
    }


    @Cacheable({"deptCache"})
     public List<Map<String, Object>> listTableColumn(String tableName) {
        return this.tableMapper.listTableColumn(tableName);
    }


    public List<Map<String, Object>> get(String ids, String tableName, String fields, Map parem) {

        List<Map<String, Object>> maps = this.tableMapper.get(ids, tableName, fields, parem);
        return maps;
    }


    public int insertBatch(String tableName, Map parem, List<Map> list) {
        int result = this.tableMapper.insertBatch(tableName, parem, list);
        return result;
    }
}