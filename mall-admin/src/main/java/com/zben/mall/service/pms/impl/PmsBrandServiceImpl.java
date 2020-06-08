package com.zben.mall.service.pms.impl;

import com.github.pagehelper.PageHelper;
import com.zben.mall.dto.PmsBrandParam;
import com.zben.mall.mapper.pms.PmsBrandMapper;
import com.zben.mall.model.brand.PmsBrand;
import com.zben.mall.service.pms.PmsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @DESC:品牌Service实现
 * @author: zhouben
 * @date: 2020/6/4 0004 20:24
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    PmsBrandMapper brandMapper;

    /**
     * 查询所有品牌
     * @return
     */
    @Override
    public List<PmsBrand> listAll() {
        return brandMapper.selectAll();
    }

    /**
     * 根据品牌名称分页获取品牌列表
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<PmsBrand> listBrand(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(PmsBrand.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andLike("name", "%" + keyword + "%");
        }
        example.orderBy("sort").desc();
        return  brandMapper.selectByExample(example);
    }

    /**
     * 添加品牌
     * @param pmsBrandParam
     * @return
     */
    @Override
    public boolean createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        //如果创建时首字母为空， 取名称的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return brandMapper.insertSelective(pmsBrand) > 0;
    }

    /**
     * 批量更新显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    @Override
    @Transactional
    public boolean updateShowStatus(List<Long> ids, Integer showStatus) {
        return brandMapper.updateShowStatus(ids, showStatus) > 0;
    }

    /**
     * 批量更新厂家制造商状态
     * @param ids
     * @param factoryStatus
     * @return
     */
    @Override
    @Transactional
    public boolean updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        return brandMapper.updateFactoryStatus(ids, factoryStatus) > 0;
    }

    /**
     * 更新品牌
     * @param id
     * @param pmsBrandParam
     * @return
     */
    @Override
    @Transactional
    public boolean updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名字的第一个为首字母
        if (StringUtils.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //todo: 更新品牌时更新商品的品牌名称
        brandMapper.updateByPrimaryKeySelective(pmsBrand);
        return Boolean.TRUE;
    }

    /**
     * 根据编号查询品牌信息
     * @param id
     * @return
     */
    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除品牌
     * @param id
     * @return
     */
    @Override
    public boolean deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id) > 0;
    }
}






















