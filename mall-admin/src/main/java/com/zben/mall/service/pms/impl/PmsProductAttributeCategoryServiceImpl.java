package com.zben.mall.service.pms.impl;

import com.zben.mall.dto.PmsProductAttributeCategoryItem;
import com.zben.mall.mapper.pms.PmsProductAttributeCategoryMapper;
import com.zben.mall.service.pms.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC:商品属性分类Service实现
 * @author: zhouben
 * @date: 2020/6/5 0005 14:12
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    @Autowired
    PmsProductAttributeCategoryMapper ProductAttributeCategoryMapper;

    /**
     * 获取所有商品属性分类及其下属性
     * @return
     */
    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return ProductAttributeCategoryMapper.getListWithAttr();
    }
}
