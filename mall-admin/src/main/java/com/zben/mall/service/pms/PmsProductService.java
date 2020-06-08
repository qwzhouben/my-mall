package com.zben.mall.service.pms;

import com.zben.mall.dto.PmsProductQueryParam;
import com.zben.mall.model.pms.PmsProduct;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/4 0004 21:04
 */
public interface PmsProductService {

    /**
     * 查询商品
     * @param productQueryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);
}
