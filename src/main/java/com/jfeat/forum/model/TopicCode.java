package com.jfeat.forum.model;

/**
 * @Author 林学文
 * @Date 2022/1/18 16:20
 * @Version 1.0
 */
public enum  TopicCode implements ResultCode{
    SUBMITTOPICSUCCESS(true,100,"提交成功"),
    SUBMITTOPICFAIL(false,104,"提交失败");

    TopicCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    @Override
    public boolean success() {
        return success;
    }
    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
