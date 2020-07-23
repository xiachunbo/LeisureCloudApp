package com.drops.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public interface GenericService {

  List<LinkedHashMap<String, Object>> selectByParem(String tableName, String fields, Map<String, Object> map);


  void insert(String tableName,Map<String, Object> map);


  void updateByParem(String tableName, Map<String, Object> parem,Map<String, Object> map);


  void deleteByParem(String tableName,Map<String, Object> map);

}
