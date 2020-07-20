package com.zben.mall.service.pms.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.dto.PmsProductParam;
import com.zben.mall.dto.PmsProductQueryParam;
import com.zben.mall.dto.PmsProductResult;
import com.zben.mall.mapper.*;
import com.zben.mall.mapper.CmsPrefrenceAreaProductRelationMapper;
import com.zben.mall.mapper.CmsSubjectProductRelationMapper;
import com.zben.mall.mapper.pms.*;
import com.zben.mall.model.pms.PmsProduct;
import com.zben.mall.model.pms.PmsSkuStock;
import com.zben.mall.service.pms.PmsProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/6/4 0004 21:04
 */
@Service
@Slf4j
public class PmsProductServiceImpl implements PmsProductService {

    @Autowired
    PmsProductMapper pmsProductMapper;
    @Autowired
    PmsMemberPriceMapper memberPriceMapper;
    @Autowired
    PmsProductLadderMapper productLadderMapper;
    @Autowired
    PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    PmsSkuStockMapper skuStockMapper;
    @Autowired
    PmsProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    CmsSubjectProductRelationMapper subjectProductRelationMapper;
    @Autowired
    CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;

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

    /**
     * 创建商品
     * @param productParam
     * @return
     */
    @Override
    @Transactional
    public boolean create(PmsProductParam productParam) {
        //创建商品
        PmsProduct product = new PmsProduct();
        BeanUtils.copyProperties(productParam, product);
        pmsProductMapper.insertSelective(product);
        //根据促销类型设置价格：会员价格、阶梯价格、满减价格
        Long productId = product.getId();
        //会员价格
        relateAndInsertList(memberPriceMapper, productParam.getMemberPriceList(), productId);
        //阶梯价格
        relateAndInsertList(productLadderMapper, productParam.getProductLadderList(), productId);
        //满减价格
        relateAndInsertList(productFullReductionMapper, productParam.getProductFullReductionList(), productId);
        //处理sku的编码
        handleSkuStoreCode(productParam.getSkuStockList(), productId);
        //添加sku库存信息
        relateAndInsertList(skuStockMapper, productParam.getSkuStockList(), productId);
        //添加商品参数，添加自定义商品价格
        relateAndInsertList(productAttributeValueMapper, productParam.getProductAttributeValueList(), productId);
        //关联专题
        relateAndInsertList(subjectProductRelationMapper, productParam.getSubjectProductRelationList(), productId);
        //关联优选
        relateAndInsertList(prefrenceAreaProductRelationMapper, productParam.getPrefrenceAreaProductRelationList(), productId);
        return Boolean.TRUE;
    }

    /**
     * 根据商品id获取商品编辑信息
     * @param id
     * @return
     */
    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return pmsProductMapper.getUpdateInfo(id);
    }

    private void handleSkuStoreCode(List<PmsSkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) {
            return;
        }
        for (int i = 0; i < skuStockList.size(); i++) {
            PmsSkuStock pmsSkuStock = skuStockList.get(i);
            if (StringUtils.isEmpty(pmsSkuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                //日期
                sb.append(sdf.format(new Date()));
                //四位商品id
                sb.append(String.format("%04d", productId));
                //三位索引id
                sb.append(String.format("%03d", i+1));
                pmsSkuStock.setSkuCode(sb.toString());
            }
        }

    }

    /**
     * 建立和插入关系表操作
     * @param dao        可以操作的mapper
     * @param dataList      要插入的数据
     * @param productId     建立关系的id
     */
    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) {
                return;
            }
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            log.warn("创建产品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
