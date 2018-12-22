package com.yeafel.learning.handler;

import com.yeafel.learning.exception.LearningException;
import com.yeafel.learning.utils.ResultVOUtil;
import com.yeafel.learning.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  异常处理
 * Created by kangyifan on 2018/9/20 1:27
 */
@ControllerAdvice
public class LearningExceptionHandler {

    @ExceptionHandler(value = LearningException.class)
    @ResponseBody
    public ResultVO handlerEvaluation(LearningException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
