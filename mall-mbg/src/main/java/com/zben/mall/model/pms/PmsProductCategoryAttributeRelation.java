package com.zben.mall.model.pms;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class PmsProductCategoryAttributeRelation implements Serializable {

    @Id
    private Long id;

    private Long productCategoryId;

    private Long productAttributeId;

    private static final long serialVersionUID = 1L;


}