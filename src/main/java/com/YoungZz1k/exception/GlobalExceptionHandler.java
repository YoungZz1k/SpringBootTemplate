package com.YoungZz1k.exception;

import com.YoungZz1k.util.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

/**
 * 自定义异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler  {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValid(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors ->{
            errors.forEach(error ->
                    sb.append(error.getField())
                            .append(" ")
                            .append(error.getDefaultMessage())
                            .append(", 当前值: '")
                            .append(error.getRejectedValue())
                            .append("';")
            );
        });
        return  Result.fail(500,sb.toString());
    }

    @ExceptionHandler(BizException.class)
    public Result BizException(BizException e){
        return Result.fail(e.getErrorCode(),e.getErrorMsg());
    }
}
