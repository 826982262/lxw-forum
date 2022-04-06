package edu.gzhh.forum.controller.home.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author 林学文
 * @Date 2022/2/20 23:04
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class addComment {
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

}
