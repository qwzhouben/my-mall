package com.zben.mall.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @DESC:动态权限相关业务类
 * @AUTHOR: zhouben
 * @DATE: 2020/5/21 0021 11:41
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应WAP
     * @return
     */
    Map<String, ConfigAttribute> loadDataSource();
}
