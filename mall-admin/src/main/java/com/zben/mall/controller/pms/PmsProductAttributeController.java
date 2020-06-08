package com.zben.mall.controller.pms;

import com.zben.mall.common.api.CommonResult;
import com.zben.mall.dto.ProductAttrInfo;
import com.zben.mall.service.pms.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @DESC:商品属性
 * @author: zhouben
 * @date: 2020/6/5 0005 14:05
 */
@RestController
@Api(tags = "PmsProductAttributeController", description = "商品属性")
@RequestMapping("/productAttribute")
public class PmsProductAttributeController {

    @Autowired
    PmsProductAttributeService productAttributeService;

    @ApiOperation("根据商品分类的id获取商品属性及属性分类")
    @GetMapping("/attrInfo/{productCategoryId}")
    public CommonResult<List<ProductAttrInfo>> getAttrInfo(@PathVariable Long productCategoryId) {
        List<ProductAttrInfo> productAttrInfoList = productAttributeService.getProductAttrInfo(productCategoryId);
        return CommonResult.success(productAttrInfoList);
    }

}
