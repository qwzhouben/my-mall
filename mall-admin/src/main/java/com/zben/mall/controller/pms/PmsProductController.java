package com.zben.mall.controller.pms;

import com.zben.mall.common.api.CommonPage;
import com.zben.mall.common.api.CommonResult;
import com.zben.mall.dto.PmsProductQueryParam;
import com.zben.mall.model.pms.PmsProduct;
import com.zben.mall.service.pms.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/6/4 0004 21:00
 */
@RestController
@Api(tags = "PmsProductController", description = "商品")
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    PmsProductService productService;

    @ApiOperation("查询商品")
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsProduct>> getList(PmsProductQueryParam productQueryParam,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProduct> productList = productService.list(productQueryParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productList));
    }
}
