package com.zben.mall.service.cms.impl;

import com.zben.mall.mapper.cms.CmsSubjectMapper;
import com.zben.mall.model.cms.CmsSubject;
import com.zben.mall.service.cms.CmsSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/6/13 0013 17:01
 */
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {

    @Autowired
    CmsSubjectMapper subjectMapper;

    /**
     * 获取全部商品专题
     * @return
     */
    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectAll();
    }
}
