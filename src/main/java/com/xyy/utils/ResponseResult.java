package com.xyy.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// 指定转换为json时不转换值为null的属性
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    // 成功的返回状态码1
    private static Integer CODE_SUCCESS = 1;
    // 失败的返回状态码0
    private static Integer CODE_ERROR = 0;
    // 成功的默认返回信息
    private static String DEFAULT_SUCCESS_MSG = "成功";
    // 失败的默认返回信息
    private static String DEFAULT_ERROR_MSG = "失败";

    /**
     * code     返回的状态码
     * data     返回的数据对象
     * msg      返回的字符串
     */
    private Integer code;
    private T data;
    private String msg;

    private ResponseResult() {
    }

    // 默认的成功返回
    public static ResponseResult<Object> success() {
        return new ResponseResult<>(CODE_SUCCESS, null, DEFAULT_SUCCESS_MSG);
    }

    // 指定返回数据data的成功返回 static方法要用泛型只能用泛型方法
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(CODE_SUCCESS, data, DEFAULT_SUCCESS_MSG);
    }
    
	// 指定返回字符串msg的成功返回
    public static  ResponseResult<Object> success(String msg) {
        return new ResponseResult<>(CODE_SUCCESS, null, msg);
    }

    //指定返回数据data和字符串msg的成功返回
    public static <T> ResponseResult<T> success(T data, String msg) {
        return new ResponseResult<>(CODE_SUCCESS, data, msg);
    }


    // 默认的失败返回
    public static ResponseResult<Object> error() {
        return new ResponseResult<>(CODE_ERROR,null,DEFAULT_ERROR_MSG);
    }

    // 指定返回数据data的失败返回
    public static <T> ResponseResult<T> error(T data){
        return new ResponseResult<>(CODE_ERROR,data,DEFAULT_ERROR_MSG);
    }

    // 指定返回字符串msg的失败返回
    public static  ResponseResult<Object> error(String msg){
        return new ResponseResult<>(CODE_ERROR,null,msg);
    }

    //指定返回数据data和字符串msg的失败返回
    public static <T> ResponseResult<T> error(T data, String msg) {
        return new ResponseResult<>(CODE_ERROR, data, msg);
    }
}

