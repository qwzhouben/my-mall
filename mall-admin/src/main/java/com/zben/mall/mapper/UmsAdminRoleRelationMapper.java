package com.zben.mall.mapper;

import com.zben.mall.model.UmsMenu;
import com.zben.mall.model.UmsResource;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @DESC:后台用户与角色自定义mapper
 * @AUTHOR: zhouben
 * @DATE: 2020/5/21 0021 14:07
 */
public interface UmsAdminRoleRelationMapper extends Mapper<UmsResource> {

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 根据管理员ID获取对应菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
}
