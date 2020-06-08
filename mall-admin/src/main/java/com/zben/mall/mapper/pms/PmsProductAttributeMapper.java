package com.zben.mall.mapper.pms;

import com.zben.mall.dto.ProductAttrInfo;
import com.zben.mall.model.pms.PmsProductAttribute;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/5 0005 14:58
 */
public interface PmsProductAttributeMapper extends Mapper<PmsProductAttribute> {

    /**
     * 根据商品分类的id获取商品属性及属性分类
     * @param productCategoryId
     * @return
     */
    List<ProductAttrInfo> getProductAttrInfo(@Param("categoryId") Long productCategoryId);
}
