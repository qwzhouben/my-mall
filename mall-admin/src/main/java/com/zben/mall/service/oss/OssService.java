package com.zben.mall.service.oss;

import com.zben.mall.dto.OssCallbackResult;
import com.zben.mall.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @DESC:OSS上传
 * @AUTHOR: zhouben
 * @DATE: 2020/6/4 0004 21:39
 */
public interface OssService {

    /**
     * oss上传策略生成
     * @return
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);
}
