package com.zben.mall.service.pms.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.dto.PmsProductQueryParam;
import com.zben.mall.mapper.pms.PmsProductMapper;
import com.zben.mall.model.pms.PmsProduct;
import com.zben.mall.service.pms.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/6/4 0004 21:04
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {

    @Autowired
    PmsProductMapper pmsProductMapper;

    /**
     * 查询商品
     * @param productQueryParam
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(PmsProduct.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deleteStatus", 0);
        if (productQueryParam.getPublishStatus() != null) {
            criteria.andEqualTo("publishStatus", productQueryParam.getPublishStatus());
        }
        if (productQueryParam.getVerifyStatus() != null) {
            criteria.andEqualTo("verifyStatus", productQueryParam.getVerifyStatus());
        }
        if (!StringUtils.isEmpty(productQueryParam.getKeyword())) {
            criteria.andLike("name", "%" + productQueryParam.getKeyword() + "%");
        }
        if (!StringUtils.isEmpty(productQueryParam.getProductSn())) {
            criteria.andEqualTo("productSn", productQueryParam.getProductSn());
        }
        if (productQueryParam.getBrandId() != null) {
            criteria.andEqualTo("brandId", productQueryParam.getBrandId());
        }
        if (productQueryParam.getProductCategoryId() != null) {
            criteria.andEqualTo("productCategoryId", productQueryParam.getProductCategoryId());
        }
        return pmsProductMapper.selectByExample(example);
    }
}
