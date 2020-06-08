package com.zben.mall.service.pms;

import com.zben.mall.dto.PmsProductAttributeCategoryItem;

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
}
