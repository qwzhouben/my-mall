package com.zben.mall.service;

import com.zben.mall.dto.UmsAdminParam;
import com.zben.mall.model.UmsAdmin;
import com.zben.mall.model.UmsResource;
import com.zben.mall.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @DESC:后台管理service
 * @AUTHOR: zhouben
 * @DATE: 2020/5/21 0021 13:56
 */
public interface UmsAdminService {

    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 根据用户名获取后台管理员
     * @param username
     * @return
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 获取指定用户的可访问权限
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 获取用户信息
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);

    /**
     * 根据用户名或昵称分页查询用户
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 用户注册
     * @param param
     * @return
     */
    UmsAdmin register(UmsAdminParam param);

    /**
     * 修改指定用户信息
     * @param id
     * @param umsAdmin
     * @return
     */
    boolean update(Long id, UmsAdmin umsAdmin);

    /**
     * 删除指定用户恓
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 获取指定用户的角色
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 给用户分配角色
     * @param adminId
     * @param roleIds
     * @return
     */
    boolean updateRole(Long adminId, List<Long> roleIds);
}
