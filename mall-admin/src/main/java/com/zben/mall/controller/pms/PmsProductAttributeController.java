package com.zben.mall.controller.pms;

import com.zben.mall.common.api.CommonPage;
import com.zben.mall.common.api.CommonResult;
import com.zben.mall.dto.PmsProductAttributeParam;
import com.zben.mall.dto.ProductAttrInfo;
import com.zben.mall.model.pms.PmsProductAttribute;
import com.zben.mall.service.pms.PmsProductAttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("根据属性分类查询属性列表或参数列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "0表示属性，1表示参数", required = true, paramType = "query", dataType = "integer")})
    @GetMapping( "/list/{cid}")
    public CommonResult<CommonPage<PmsProductAttribute>> getList(@PathVariable Long cid,
                                                                 @RequestParam(value = "type") Integer type,
                                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductAttribute> productAttributeList = productAttributeService.getList(cid, type, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productAttributeList));
    }

    @ApiOperation("添加商品属性信息")
    @PostMapping("/create")
    public CommonResult create(@RequestBody PmsProductAttributeParam productAttributeParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CommonResult.failed(bindingResult.getFieldError().getDefaultMessage());
        }
        if (productAttributeService.create(productAttributeParam)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("查询单个商品属性")
    @GetMapping("/{id}")
    public CommonResult<PmsProductAttribute> getItem(@PathVariable Long id) {
        PmsProductAttribute productAttribute = productAttributeService.getItem(id);
        return CommonResult.success(productAttribute);
    }

    @ApiOperation("修改商品属性信息")
    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody PmsProductAttributeParam productAttributeParam, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CommonResult.failed(bindingResult.getFieldError().getDefaultMessage());
        }
        if (productAttributeService.update(id, productAttributeParam)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除商品属性")
    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        if (productAttributeService.delete(ids)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

}
