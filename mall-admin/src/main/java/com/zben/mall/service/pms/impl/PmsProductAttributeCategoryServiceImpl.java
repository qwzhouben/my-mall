package com.zben.mall.service.pms.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.dto.PmsProductAttributeCategoryItem;
import com.zben.mall.mapper.pms.PmsProductAttributeCategoryMapper;
import com.zben.mall.model.pms.PmsProductAttributeCategory;
import com.zben.mall.service.pms.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @DESC:商品属性分类Service实现
 * @author: zhouben
 * @date: 2020/6/5 0005 14:12
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    @Autowired
    PmsProductAttributeCategoryMapper productAttributeCategoryMapper;

    /**
     * 获取所有商品属性分类及其下属性
     * @return
     */
    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return productAttributeCategoryMapper.getListWithAttr();
    }

    /**
     * 分页获取所有商品属性分类
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        return productAttributeCategoryMapper.selectByExample(new Example(PmsProductAttributeCategory.class));
    }

    /**
     * 添加商品属性分类
     * @param name
     * @return
     */
    @Override
    public boolean create(String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory(){{
            setName(name);
        }};
        return productAttributeCategoryMapper.insertSelective(productAttributeCategory) > 0;
    }

    /**
     * 修改商品属性分类
     * @param id
     * @param name
     * @return
     */
    @Override
    public boolean update(Long id, String name) {
        PmsProductAttributeCategory productAttributeCategory = new PmsProductAttributeCategory(){{
           setId(id);
           setName(name);
        }};
        return productAttributeCategoryMapper.updateByPrimaryKeySelective(productAttributeCategory) > 0;
    }

    /**
     * 删除单个商品属性分类
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        return productAttributeCategoryMapper.deleteByPrimaryKey(id) > 0;
    }
}
