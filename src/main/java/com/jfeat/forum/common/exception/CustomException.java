package com.jfeat.forum.common.exception;


import com.jfeat.forum.model.ResultCode;

/**
 * @Author 林学文
 * @Date 2021/12/17 11:44
 * @Version 1.0
 */
public class CustomException extends  RuntimeException{
    ResultCode resultCode;
    public CustomException(ResultCode resultCode){this.resultCode=resultCode;}
    public ResultCode getResultCode(){return resultCode;}
}
