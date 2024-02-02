package com.YoungZz1k.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 自定义通用返回结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public Result(Integer code, Object data){
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Result(String msg, Object data){
        this.msg = msg;
        this.data = data;
    }

    public Result(Object data){
        this.code = 200;
        this.data = data;
        this.msg = "success";
    }

    public Result(Object data, String msg){
        this.code = 200;
        this.msg = msg;
    }

    public static Result success(Integer code, Object data){
        return new Result(code,data);
    }

    public static Result success(Object data, String msg){
        return new Result(msg,data);
    }

    public static Result success(Object data){
        return new Result(data);
    }


    public static Result fail(Integer code, String msg){
        return new Result(code,msg);
    }


}
