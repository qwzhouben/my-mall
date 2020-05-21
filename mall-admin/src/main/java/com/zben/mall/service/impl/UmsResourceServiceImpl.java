package com.zben.mall.service.impl;

import com.zben.mall.mapper.UmsResourceMapper;
import com.zben.mall.model.UmsResource;
import com.zben.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC:UmsResourceService实现类
 * @author: zhouben
 * @date: 2020/5/21 0021 15:05
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    UmsResourceMapper resourceMapper;

    /**
     * 查询全部资源
     * @return
     */
    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectAll();
    }
}
