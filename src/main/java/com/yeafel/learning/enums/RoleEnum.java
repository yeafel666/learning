package com.yeafel.learning.enums;

import lombok.Getter;

/**
 * Created by kangyifan on 2018/9/14 10:44
 * 角色枚举，这里注意严格与角色枚举区分开来，两者的内容或许不一样
 */
@Getter
public enum RoleEnum implements  CodeEnum<Integer>{

    STUDENT(1,"学生"),
    TEACHER(2,"老师"),
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
