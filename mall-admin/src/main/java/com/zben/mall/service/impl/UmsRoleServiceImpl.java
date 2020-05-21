package com.zben.mall.service.impl;

import com.zben.mall.mapper.UmsAdminRoleRelationMapper;
import com.zben.mall.mapper.UmsRoleMapper;
import com.zben.mall.model.UmsMenu;
import com.zben.mall.model.UmsRole;
import com.zben.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC:UmsRoleService实现类
 * @author: zhouben
 * @date: 2020/5/21 0021 17:19
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {

    @Autowired
    UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    UmsRoleMapper roleMapper;

    /**
     * 根据管理员ID获取对应菜单
     * @param adminId
     * @return
     */
    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return adminRoleRelationMapper.getMenuList(adminId);
    }

    /**
     * 获取所有角色
     * @return
     */
    @Override
    public List<UmsRole> list() {
        return roleMapper.selectAll();
    }
}
