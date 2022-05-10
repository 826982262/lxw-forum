package com.jfeat.forum.common.exception;


import com.jfeat.forum.model.ResultCode;

/**
 * @Author 林学文
 * @Date 2021/12/17 11:47
 * @Version 1.0
 */

public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }


}
