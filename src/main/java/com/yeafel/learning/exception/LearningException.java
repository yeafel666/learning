package com.yeafel.learning.exception;

import com.yeafel.learning.enums.ResultEnum;
import lombok.Getter;

/**
 * Created by kangyifan on 2018/11/5 14:57
 */
@Getter
public class LearningException extends RuntimeException {

    private Integer code;

    public LearningException(ResultEnum resultEnum){
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }


    public LearningException(Integer code,String message){
        super(message);
        this.code = code;
    }
}
