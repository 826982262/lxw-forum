package edu.gzhh.forum.controller.home.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author 林学文
 * @Date 2021/12/20 11:39
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginUserVo {
    /**
     * 用户名
     */
    private String uname;

    /**
     * 密码
     */
    private String password;


    /*验证码*/
    private String code;
}
