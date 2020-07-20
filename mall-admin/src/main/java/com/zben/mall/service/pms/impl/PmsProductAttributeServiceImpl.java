package com.zben.mall.service.pms.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.dto.PmsProductAttributeParam;
import com.zben.mall.dto.ProductAttrInfo;
import com.zben.mall.mapper.pms.PmsProductAttributeCategoryMapper;
import com.zben.mall.mapper.pms.PmsProductAttributeMapper;
import com.zben.mall.model.pms.PmsProductAttribute;
import com.zben.mall.model.pms.PmsProductAttributeCategory;
import com.zben.mall.service.pms.PmsProductAttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

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
    @Autowired
    PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    /**
     * 根据商品分类的id获取商品属性及属性分类
     * @param productCategoryId
     * @return
     */
    @Override
    public List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId) {
        return productAttributeMapper.getProductAttrInfo(productCategoryId);
    }

    /**
     * 根据属性分类查询属性列表或参数列表
     * @param cid
     * @param type
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(PmsProductAttribute.class);
        example.createCriteria().andEqualTo("productAttributeCategoryId", cid);
        return productAttributeMapper.selectByExample(example);
    }

    /**
     * 添加商品属性信息
     * @param productAttributeParam
     * @return
     */
    @Override
    @Transactional
    public boolean create(PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(productAttributeParam, productAttribute);
        productAttributeMapper.insertSelective(productAttribute);
        //新增商品属性以后需要更新商品分类数量
        PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(productAttribute.getProductAttributeCategoryId());
        if (productAttribute.getType() == 0) {
            productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() + 1);
        } else if (productAttribute.getType() == 1) {
            productAttributeCategory.setParamCount(productAttributeCategory.getParamCount() + 1);
        }
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory) > 0;
    }

    /**
     * 查询单个商品属性
     * @param id
     * @return
     */
    @Override
    public PmsProductAttribute getItem(Long id) {
        return productAttributeMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改商品属性信息
     * @param id
     * @param productAttributeParam
     * @return
     */
    @Override
    public boolean update(Long id, PmsProductAttributeParam productAttributeParam) {
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(productAttributeParam, productAttribute);
        productAttribute.setId(id);
        return productAttributeMapper.updateByPrimaryKeySelective(productAttribute) > 0;
    }

    /**
     * 批量删除商品属性
     * @param ids
     * @return
     */
    @Override
    public boolean delete(List<Long> ids) {
        PmsProductAttribute productAttribute = productAttributeMapper.selectByPrimaryKey(ids.get(0));
        Integer type = productAttribute.getType();
        PmsProductAttributeCategory productAttributeCategory = productAttributeCategoryMapper.selectByPrimaryKey(productAttribute.getProductAttributeCategoryId());
        Example example = new Example(PmsProductAttribute.class);
        example.createCriteria().andIn("id", ids);
        int count = productAttributeMapper.deleteByExample(example);
        if (type == 0) {
            if (productAttributeCategory.getAttributeCount() >= count) {
                productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() - count);
            } else {
                productAttributeCategory.setAttributeCount(0);
            }
        } else if (type == 1) {
            if(productAttributeCategory.getParamCount()>=count){
                productAttributeCategory.setParamCount(productAttributeCategory.getParamCount()-count);
            }else{
                productAttributeCategory.setParamCount(0);
            }
        }
        return productAttributeCategoryMapper.updateByPrimaryKey(productAttributeCategory) > 0;
    }
}
