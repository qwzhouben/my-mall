package com.zben.mall.mapper.pms;

import com.zben.mall.dto.PmsProductResult;
import com.zben.mall.model.pms.PmsProduct;
import tk.mybatis.mapper.common.Mapper;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/4 0004 21:13
 */
public interface PmsProductMapper extends Mapper<PmsProduct> {

    /**
     * 根据商品id获取商品编辑信息
     * @param id
     * @return
     */
    PmsProductResult getUpdateInfo(Long id);
}
