package com.sxau.master.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 * @BelongsProject: ssm-master
 * @BelongsPackage: com.sxau.common
 * @CreateTime: 2019-09-01 11:24
 * @Description:
 */
@ControllerAdvice
//实现全局异常处理
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);



    /**
     * 功能描述: <校验异常统一处理>
     * @Param: [e]
     * @Return: com.sxau.master.common.Result
     * @Author: Shijie Chang
     * @Date: 2020/09/03 19:26
     */
    @ExceptionHandler(ConstraintViolationException.class)
    //统一处理方法抛出的异常
    @ResponseBody
    public Result handleValidationException(ConstraintViolationException e){
        LOGGER.error(e.getCause().getMessage());
        Result result  = new Result();
        result.setErrorCode(ResultEnum.PARAM_ERROR.getErrorCode());
        for(ConstraintViolation<?> s:e.getConstraintViolations()){
            result.setErrorMsg( s.getInvalidValue()+": "+s.getMessage());
        }
        return result;
    }

    /**
     * 功能描述: <自定义异常统一处理>
     * @Param: [e]
     * @Return: com.sxau.master.common.Result
     * @Author: Shijie Chang
     * @Date: 2020/09/03 19:30
     */
    @ExceptionHandler(value = ErrorException.class)
    @ResponseBody
    public Result dealErrorCodeException(ErrorException e) throws Exception{
        LOGGER.error(e.getMessage());
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorCode(e.getCode());
        result.setErrorMsg(e.getMessage());
        return result;
    }

    /**
     * 功能描述: <其他异常统一处理>
     * @Param: [e]
     * @Return: com.sxau.master.common.Result
     * @Author: Shijie Chang
     * @Date: 2020/09/03 19:30
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result dealException(Exception e) throws Exception{
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorCode(ResultEnum.SYSTEM_ERROR.getErrorCode());
        result.setErrorMsg(ResultEnum.SYSTEM_ERROR.getErrorMsg());
        e.printStackTrace();
        return result;
    }
}
