package com.zben.mall.service.pms;

import com.zben.mall.dto.PmsProductAttributeParam;
import com.zben.mall.dto.ProductAttrInfo;
import com.zben.mall.model.pms.PmsProductAttribute;

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

    /**
     * 根据分类查询属性列表或参数列表
     * @param cid
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性信息
     * @param productAttributeParam
     * @return
     */
    boolean create(PmsProductAttributeParam productAttributeParam);

    /**
     * 查询单个商品属性
     * @param id
     * @return
     */
    PmsProductAttribute getItem(Long id);

    /**
     * 修改商品属性信息
     * @param id
     * @param productAttributeParam
     * @return
     */
    boolean update(Long id, PmsProductAttributeParam productAttributeParam);

    /**
     * 批量删除商品属性
     * @param ids
     * @return
     */
    boolean delete(List<Long> ids);
}
