package com.zben.mall.service;

import com.zben.mall.model.UmsResourceCategory;

import java.util.List;

/**
 * @DESC:资源分类Service
 * @AUTHOR: zhouben
 * @DATE: 2020/5/27 0027 16:00
 */
public interface UmsResourceCategoryService {

    /**
     * 查询所有后台资源分类
     * @return
     */
    List<UmsResourceCategory> listAll();

    /**
     * 添加资源分类
     * @param resourceCategory
     * @return
     */
    boolean create(UmsResourceCategory resourceCategory);

    /**
     * 修改资源分类
     * @param id
     * @param umsResourceCategory
     * @return
     */
    boolean update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * 根据ID删除后台资源
     * @param id
     * @return
     */
    boolean delete(Long id);
}
