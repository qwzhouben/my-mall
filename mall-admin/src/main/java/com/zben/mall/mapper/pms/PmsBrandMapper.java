package com.zben.mall.mapper.pms;

import com.zben.mall.model.brand.PmsBrand;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/4 0004 20:27
 */
public interface PmsBrandMapper extends Mapper<PmsBrand> {

    /**
     * 批量更新显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(@Param("ids") List<Long> ids, @Param("showStatus") Integer showStatus);

    /**
     * 批量更新厂家制造商状态
     * @param ids
     * @param factoryStatus
     * @return
     */
    int updateFactoryStatus(@Param("ids") List<Long> ids, @Param("factoryStatus") Integer factoryStatus);
}
