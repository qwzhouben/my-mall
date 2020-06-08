package com.zben.mall.service.pms.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.dto.PmsProductCategoryParam;
import com.zben.mall.mapper.pms.PmsProductCategoryAttributeRelationMapper;
import com.zben.mall.mapper.pms.PmsProductCategoryMapper;
import com.zben.mall.mapper.pms.PmsProductMapper;
import com.zben.mall.model.pms.PmsProduct;
import com.zben.mall.model.pms.PmsProductCategory;
import com.zben.mall.model.pms.PmsProductCategoryAttributeRelation;
import com.zben.mall.model.pms.PmsProductCategoryWithChildrenItem;
import com.zben.mall.service.pms.PmsProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @DESC:商品分类Service
 * @author: zhouben
 * @date: 2020/6/4 0004 20:36
 */
@Service
public class PmsProductCategoryServiceImpl implements PmsProductCategoryService {


    @Autowired
    PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    PmsProductCategoryAttributeRelationMapper pmsProductCategoryAttributeRelationMapper;
    @Autowired
    PmsProductMapper productMapper;

    /**
     * 查询所有一级分类及子分类
     * @return
     */
    @Override
    public List<PmsProductCategoryWithChildrenItem> listWithChildren() {
        List<PmsProductCategory> productCategoryList = productCategoryMapper.selectAll();
        List<PmsProductCategoryWithChildrenItem> result = productCategoryList.stream()
                .filter(category -> category.getParentId().equals(0L))
                .map(category -> convertCategoryNode(category, productCategoryList))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 分页查询商品分类
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(PmsProductCategory.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", parentId);
        example.orderBy("sort").desc();
        return productCategoryMapper.selectByExample(example);
    }

    /**
     * 添加产品分类
     * @param productCategoryParam
     * @return
     */
    @Override
    @Transactional
    public boolean create(PmsProductCategoryParam productCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(productCategoryParam, productCategory);
        productCategory.setProductCount(0);
        //没有父分类时为一级分类
        setCategoryLevel(productCategory);
        productCategoryMapper.insertSelective(productCategory);
        //创建筛选属性关联
        if (!CollectionUtils.isEmpty(productCategoryParam.getProductAttributeIdList())) {
            insertRelationList(productCategory.getId(), productCategoryParam.getProductAttributeIdList());
        }
        return Boolean.TRUE;
    }

    /**
     * 修改导航栏显示状态
     * @param ids
     * @param navStatus
     * @return
     */
    @Override
    @Transactional
    public boolean updateNavStatus(List<Long> ids, Integer navStatus) {
        PmsProductCategory pmsProductCategory = new PmsProductCategory();
        pmsProductCategory.setNavStatus(navStatus);
        Example example = new Example(PmsProductCategory.class);
        example.createCriteria().andIn("id", ids);
        return productCategoryMapper.updateByExampleSelective(pmsProductCategory, example) > 0;
    }

    /**
     * 修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    @Override
    @Transactional
    public boolean updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsProductCategory pmsProductCategory = new PmsProductCategory(){{
            setShowStatus(showStatus);
        }};
        Example example = new Example(PmsProductCategory.class);
        example.createCriteria().andIn("id", ids);
        return productCategoryMapper.updateByExampleSelective(pmsProductCategory, example) > 0;
    }

    /**
     * 根据id获取商品分类
     * @param id
     * @return
     */
    @Override
    public PmsProductCategory getItem(Long id) {
        return productCategoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改商品分类
     * @param id
     * @param productCategoryParam
     * @return
     */
    @Override
    @Transactional
    public boolean update(Long id, PmsProductCategoryParam productCategoryParam) {
        PmsProductCategory productCategory = new PmsProductCategory();
        BeanUtils.copyProperties(productCategoryParam, productCategory);
        productCategory.setId(id);
        setCategoryLevel(productCategory);
        //更新商品分类时更新商品中的名称
        PmsProduct product = new PmsProduct(){{setProductCategoryName(productCategory.getName());}};
        Example example = new Example(PmsProduct.class);
        example.createCriteria().andEqualTo("productCategoryId", id);
        productMapper.updateByExampleSelective(product, example);
        //同时更新筛选属性的信息
        Example example_ = new Example(PmsProductCategoryAttributeRelation.class);
        example_.createCriteria().andEqualTo("productCategoryId", id);
        pmsProductCategoryAttributeRelationMapper.deleteByExample(example);
        if (!CollectionUtils.isEmpty(productCategoryParam.getProductAttributeIdList())) {
            insertRelationList(id, productCategoryParam.getProductAttributeIdList());
        }
        return productCategoryMapper.updateByPrimaryKeySelective(productCategory) > 0;
    }

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    @Override
    @Transactional
    public boolean delete(Long id) {
        //删除筛选属性关系
        Example example = new Example(PmsProductCategoryAttributeRelation.class);
        example.createCriteria().andEqualTo("productCategoryId", id);
        pmsProductCategoryAttributeRelationMapper.deleteByExample(example);
        return productCategoryMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 批量插入商品分类与筛选属性关联表
     * @param productCategoryId
     * @param productAttributeIdList
     */
    public void insertRelationList(Long productCategoryId, List<Long> productAttributeIdList) {
        List<PmsProductCategoryAttributeRelation> relationList = productAttributeIdList.stream()
                .map(productAttributeId -> {
                    return new PmsProductCategoryAttributeRelation(){{
                       setProductCategoryId(productCategoryId);
                       setProductAttributeId(productAttributeId);
                    }};
                })
                .collect(Collectors.toList());
        pmsProductCategoryAttributeRelationMapper.insertList(relationList);
    }

    /**
     * 根据分类的parentId设置分类的level
     * @param productCategory
     */
    private void setCategoryLevel(PmsProductCategory productCategory) {
        //没有父分类时为一级分类
        if (productCategory.getParentId() == 0) {
            productCategory.setLevel(0);
        } else {
            //有父分类根据父分类设置level
            PmsProductCategory parentCate = productCategoryMapper.selectByPrimaryKey(productCategory.getParentId());
            if (parentCate != null) {
                productCategory.setLevel(parentCate.getLevel() + 1);
            } else {
                productCategory.setLevel(0);
            }
        }

    }

    private PmsProductCategoryWithChildrenItem convertCategoryNode(PmsProductCategory category, List<PmsProductCategory> productCategoryList) {
        PmsProductCategoryWithChildrenItem item = new PmsProductCategoryWithChildrenItem();
        BeanUtils.copyProperties(category, item);
        List<PmsProductCategoryWithChildrenItem> children = productCategoryList.stream()
                .filter(subCate -> subCate.getParentId().equals(category.getId()))
                .map(subCate -> convertCategoryNode(subCate, productCategoryList))
                .collect(Collectors.toList());
        item.setChildren(children);
        return item;
    }
}
