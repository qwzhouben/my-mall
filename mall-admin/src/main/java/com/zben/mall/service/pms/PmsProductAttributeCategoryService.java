package com.zben.mall.service.pms;

import com.zben.mall.dto.PmsProductAttributeCategoryItem;
import com.zben.mall.model.pms.PmsProductAttributeCategory;

import java.util.List;

/**
 * @DESC:商品属性分类Service
 * @AUTHOR: zhouben
 * @DATE: 2020/6/5 0005 14:12
 */
public interface PmsProductAttributeCategoryService {

    /**
     * 获取所有商品属性分类及其下属性
     * @return
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();

    /**
     * 分页获取所有商品属性分类
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性分类
     * @param name
     * @return
     */
    boolean create(String name);

    /**
     * 修改商品属性分类
     * @param id
     * @param name
     * @return
     */
    boolean update(Long id, String name);

    /**
     * 删除单个商品属性分类
     * @param id
     * @return
     */
    boolean delete(Long id);
}
