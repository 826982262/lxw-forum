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
 * @since 2022-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Topic implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 话题id
     */
    @TableId(value = "t_id", type = IdType.AUTO)
    private Long tId;

    private Integer lId;

    private String lName;

    private Long uid;
    /*作者*/
    @TableField(exist = false)
    private String uname;
    /* 作者头像*/
    @TableField(exist = false)
    private String headUrl;
    private String title;

    private String time;

    /**
     * 最后更新时间
     */
    @TableField("updateTime")
    private String updateTime;
    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 内容摘要
     */
    private String summary;
    /*审核通过*/

    private Integer audit;



    /*是否置顶*/
    @TableField("istop")
    private Integer istop;

    /** 评论总数 **/
    @TableField(exist = false)
    private Long commentTotal = 0L;
    /** 允许评论 **/
    @TableField(exist = false)
    private boolean allow = true;
}
