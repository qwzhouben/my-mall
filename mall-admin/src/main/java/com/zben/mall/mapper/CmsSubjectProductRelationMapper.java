package com.zben.mall.mapper;

import com.zben.mall.model.pms.CmsSubjectProductRelation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/14 0014 15:45
 */
public interface CmsSubjectProductRelationMapper extends Mapper<CmsSubjectProductRelation> {
    /**
     * 批量创建
     */
    int insertList(@Param("list") List<CmsSubjectProductRelation> subjectProductRelationList);
}
