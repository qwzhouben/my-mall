package com.zben.mall.service.cms.impl;

import com.zben.mall.mapper.cms.CmsPrefrenceAreaMapper;
import com.zben.mall.model.cms.CmsPrefrenceArea;
import com.zben.mall.service.cms.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/6/14 0014 14:39
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {

    @Autowired
    CmsPrefrenceAreaMapper prefrenceAreaMapper;

    /**
     * 获取所有商品优选
     * @return
     */
    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectAll();
    }
}
