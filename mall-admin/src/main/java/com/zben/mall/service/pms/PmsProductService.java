package com.zben.mall.service.pms;

import com.zben.mall.dto.PmsProductParam;
import com.zben.mall.dto.PmsProductQueryParam;
import com.zben.mall.dto.PmsProductResult;
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

    /**
     * 创建商品
     * @param productParam
     * @return
     */
    boolean create(PmsProductParam productParam);

    /**
     * 根据商品id获取商品编辑信息
     * @param id
     * @return
     */
    PmsProductResult getUpdateInfo(Long id);
}
