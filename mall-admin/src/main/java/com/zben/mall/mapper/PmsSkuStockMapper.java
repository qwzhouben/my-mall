package com.zben.mall.mapper;

import com.zben.mall.model.pms.PmsSkuStock;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/14 0014 15:41
 */
public interface PmsSkuStockMapper extends Mapper<PmsSkuStock> {
    /**
     * 批量插入操作
     */
    int insertList(@Param("list")List<PmsSkuStock> skuStockList);
}
