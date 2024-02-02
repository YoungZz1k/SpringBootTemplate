package com.YoungZz1k.enums;

import com.YoungZz1k.exception.BaseExceptionInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义返回状态码
 */
@Getter
@AllArgsConstructor
public enum ReturnCodeEnum implements BaseExceptionInterface {


    ILLEGAL_ARGUMENT_ERROR(1000,"非法参数错误");


    private Integer errorCode;

    private String errorMsg;


}
