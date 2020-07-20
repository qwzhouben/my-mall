package com.zben.mall.controller.cms;

import com.zben.mall.common.api.CommonResult;
import com.zben.mall.model.cms.CmsPrefrenceArea;
import com.zben.mall.service.cms.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESC:商品优选管理
 * @author: zhouben
 * @date: 2020/6/13 0013 17:05
 */
@RestController
@RequestMapping("/prefrenceArea")
@Api(tags = "CmsPreferenceAreaController", description = "商品优选管理")
public class CmsPreferenceAreaController {

    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation("获取所有商品优选")
    @GetMapping("/listAll")
    public CommonResult<List<CmsPrefrenceArea>> listAll() {
        List<CmsPrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
        return CommonResult.success(prefrenceAreaList);
    }

}
