package com.zben.mall.controller.pms;

import com.zben.mall.common.api.CommonPage;
import com.zben.mall.common.api.CommonResult;
import com.zben.mall.dto.PmsProductCategoryParam;
import com.zben.mall.model.pms.PmsProductCategory;
import com.zben.mall.model.pms.PmsProductCategoryWithChildrenItem;
import com.zben.mall.service.pms.PmsProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/6/4 0004 20:35
 */
@RestController
@Api(tags = "PmsProductCategoryController", description = "商品分类管理")
@RequestMapping("/productCategory")
public class PmsProductCategoryController {

    @Autowired
    PmsProductCategoryService productCategoryService;

    @ApiOperation("查询所有一级分类及子分类")
    @GetMapping("/list/withChildren")
    public CommonResult<List<PmsProductCategoryWithChildrenItem>> listWithChildren() {
        List<PmsProductCategoryWithChildrenItem> list = productCategoryService.listWithChildren();
        return CommonResult.success(list);
    }

    @ApiOperation("分页查询商品分类")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<PmsProductCategory>> getList(@PathVariable Long parentId,
                                                                @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<PmsProductCategory> productCategoryList = productCategoryService.getList(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(productCategoryList));
    }

    @ApiOperation("添加产品分类")
    @PostMapping("/create")
    public CommonResult create(@Validated @RequestBody PmsProductCategoryParam productCategoryParam,
                               BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.failed(result.getFieldError().getDefaultMessage());
        }
        if (productCategoryService.create(productCategoryParam)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改导航栏显示状态")
    @PostMapping("/update/navStatus")
    public CommonResult updateNavStatus(@RequestParam("ids") List<Long> ids, @RequestParam("navStatus") Integer navStatus) {
        if (productCategoryService.updateNavStatus(ids, navStatus)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改显示状态")
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids, @RequestParam("showStatus") Integer showStatus) {
        if (productCategoryService.updateShowStatus(ids, showStatus)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据id获取商品分类")
    @GetMapping("/{id}")
    public CommonResult<PmsProductCategory> getItem(@PathVariable Long id) {
        PmsProductCategory productCategory = productCategoryService.getItem(id);
        return CommonResult.success(productCategory);
    }

    @ApiOperation("修改商品分类")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable Long id,
                               @Validated
                               @RequestBody PmsProductCategoryParam productCategoryParam,
                               BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.failed(result.getFieldError().getDefaultMessage());
        }
        if (productCategoryService.update(id, productCategoryParam)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("删除商品分类")
    @PostMapping("/delete/{id}")
    public CommonResult delete(@PathVariable Long id) {
        if (productCategoryService.delete(id)) {
            return CommonResult.success();
        }
        return CommonResult.failed();

    }
}
