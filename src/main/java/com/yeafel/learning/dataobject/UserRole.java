package com.yeafel.learning.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by kangyifan on 2018/11/5 14:14
 */
@Data
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    /** 用户id */
    private Long userId;

    /** 角色id */
    private Long roleId;



}
