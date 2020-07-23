package com.drops.common.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseService<T, T2> {
  int deleteByPrimaryKey(String paramString);
  
  int insertSelective(T paramT);
  
  T selectByPrimaryKey(String paramString);
  
  int updateByPrimaryKeySelective(T paramT);
  
  int updateByExampleSelective(@Param("record") T paramT, @Param("example") T2 paramT2);
  
  int updateByExample(@Param("record") T paramT, @Param("example") T2 paramT2);
  
  List<T> selectByExample(T2 paramT2);
  
  long countByExample(T2 paramT2);
  
  int deleteByExample(T2 paramT2);
}