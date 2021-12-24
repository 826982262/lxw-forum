package edu.gzhh.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lxw
 * @since 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户名
     */
    private String uname;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 权限
     */
    private Integer flag;

    /**
     * 头像地址
     */
    private String headUrl;


    public User(String uname, String password, String phone, String email, Integer sex, Integer flag, String headUrl) {
        this.uname = uname;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.sex = sex;
        this.flag = flag;
        this.headUrl = headUrl;
    }

    public User() {
    }
}
