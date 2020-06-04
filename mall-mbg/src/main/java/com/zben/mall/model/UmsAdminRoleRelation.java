package com.zben.mall.model;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;

@Data
public class UmsAdminRoleRelation implements Serializable {

    @Id
    private Long id;

    private Long adminId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

}