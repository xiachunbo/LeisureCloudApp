package com.drops.service;

import com.drops.domain.SysMenu;
import java.util.List;

public interface SysMenuService {
  List<SysMenu> listByUserId(Long paramLong);
}