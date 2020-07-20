package com.zben.mall.controller.cms;

import com.zben.mall.common.api.CommonResult;
import com.zben.mall.model.cms.CmsSubject;
import com.zben.mall.service.cms.CmsSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @DESC:商品专题
 * @author: zhouben
 * @date: 2020/6/13 0013 16:58
 */
@RestController
@RequestMapping("/subject")
@Api(tags = "CmsSubjectController", description = "商品专题")
public class CmsSubjectController {

    @Autowired
    CmsSubjectService subjectService;

    @ApiOperation("获取全部商品专题")
    @GetMapping("/listAll")
    public CommonResult<List<CmsSubject>> listAll() {
        List<CmsSubject> subjectList = subjectService.listAll();
        return CommonResult.success(subjectList);
    }

}
