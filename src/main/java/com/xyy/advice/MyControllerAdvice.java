package com.xyy.advice;

import com.xyy.utils.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object handlerException(Exception e){
        //获取异常信息，存放如ResponseResult的msg属性
        String message = e.getMessage();
        //把ResponseResult作为返回值返回，要求到时候转换成json存入响应体中
        return ResponseResult.error(message);
    }
    
    @ExceptionHandler()
    @ResponseBody
    // 处理默认处理异常方法
    public Object handlerDefaultException(Exception e){
        return ResponseResult.error("出现未知错误");
    }
}