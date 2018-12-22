package com.yeafel.learning.enums;

import lombok.Getter;

/**
 * Created by kangyifan on 2018/9/17 15:11
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    LOGIN_FAIL(10,"登录失败,登录信息不正确"),

    USER_NOT_EXIST(11,"用户不存在"),

    PASSWORD_ERROR(12,"密码错误"),

    ROLE_REJECT(13,"你不拥有此角色"),

    ROLE_NOT_EXIST(14,"角色不存在"),

    UPDATE_ERROR(15,"更新失败"),

    CREATE_ERROR(16,"创建失败"),

    COURSE_NOT_EXIST(17,"课程不存在"),

    ACTIONROLE_NOT_EXIST(18,"角色权限不足"),

    COURSEWARE_NOT_EXIST(19,"课件不存在"),

    RECORD_NOT_EXIST(20,"记录不存在"),

    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
