package com.zben.mall.service.pms;

import com.zben.mall.dto.PmsBrandParam;
import com.zben.mall.model.brand.PmsBrand;

import java.util.List;

/**
 * @DESC:品牌Service
 * @AUTHOR: zhouben
 * @DATE: 2020/6/4 0004 20:24
 */
public interface PmsBrandService {

    /**
     * 查询所有品牌
     * @return
     */
    List<PmsBrand> listAll();

    /**
     * 根据品牌名称分页获取品牌列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsBrand> listBrand(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 添加品牌
     * @param pmsBrand
     * @return
     */
    boolean createBrand(PmsBrandParam pmsBrand);

    /**
     * 批量更新显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    boolean updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 批量更新厂家制造商状态
     * @param ids
     * @param factoryStatus
     * @return
     */
    boolean updateFactoryStatus(List<Long> ids, Integer factoryStatus);

    /**
     * 更新品牌
     * @param id
     * @param pmsBrandParam
     * @return
     */
    boolean updateBrand(Long id, PmsBrandParam pmsBrandParam);

    /**
     * 根据编号查询品牌信息
     * @param id
     * @return
     */
    PmsBrand getBrand(Long id);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    boolean deleteBrand(Long id);
}
