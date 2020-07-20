package com.zben.mall.service.ums.impl;

import com.zben.mall.mapper.ums.UmsMemberLevelMapper;
import com.zben.mall.model.ums.UmsMemberLevel;
import com.zben.mall.service.ums.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @DESC:
 * @author: zhouben
 * @date: 2020/6/14 0014 14:46
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {


    @Autowired
    UmsMemberLevelMapper memberLevelMapper;

    /**
     * 查询所有会员等级
     * @param defaultStatus
     * @return
     */
    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        Example example = new Example(UmsMemberLevel.class);
        example.createCriteria().andEqualTo("defaultStatus", defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
