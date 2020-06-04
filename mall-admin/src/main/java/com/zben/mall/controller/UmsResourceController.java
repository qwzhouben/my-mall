package com.zben.mall.controller;

import com.zben.mall.common.api.CommonPage;
import com.zben.mall.common.api.CommonResult;
import com.zben.mall.model.UmsResource;
import com.zben.mall.security.component.DynamicSecurityMetadataSource;
import com.zben.mall.service.UmsResourceService;
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
 * @date: 2020/5/28 0028 17:42
 */
@RestController
@RequestMapping("/resource")
@Api(tags = "UmsResourceController", description = "后台资源管理")
public class UmsResourceController {

    @Autowired
    UmsResourceService resourceService;
    @Autowired
    DynamicSecurityMetadataSource dynamicSecurityMetadataSource;


    @ApiOperation("分页模糊查询后台资源")
    @GetMapping("/list")
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResource> list = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation("添加后台资源")
    @PostMapping("/create")
    public CommonResult create(@RequestBody @Validated UmsResource umsResource, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.failed(result.getFieldError().getDefaultMessage());
        }
        if (resourceService.create(umsResource)) {
            dynamicSecurityMetadataSource.clearDataSource();
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改后台资源")
    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResource umsResource) {
        if (resourceService.update(id, umsResource)) {
            dynamicSecurityMetadataSource.clearDataSource();
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation("根据ID删除后台资源")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        if (resourceService.delete(id)) {
            dynamicSecurityMetadataSource.clearDataSource();
            return CommonResult.success();
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation("查询所有后台资源")
    @GetMapping("/listAll")
    public CommonResult<List<UmsResource>> listAll() {
        List<UmsResource> resourceList = resourceService.listAll();
        return CommonResult.success(resourceList);
    }
}
