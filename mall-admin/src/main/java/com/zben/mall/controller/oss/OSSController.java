package com.zben.mall.controller.oss;

import com.zben.mall.common.api.CommonResult;
import com.zben.mall.dto.OssCallbackResult;
import com.zben.mall.dto.OssPolicyResult;
import com.zben.mall.service.oss.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @DESC:OSS相关操作
 * @author: zhouben
 * @date: 2020/6/4 0004 21:36
 */
@RestController
@Api(tags = "OSSController", description = "OSS管理")
@RequestMapping("/aliyun/oss")
public class OSSController {

    @Autowired
    OssService ossService;

    @ApiOperation(value = "oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    public CommonResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return CommonResult.success(result);
    }

    @ApiOperation(value = "oss上传成功回调")
    @PostMapping("/callback")
    public CommonResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return CommonResult.success(ossCallbackResult);
    }
}



























