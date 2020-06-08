package com.zben.mall.controller.pms;

import com.zben.mall.common.api.CommonPage;
import com.zben.mall.common.api.CommonResult;
import com.zben.mall.dto.PmsBrandParam;
import com.zben.mall.model.brand.PmsBrand;
import com.zben.mall.service.pms.PmsBrandService;
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
 * @date: 2020/6/4 0004 20:20
 */
@RestController
@Api(tags = "PmsBrandController", description = "品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    PmsBrandService brandService;

    @ApiOperation(value = "获取全部品牌列表")
    @GetMapping("/listAll")
    public CommonResult<List<PmsBrand>> listAll() {
        return CommonResult.success(brandService.listAll());
    }

    @ApiOperation(value = "根据品牌名称分页获取品牌列表")
    @GetMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<PmsBrand> brandList = brandService.listBrand(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation(value = "添加品牌")
    @PostMapping("/create")
    public CommonResult create(@Validated @RequestBody PmsBrandParam pmsBrand, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.failed(result.getFieldError().getDefaultMessage());
        }
        if (brandService.createBrand(pmsBrand)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "批量更新显示状态")
    @PostMapping("/update/showStatus")
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,
                                         @RequestParam("showStatus") Integer showStatus) {
        if (brandService.updateShowStatus(ids, showStatus)) {
            return CommonResult.success();
        }
        return CommonResult.failed();

    }

    @ApiOperation(value = "批量更新厂家制造商状态")
    @PostMapping("/update/factoryStatus")
    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus) {
        if (brandService.updateFactoryStatus(ids, factoryStatus)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "更新品牌")
    @PostMapping("/update/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @Validated @RequestBody PmsBrandParam pmsBrandParam, BindingResult result) {
        if (result.hasErrors()) {
            return CommonResult.failed(result.getFieldError().getDefaultMessage());
        }
        if (brandService.updateBrand(id, pmsBrandParam)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "根据编号查询品牌信息")
    @RequestMapping("/{id}")
    public CommonResult<PmsBrand> getItem(@PathVariable("id") Long id) {
        return CommonResult.success(brandService.getBrand(id));
    }

    @ApiOperation(value = "删除品牌")
    @GetMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") Long id) {
        if (brandService.deleteBrand(id)) {
            return CommonResult.success();
        }
        return CommonResult.failed();
    }
}
