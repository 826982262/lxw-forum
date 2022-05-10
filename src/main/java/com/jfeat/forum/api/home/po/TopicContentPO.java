package com.jfeat.forum.api.home.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jfeat.forum.services.gen.persistence.model.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author 林学文
 * @Date 2022/2/16 14:31
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopicContentPO {
    /*话题id*/
    @TableId(value = "id", type = IdType.AUTO)
    private Long tId;
    /*标签id*/
    private Integer lId;
    /*标签名*/
    private String lName;
    /*作者*/
    private String uname;

    private String uId;
    /* 作者头像*/
    private String headUrl;
    /*标题*/
    private String title;
    /*发表时间*/
    private String time;

    private String content;
    /*回复内容*/
    private List<Comment> comments;
}
