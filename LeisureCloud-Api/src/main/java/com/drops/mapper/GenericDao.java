package com.drops.mapper;

import com.drops.config.datasource.SwitchDataSource;
import com.drops.mapper.SqlProvider;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface GenericDao {
  @SelectProvider(type = SqlProvider.class, method = "selectByParem")
  List<LinkedHashMap<String, Object>> selectByParem(final String tableName,final String fields,final Map<String, Object> map);

  @UpdateProvider(type = SqlProvider.class, method = "updateByParem")
  void updateByParem(String paramString, Map<String, Object> paramMap1, Map<String, Object> paramMap2);

  @InsertProvider(type = SqlProvider.class, method = "insert")
  int insert(String paramString, Map<String, Object> paramMap);

  @DeleteProvider(type = SqlProvider.class, method = "deleteByParem")
  void deleteByParem(String paramString, Map<String, Object> paramMap);
}
