package edu.gzhh.forum.model.response;

/**
 * @Author 林学文
 * @Date 2021/12/16 15:50
 * @Version 1.0
 */
public enum CommonCode implements ResultCode{
    LOGINERROR(false,500,"用户名或密码错误"),
    CODE_ERROR(false,500,"验证码错误"),
    INVALID_PARAM(false,10003,"非法参数！"),
    ISNOTNULL(false,10003,"参数不能为空"),
    LOGSUCCESS(true,200,"登录成功"),
    REGISTERSUCCESS(true,200,"注册成功"),
    REGISTERFAIL(false,500,"注册失败"),
    LOGFAIL(false,500,"登录失败"),
    SUCCESS(true,10000,"操作成功！"),
    FAIL(false,11111,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");
    //    private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private CommonCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

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
