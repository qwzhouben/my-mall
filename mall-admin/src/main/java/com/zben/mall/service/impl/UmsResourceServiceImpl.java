package com.zben.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.mapper.UmsResourceMapper;
import com.zben.mall.model.UmsResource;
import com.zben.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @DESC:ResourceService实现
 * @author: zhouben
 * @date: 2020/5/28 0028 18:02
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

    /**
     * 分页模糊查询后台资源
     * @param categoryId
     * @param nameKeyword
     * @param urlKeyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(UmsResource.class);
        Example.Criteria criteria = example.createCriteria();
        if (categoryId != null) {
            criteria.andEqualTo("categoryId", categoryId);
        }
        if (!StringUtils.isEmpty(nameKeyword)) {
            criteria.andLike("name", "%" + nameKeyword + "%");
        }
        if (!StringUtils.isEmpty(urlKeyword)) {
            criteria.andLike("url", "%" + urlKeyword + "%");
        }
        return resourceMapper.selectByExample(example);
    }

    /**
     * 添加后台资源
     * @param umsResource
     * @return
     */
    @Override
    public boolean create(UmsResource umsResource) {
        return resourceMapper.insertSelective(umsResource) > 0;
    }

    /**
     * 修改后台资源
     * @param id
     * @param umsResource
     * @return
     */
    @Override
    public boolean update(Long id, UmsResource umsResource) {
        return resourceMapper.updateByPrimaryKeySelective(umsResource) > 0;
    }

    /**
     * 根据ID删除后台资源
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        return resourceMapper.deleteByPrimaryKey(id) > 0;
    }
}
