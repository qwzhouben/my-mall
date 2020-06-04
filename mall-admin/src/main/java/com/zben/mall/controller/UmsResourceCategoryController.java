package com.zben.mall.controller;

import com.zben.mall.common.api.CommonResult;
import com.zben.mall.model.UmsResourceCategory;
import com.zben.mall.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/5/27 0027 15:56
 */
@RestController
@RequestMapping("/resourceCategory")
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
public class UmsResourceCategoryController {

    @Autowired
    UmsResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResourceCategory>> listAll() {
        List<UmsResourceCategory> categories = resourceCategoryService.listAll();
        return CommonResult.success(categories);
    }

    @ApiOperation("添加后台资源分类")
    @PostMapping("/create")
    public CommonResult create(@RequestBody UmsResourceCategory resourceCategory) {
        if (resourceCategoryService.create(resourceCategory)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改后台资源分类")
    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResourceCategory umsResourceCategory) {
        if (resourceCategoryService.update(id, umsResourceCategory)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        if (resourceCategoryService.delete(id)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed();
        }
    }
}
