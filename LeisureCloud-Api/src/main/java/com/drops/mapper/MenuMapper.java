package com.drops.mapper;

import com.drops.domain.SysMenu;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MenuMapper {
  @Select({"select distinct m.* from crm_sys_menu m inner join crm_sys_role_menu rm on m.menu_id = rm.menuId inner join crm_sys_user_role ru on ru.roleId = rm.roleId where isDel = 1 and isEnable = 1 and ru.userId = #{userId} order by m.sequence"})
  List<SysMenu> listByUserId(Long paramLong);
}