package com.xpcf.tmall.utils;

import lombok.Data;
import sun.plugin2.os.windows.FLASHWINFO;

/**
 * @program: tmall_springboot
 * @description:
 * @author: "xpcf"
 * @create: 2020-04-02 20:07
 **/
@Data
public class Result {
    public static Integer SUCCESS_CODE=0;
    public static Integer FAIL_CODE = 1;

    Integer code;
    String message;
    Object data;

    private Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(){
        return new Result(SUCCESS_CODE,null,null);
    }

    public static Result success(Object data){
        return new Result(SUCCESS_CODE,null,data);
    }

    public static Result fail(String message){
        return new Result(FAIL_CODE,message,null);
    }
}
