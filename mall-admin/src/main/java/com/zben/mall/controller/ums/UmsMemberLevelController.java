package com.zben.mall.controller.ums;

import com.zben.mall.common.api.CommonResult;
import com.zben.mall.model.ums.UmsMemberLevel;
import com.zben.mall.service.ums.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESC:会员管理
 * @author: zhouben
 * @date: 2020/6/14 0014 14:44
 */
@RestController
@RequestMapping("/memberLevel")
@Api(tags = "UmsMemberLevelController", description = "会员管理")
public class UmsMemberLevelController {

    @Autowired
    UmsMemberLevelService memberLevelService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    @ResponseBody
    public CommonResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return CommonResult.success(memberLevelList);
    }
}
