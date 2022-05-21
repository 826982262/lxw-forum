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
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
@ApiModel(value="Reply对象", description="")
public class Reply extends Model<Reply> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "Id")
      @TableId(value = "reply_id", type = IdType.AUTO)
      private Long replyId;

    @TableField("commentuId")
    private Long commentuId;

      @ApiModelProperty(value = "评论Id")
      @TableField("commentId")
    private Long commentId;

      @ApiModelProperty(value = "用户")
      private Long uid;

      @ApiModelProperty(value = "话题Id")
      @TableField("topicId")
    private Long topicId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
      @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
      @ApiModelProperty(value = "回复时间 ")
      private Date time;

      @ApiModelProperty(value = "回复内容")
      private String content;

      @ApiModelProperty(value = "0为未审核，1为待人工审核，2为审核通过，3为不通过,4为隐藏")
      private Integer audit;

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
    public Long getReplyId() {
        return replyId;
    }

      public Reply setReplyId(Long replyId) {
          this.replyId = replyId;
          return this;
      }
    
    public Long getCommentuId() {
        return commentuId;
    }

      public Reply setCommentuId(Long commentuId) {
          this.commentuId = commentuId;
          return this;
      }
    
    public Long getCommentId() {
        return commentId;
    }

      public Reply setCommentId(Long commentId) {
          this.commentId = commentId;
          return this;
      }
    
    public Long getUid() {
        return uid;
    }

      public Reply setUid(Long uid) {
          this.uid = uid;
          return this;
      }
    
    public Long getTopicId() {
        return topicId;
    }

      public Reply setTopicId(Long topicId) {
          this.topicId = topicId;
          return this;
      }
    
    public Date getTime() {
        return time;
    }

      public Reply setTime(Date time) {
          this.time = time;
          return this;
      }
    
    public String getContent() {
        return content;
    }

      public Reply setContent(String content) {
          this.content = content;
          return this;
      }
    
    public Integer getAudit() {
        return audit;
    }

      public Reply setAudit(Integer audit) {
          this.audit = audit;
          return this;
      }

      public static final String REPLY_ID = "reply_id";

      public static final String COMMENTUID = "commentuId";

      public static final String COMMENTID = "commentId";

      public static final String UID = "uid";

      public static final String TOPICID = "topicId";

      public static final String TIME = "time";

      public static final String CONTENT = "content";

      public static final String AUDIT = "audit";

      @Override
    protected Serializable pkVal() {
          return this.replyId;
      }

    @Override
    public String toString() {
        return "Reply{" +
              "replyId=" + replyId +
                  ", commentuId=" + commentuId +
                  ", commentId=" + commentId +
                  ", uid=" + uid +
                  ", topicId=" + topicId +
                  ", time=" + time +
                  ", content=" + content +
                  ", audit=" + audit +
              "}";
    }
}
