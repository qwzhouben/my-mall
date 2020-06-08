package com.zben.mall.model.pms;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by macro on 2018/5/25.
 */
@Getter
@Setter
@ToString(callSuper = true)
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {

    private List<PmsProductCategoryWithChildrenItem> children;

}
