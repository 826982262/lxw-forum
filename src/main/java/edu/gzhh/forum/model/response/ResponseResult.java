package edu.gzhh.forum.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author 林学文
 * @Date 2021/12/16 15:39
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements Response {


    //操作是否成功
    boolean success = SUCCESS;

    //操作代码
    int code = SUCCESS_CODE;

    //提示信息
    String message;
    String jumpUri;

    public ResponseResult(ResultCode resultCode){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }
    public ResponseResult(ResultCode resultCode,String jumpUri){
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.jumpUri = jumpUri;
    }


    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }

    public void setJumpUri(String jumpUri) {
        this.jumpUri = jumpUri;
    }
}
