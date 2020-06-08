package com.zben.mall.mapper.pms;

import com.zben.mall.model.pms.PmsProductCategoryAttributeRelation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/5 0005 16:44
 */
public interface PmsProductCategoryAttributeRelationMapper extends Mapper<PmsProductCategoryAttributeRelation> {

    /**
     * 批量插入
     * @param relationList
     * @return
     */
    int insertList(@Param("list") List<PmsProductCategoryAttributeRelation> relationList);
}
