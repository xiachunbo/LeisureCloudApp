package com.drops.service;

import java.util.List;
import java.util.Map;

public interface TableService {
  List<Map<String, Object>> listTable();
  
  List<Map<String, Object>> listTableColumn(String paramString);
  
  List<Map<String, Object>> get(String paramString1, String paramString2, String paramString3, Map paramMap);
  
  int insertBatch(String paramString, Map paramMap, List<Map> paramList);
}