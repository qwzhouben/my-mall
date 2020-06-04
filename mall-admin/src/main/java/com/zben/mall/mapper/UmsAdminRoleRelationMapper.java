package com.zben.mall.mapper;

import com.zben.mall.model.UmsAdminRoleRelation;
import com.zben.mall.model.UmsMenu;
import com.zben.mall.model.UmsResource;
import com.zben.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @DESC:后台用户与角色自定义mapper
 * @AUTHOR: zhouben
 * @DATE: 2020/5/21 0021 14:07
 */
public interface UmsAdminRoleRelationMapper extends Mapper<UmsAdminRoleRelation> {

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    /**
     * 获取指定用户角色
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 根据adminId删除
     * @param adminId
     */
    int deleteByAdminId(@Param("adminId") Long adminId);

    /**
     * 批量插入新关系
     * @param roleId
     * @param menuIds
     */
    void batchInsert(@Param("roleId") Long roleId, @Param("menuIds") List<Long> menuIds);

    /**
     * 批量插入新关系
     * @param roleId
     * @param resourceIds
     */
    void batchInsertRoleAndResource(@Param("roleId") Long roleId, @Param("resourceIds") List<Long> resourceIds);

    /**
     * 根据角色Id删除
     * @param roleId
     */
    void deleteByRoleId(@Param("roleId") Long roleId);
}
