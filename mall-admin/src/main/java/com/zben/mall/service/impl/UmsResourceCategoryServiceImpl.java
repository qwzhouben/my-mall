package com.zben.mall.service.impl;

import com.zben.mall.mapper.UmsResourceCategoryMapper;
import com.zben.mall.model.UmsResourceCategory;
import com.zben.mall.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC:UmsResourceCategoryService实现
 * @author: zhouben
 * @date: 2020/5/27 0027 16:04
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {

    @Autowired
    UmsResourceCategoryMapper resourceCategoryMapper;

    /**
     * 查询所有后台资源分类
     * @return
     */
    @Override
    public List<UmsResourceCategory> listAll() {
        return resourceCategoryMapper.selectAll();
    }

    /**
     * 添加资源分类
     * @param resourceCategory
     * @return
     */
    @Override
    public boolean create(UmsResourceCategory resourceCategory) {
        return resourceCategoryMapper.insertSelective(resourceCategory) > 0;
    }

    /**
     * 修改资源分类
     * @param id
     * @param umsResourceCategory
     * @return
     */
    @Override
    public boolean update(Long id, UmsResourceCategory umsResourceCategory) {
        return resourceCategoryMapper.updateByPrimaryKeySelective(umsResourceCategory) > 0;
    }

    /**
     * 根据ID删除后台资源
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        return resourceCategoryMapper.deleteByPrimaryKey(id) > 0;
    }
}
