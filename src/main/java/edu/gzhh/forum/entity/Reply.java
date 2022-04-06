package edu.gzhh.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lxw
 * @since 2022-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Reply implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * Id
     */
    @TableId(value = "reply_id", type = IdType.AUTO)
    private Long replyId;

    /**
     * 评论Id
     */
    @TableField("commentId")
    private Long commentId;
    /**
     * 评论Id
     */
    @TableField(value = "commentuId")
    private Long commentuId;
    /**
     * 用户
     */

    private Long uid;
    /**
     * 用户名称
     */
    @TableField(exist = false)
    private String uname;
    /**
     * 用户账号
     */
    @TableField(exist = false)
    private String account;


    /**
     * 头像地址
     */
    @TableField(exist = false)
    private String headUrl;
    /**
     * 话题Id
     */
    @TableField("topicId")
    private Long topicId;


    /**
     * 回复时间 
     */
    private String time;

    /**
     * 回复内容
     */
    private String content;

    /*审核状态*/
    private Integer audit;

}
