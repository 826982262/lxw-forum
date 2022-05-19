package com.jfeat.forum.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code generator
 * @since 2022-05-10
 */
@Data
@ApiModel(value="User对象", description="")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "用户ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private String account;

      @ApiModelProperty(value = "用户名")
      private String uname;

      @ApiModelProperty(value = "密码")
      private String password;

      @ApiModelProperty(value = "邮箱")
      private String email;

      @ApiModelProperty(value = "权限")
      private Integer flag;

      @ApiModelProperty(value = "头像地址")
      private String headUrl;
    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
      @ApiModelProperty(value = "创建时间")
      @TableField("createTime")
    private Date createTime;

    
    public Long getId() {
        return id;
    }

      public User setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getAccount() {
        return account;
    }

      public User setAccount(String account) {
          this.account = account;
          return this;
      }
    
    public String getUname() {
        return uname;
    }

      public User setUname(String uname) {
          this.uname = uname;
          return this;
      }
    
    public String getPassword() {
        return password;
    }

      public User setPassword(String password) {
          this.password = password;
          return this;
      }
    
    public String getEmail() {
        return email;
    }

      public User setEmail(String email) {
          this.email = email;
          return this;
      }
    
    public Integer getFlag() {
        return flag;
    }

      public User setFlag(Integer flag) {
          this.flag = flag;
          return this;
      }
    
    public String getHeadUrl() {
        return headUrl;
    }

      public User setHeadUrl(String headUrl) {
          this.headUrl = headUrl;
          return this;
      }
    
    public Date getCreateTime() {
        return createTime;
    }

      public User setCreateTime(Date createTime) {
          this.createTime = createTime;
          return this;
      }

      public static final String ID = "id";

      public static final String ACCOUNT = "account";

      public static final String UNAME = "uname";

      public static final String PASSWORD = "password";

      public static final String EMAIL = "email";

      public static final String FLAG = "flag";

      public static final String HEAD_URL = "head_url";

      public static final String CREATETIME = "createTime";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "User{" +
              "id=" + id +
                  ", account=" + account +
                  ", uname=" + uname +
                  ", password=" + password +
                  ", email=" + email +
                  ", flag=" + flag +
                  ", headUrl=" + headUrl +
                  ", createTime=" + createTime +
              "}";
    }
}
