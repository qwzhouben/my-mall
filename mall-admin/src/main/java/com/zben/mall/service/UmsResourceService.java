package com.zben.mall.service;

import com.zben.mall.model.UmsResource;

import java.util.List;

/**
 * @DESC:后台资源管理器Service
 * @AUTHOR: zhouben
 * @DATE: 2020/5/21 0021 15:04
 */
public interface UmsResourceService {

    /**
     * 查询全部资源
     * @return
     */
    List<UmsResource> listAll();
}
