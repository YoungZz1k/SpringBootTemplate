package com.YoungZz1k.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
@Getter
@Setter
public class BizException extends RuntimeException{


    private Integer errorCode;

    private String errorMsg;

    public BizException(BaseExceptionInterface baseExceptionInterface){
        this.errorCode =  baseExceptionInterface.getErrorCode();
        this.errorMsg = baseExceptionInterface.getErrorMsg();

    }
}
