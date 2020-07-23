package com.drops.mapper;

import com.drops.domain.SysMenu;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TableMapper {
  @Select({"select * from information_schema.TABLES where TABLE_SCHEMA=(select database())"})
  List<Map<String, Object>> listTable();
  
  @Select({"select * from information_schema.COLUMNS where TABLE_SCHEMA = (select database()) and TABLE_NAME=#{tableName}"})
  List<Map<String, Object>> listTableColumn(String paramString);
  
  List<Map<String, Object>> get(@Param("ids") String paramString1, @Param("tableName") String paramString2, @Param("fields") String paramString3, @Param("parem") Map paramMap);
  
  int insertBatch(@Param("tableName") String paramString, @Param("parem") Map paramMap, @Param("list") List<Map> paramList);
  
  @Select({"select distinct m.* from crm_sys_menu m inner join crm_sys_role_menu rm on m.id = rm.menuId inner join crm_sys_user_role ru on ru.roleId = rm.roleId where isDel = 1 and isEnable = 1 and ru.userId = #{userId} order by m.sequence"})
  List<SysMenu> listByUserId(Long paramLong);
}