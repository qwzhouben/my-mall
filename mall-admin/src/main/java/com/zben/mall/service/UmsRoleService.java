package com.zben.mall.service;

import com.zben.mall.model.UmsMenu;
import com.zben.mall.model.UmsRole;

import java.util.List;

/**
 * @DESC:后台角色管理service
 * @AUTHOR: zhouben
 * @DATE: 2020/5/21 0021 17:18
 */
public interface UmsRoleService {

    /**
     * 根据管理员ID获取对应菜单
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 获取所有角色
     * @return
     */
    List<UmsRole> list();
}
