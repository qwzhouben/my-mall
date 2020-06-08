package com.zben.mall.service.pms;

import com.zben.mall.dto.PmsProductCategoryParam;
import com.zben.mall.model.pms.PmsProductCategory;
import com.zben.mall.model.pms.PmsProductCategoryWithChildrenItem;

import java.util.List;

/**
 * @DESC:商品分类Service
 * @AUTHOR: zhouben
 * @DATE: 2020/6/4 0004 20:36
 */
public interface PmsProductCategoryService {

    /**
     * 查询所有一级分类及子分类
     * @return
     */
    List<PmsProductCategoryWithChildrenItem> listWithChildren();

    /**
     * 分页查询商品分类
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProductCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 添加产品分类
     * @param productCategoryParam
     * @return
     */
    boolean create(PmsProductCategoryParam productCategoryParam);

    /**
     * 修改导航栏显示状态
     * @param ids
     * @param navStatus
     * @return
     */
    boolean updateNavStatus(List<Long> ids, Integer navStatus);

    /**
     * 修改显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    boolean updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 根据id获取商品分类
     * @param id
     * @return
     */
    PmsProductCategory getItem(Long id);

    /**
     * 修改商品分类
     * @param id
     * @param productCategoryParam
     * @return
     */
    boolean update(Long id, PmsProductCategoryParam productCategoryParam);

    /**
     * 删除商品分类
     * @param id
     * @return
     */
    boolean delete(Long id);
}
