package com.zben.mall.controller.pms;

import com.zben.mall.common.api.CommonResult;
import com.zben.mall.dto.PmsProductAttributeCategoryItem;
import com.zben.mall.dto.ProductAttrInfo;
import com.zben.mall.service.pms.PmsProductAttributeCategoryService;
import com.zben.mall.service.pms.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESC:商品属性分类管理
 * @author: zhouben
 * @date: 2020/6/5 0005 14:07
 */
@RestController
@Api(tags = "PmsProductAttributeCategoryController", description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    PmsProductAttributeCategoryService productAttributeCategoryService;

    @ApiOperation("获取所有商品属性分类及其下属性")
    @GetMapping("/list/withAttr")
    public CommonResult<List<PmsProductAttributeCategoryItem>> getListWithAttr() {
        List<PmsProductAttributeCategoryItem> productAttributeCategoryResultList = productAttributeCategoryService.getListWithAttr();
        return CommonResult.success(productAttributeCategoryResultList);
    }
}
