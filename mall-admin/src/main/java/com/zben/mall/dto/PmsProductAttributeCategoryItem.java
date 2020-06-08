package com.zben.mall.dto;

import com.zben.mall.model.pms.PmsProductAttribute;
import com.zben.mall.model.pms.PmsProductAttributeCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 包含有分类下属性的dto
 * Created by macro on 2018/5/24.
 */
@Setter
@Getter
public class PmsProductAttributeCategoryItem {

    private Long id;

    private String name;

    private List<PmsProductAttribute> productAttributeList;

}
