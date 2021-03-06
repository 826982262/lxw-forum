package com.jfeat.forum.api.home.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * @Author 林学文
 * @Date 2022/2/12 19:58
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TopicPo {
    /*话题id*/
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /*标签名*/
    private String lName;
    /*作者*/
    private String uname;
    /* 作者头像*/
    private String headUrl;
    /*标题*/
    private String title;
    /*发表时间*/
    private String time;
    /*是否置顶*/
    @TableField("istop")
    private Integer istop;
    /*发表内容摘要*/
    private String summary;

}
