package com.zben.mall.service;

import com.zben.mall.model.UmsMenu;
import com.zben.mall.model.UmsResource;
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
    List<UmsRole> listAll();

    /**
     * 根据角色名称分页获取角色列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改角色状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Long id, Integer status);

    /**
     * 添加角色
     * @param role
     * @return
     */
    boolean create(UmsRole role);

    /**
     * 修改角色
     * @param id
     * @param role
     * @return
     */
    boolean update(Long id, UmsRole role);

    /**
     * 获取相关角色菜单
     * @param roleId
     * @return
     */
    List<UmsMenu> listMenu(Long roleId);

    /**
     * 给角色分配菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    boolean delete(List<Long> ids);

    /**
     * 获取角色相关资源
     * @param roleId
     * @return
     */
    List<UmsResource> listResource(Long roleId);

    /**
     * 给角色分配资源
     * @param roleId
     * @param resourceIds
     * @return
     */
    boolean allocResource(Long roleId, List<Long> resourceIds);
}
