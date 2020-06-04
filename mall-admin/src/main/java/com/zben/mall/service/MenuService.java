package com.zben.mall.service;

import com.zben.mall.model.UmsMenu;
import com.zben.mall.model.UmsMenuNode;

import java.util.List;

/**
 * @DESC:后台菜单管理Service
 * @AUTHOR: zhouben
 * @DATE: 2020/5/27 0027 14:04
 */
public interface MenuService {

    /**
     * 分页查询菜单
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 根据ID查询菜单详情
     * @param id
     * @return
     */
    UmsMenu getItem(Long id);

    /**
     * 添加后台菜单
     * @param umsMenu
     * @return
     */
    boolean create(UmsMenu umsMenu);

    /**
     * 根据ID删除后台菜单
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 修改菜单显示状态
     * @param id
     * @param hidden
     * @return
     */
    boolean updateHidden(Long id, Integer hidden);

    /**
     * 修改后台菜单
     * @param id
     * @param umsMenu
     * @return
     */
    boolean update(Long id, UmsMenu umsMenu);

    /**
     * 树形结构返回所有菜单列表
     * @return
     */
    List<UmsMenuNode> treeList();
}
