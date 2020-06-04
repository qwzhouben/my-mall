package com.zben.mall.mapper;

import com.zben.mall.model.UmsMenu;
import com.zben.mall.model.UmsResource;
import com.zben.mall.model.UmsRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/5/21 0021 17:49
 */
public interface UmsRoleMapper extends Mapper<UmsRole> {

    int updateById(UmsRole role);

    /**
     * 获取相关角色菜单
     * @param roleId
     * @return
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 获取角色相关资源
     * @param roleId
     * @return
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
