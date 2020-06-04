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

    /**
     * 分页模糊查询后台资源
     * @param categoryId
     * @param nameKeyword
     * @param urlKeyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 添加后台资源
     * @param umsResource
     * @return
     */
    boolean create(UmsResource umsResource);

    /**
     * 修改后台资源
     * @param id
     * @param umsResource
     * @return
     */
    boolean update(Long id, UmsResource umsResource);

    /**
     * 根据ID删除后台资源
     * @param id
     * @return
     */
    boolean delete(Long id);
}
