package edu.gzhh.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    /**
     * 用户id

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

    @TableField("toUid")
    private Long toUid;

    /**
     * 话题Id 
     */
    @TableField("topicId")
    private Long topicId;

    /**
     * 评论内容 
     */
    private String content;

    /**
     * 评论时间
     */
    @TableField("postTime")
    private String postTime;

    /**
     * 总回复数
     */
    @TableField("totalReply")

    private Integer totalReply;


    /*审核状态*/
    private Integer audit;

    /** 回复集合 **/
    @TableField(exist = false)
    private List<Reply> replyList = new ArrayList<Reply>();
}
