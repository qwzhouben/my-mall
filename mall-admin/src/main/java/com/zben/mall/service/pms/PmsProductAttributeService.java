package com.zben.mall.service.pms;

import com.zben.mall.dto.ProductAttrInfo;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/8 0008 9:47
 */
public interface PmsProductAttributeService {

    /**
     * 根据商品分类的id获取商品属性及属性分类
     * @param productCategoryId
     * @return
     */
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
