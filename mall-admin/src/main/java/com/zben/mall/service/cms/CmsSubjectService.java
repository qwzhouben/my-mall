package com.zben.mall.service.cms;

import com.zben.mall.model.cms.CmsSubject;

import java.util.List;

/**
 * @DESC:
 * @AUTHOR: zhouben
 * @DATE: 2020/6/13 0013 17:01
 */
public interface CmsSubjectService {

    /**
     * 获取全部商品专题
     * @return
     */
    List<CmsSubject> listAll();

}
