package com.zben.mall.service.pms.impl;

import com.zben.mall.dto.ProductAttrInfo;
import com.zben.mall.mapper.pms.PmsProductAttributeMapper;
import com.zben.mall.service.pms.PmsProductAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/6/8 0008 9:47
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {

    @Autowired
    PmsProductAttributeMapper productAttributeMapper;

    /**
     * 根据商品分类的id获取商品属性及属性分类
     * @param productCategoryId
     * @return
     */
    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeMapper.getProductAttrInfo(productCategoryId);
    }
}
