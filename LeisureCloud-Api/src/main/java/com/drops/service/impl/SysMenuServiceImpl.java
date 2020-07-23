package com.drops.service.impl;

import com.drops.domain.SysMenu;
import com.drops.mapper.MenuMapper;
import com.drops.service.SysMenuService;
import com.drops.service.impl.SysMenuServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl
        implements SysMenuService {
    @Autowired
    public MenuMapper menuMapper;

    public List<SysMenu> listByUserId(Long id) {
        return this.menuMapper.listByUserId(id);
    }
}