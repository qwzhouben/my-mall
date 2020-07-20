package com.zben.mall.service.cms;

import com.zben.mall.model.cms.CmsPrefrenceArea;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/14 0014 14:39
 */
public interface CmsPrefrenceAreaService {

    /**
     * 获取所有商品优选
     * @return
     */
    List<CmsPrefrenceArea> listAll();
}
