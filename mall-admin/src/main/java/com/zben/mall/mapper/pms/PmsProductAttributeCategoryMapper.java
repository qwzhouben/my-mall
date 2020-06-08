package com.zben.mall.mapper.pms;

import com.zben.mall.dto.PmsProductAttributeCategoryItem;
import com.zben.mall.model.pms.PmsProductAttributeCategory;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/5 0005 14:39
 */
public interface PmsProductAttributeCategoryMapper extends Mapper<PmsProductAttributeCategory> {

    /**
     * 获取所有商品属性分类及其下属性
     * @return
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}
