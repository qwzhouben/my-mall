package com.zben.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.mapper.UmsAdminRoleRelationMapper;
import com.zben.mall.mapper.UmsRoleMapper;
import com.zben.mall.model.*;
import com.zben.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

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
    public List<UmsRole> listAll() {
        return roleMapper.selectAll();
    }

    /**
     * 根据角色名称分页获取角色列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsRole.class);
        if (!StringUtils.isEmpty(keyword)) {
            example.createCriteria().andLike("name", "%" + keyword + "%");
        }
        return roleMapper.selectByExample(example);
    }

    /**
     * 修改角色状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(Long id, Integer status) {
        UmsRole role = new UmsRole(){{
            setId(id);
            setStatus(status);
        }};
        return roleMapper.updateById(role) > 0;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @Override
    public boolean create(UmsRole role) {
        return roleMapper.insertSelective(role) > 0;
    }

    /**
     * 修改角色
     * @param id
     * @param role
     * @return
     */
    @Override
    public boolean update(Long id, UmsRole role) {
        return roleMapper.updateByPrimaryKeySelective(role) > 0;
    }

    /**
     * 获取相关角色菜单
     * @param roleId
     * @return
     */
    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return roleMapper.getMenuListByRoleId(roleId);
    }

    /**
     * 给角色分配菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    @Override
    @Transactional
    public boolean allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        Example example = new Example(UmsAdminRoleRelation.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        adminRoleRelationMapper.deleteByExample(example);

        //批量插入新关系
        adminRoleRelationMapper.batchInsert(roleId, menuIds);
        return Boolean.TRUE;
    }

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    @Override
    public boolean delete(List<Long> ids) {
        Example example = new Example(UmsRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        return roleMapper.deleteByExample(example) > 0;
    }

    /**
     * 获取角色相关资源
     * @param roleId
     * @return
     */
    @Override
    public List<UmsResource> listResource(Long roleId) {
        return roleMapper.getResourceListByRoleId(roleId);
    }

    /**
     * 给角色分配资源
     * @param roleId
     * @param resourceIds
     * @return
     */
    @Override
    @Transactional
    public boolean allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有关系
//        Example example = new Example(UmsRoleResourceRelation.class);
//        example.createCriteria().andEqualTo("roleId", roleId);
        adminRoleRelationMapper.deleteByRoleId(roleId);
        //添加新关系
        adminRoleRelationMapper.batchInsertRoleAndResource(roleId, resourceIds);
        return Boolean.TRUE;
    }
}
