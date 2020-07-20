package com.zben.mall.service.ums;

import com.zben.mall.model.ums.UmsMemberLevel;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/14 0014 14:46
 */
public interface UmsMemberLevelService {

    /**
     * 查询所有会员等级
     * @param defaultStatus
     * @return
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
