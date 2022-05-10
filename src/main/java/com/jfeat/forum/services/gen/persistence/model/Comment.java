package com.jfeat.forum.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.ArrayList;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code generator
 * @since 2022-05-10
 */
@Data
@ApiModel(value="Comment对象", description="")
public class Comment extends Model<Comment> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "用户id\r\n")
      private Long uid;

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
      @ApiModelProperty(value = "接收者id")
      @TableField("toUid")
    private Long toUid;

      @ApiModelProperty(value = "话题Id ")
      @TableField("topicId")
    private Long topicId;

      @ApiModelProperty(value = "评论内容 ")
      private String content;
    @JsonFormat(timezone = "GMT+8" , pattern = "yyyy:MM:dd HH:mm:ss")
      @ApiModelProperty(value = "评论时间")
      @TableField("postTime")
    private Date postTime;


    /** 回复集合 **/
    @TableField(exist = false)
    private List<Reply> replyList = new ArrayList<Reply>();

      @ApiModelProperty(value = "总回复数")
      @TableField("totalReply")
    private Integer totalReply;

      @ApiModelProperty(value = "0为未审核，1为待人工审核，2为审核通过，3为不通过")
      private Integer audit;


    
    public Long getId() {
        return id;
    }

      public Comment setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getUid() {
        return uid;
    }

      public Comment setUid(Long uid) {
          this.uid = uid;
          return this;
      }
    
    public Long getToUid() {
        return toUid;
    }

      public Comment setToUid(Long toUid) {
          this.toUid = toUid;
          return this;
      }
    
    public Long getTopicId() {
        return topicId;
    }

      public Comment setTopicId(Long topicId) {
          this.topicId = topicId;
          return this;
      }
    
    public String getContent() {
        return content;
    }

      public Comment setContent(String content) {
          this.content = content;
          return this;
      }
    
    public Date getPostTime() {
        return postTime;
    }

      public Comment setPostTime(Date postTime) {
          this.postTime = postTime;
          return this;
      }
    
    public Integer getTotalReply() {
        return totalReply;
    }

      public Comment setTotalReply(Integer totalReply) {
          this.totalReply = totalReply;
          return this;
      }
    
    public Integer getAudit() {
        return audit;
    }

      public Comment setAudit(Integer audit) {
          this.audit = audit;
          return this;
      }

      public static final String ID = "id";

      public static final String UID = "uid";

      public static final String TOUID = "toUid";

      public static final String TOPICID = "topicId";

      public static final String CONTENT = "content";

      public static final String POSTTIME = "postTime";

      public static final String TOTALREPLY = "totalReply";

      public static final String AUDIT = "audit";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Comment{" +
              "id=" + id +
                  ", uid=" + uid +
                  ", toUid=" + toUid +
                  ", topicId=" + topicId +
                  ", content=" + content +
                  ", postTime=" + postTime +
                  ", totalReply=" + totalReply +
                  ", audit=" + audit +
              "}";
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
