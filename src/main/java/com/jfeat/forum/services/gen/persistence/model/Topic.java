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

/**
 * <p>
 * 
 * </p>
 *
 * @author Code generator
 * @since 2022-05-10
 */
@Data
@ApiModel(value="Topic对象", description="")
public class Topic extends Model<Topic> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "话题id")
      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private Integer lId;

    private String lName;

    private Long uid;

    private String title;
    @JsonFormat(timezone = "GMT+8" , pattern = "yyyy:MM:dd HH:mm:ss")
    private Date time;
    @JsonFormat(timezone = "GMT+8" , pattern = "yyyy:MM:dd HH:mm:ss")
      @ApiModelProperty(value = "更新时间")
      @TableField("updateTime")
    private Date updateTime;
    /* 作者头像*/
    @TableField(exist = false)
    private String headUrl;
    /*作者*/
    @TableField(exist = false)
    private String uname;
      @ApiModelProperty(value = "内容")
      private String content;

      @ApiModelProperty(value = "内容摘要")
      private String summary;

      @ApiModelProperty(value = "评论总数")
      @TableField("commentTotal")
    private Long commentTotal;

      @ApiModelProperty(value = "s是否置顶")
      private Integer istop;

      @ApiModelProperty(value = "0为未审核，1为待人工审核，2为审核通过，3为不通过")
      private Integer audit;
    private String contentFilter;

    public String getContentFilter() {
        return contentFilter;
    }

    public Topic setContentFilter(String contentFilter) {
        this.contentFilter = contentFilter;
        return this;
    }

    public Long getId() {
        return id;
    }

      public Topic setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Integer getlId() {
        return lId;
    }

      public Topic setlId(Integer lId) {
          this.lId = lId;
          return this;
      }
    
    public String getlName() {
        return lName;
    }

      public Topic setlName(String lName) {
          this.lName = lName;
          return this;
      }
    
    public Long getUid() {
        return uid;
    }

      public Topic setUid(Long uid) {
          this.uid = uid;
          return this;
      }
    
    public String getTitle() {
        return title;
    }

      public Topic setTitle(String title) {
          this.title = title;
          return this;
      }
    
    public Date getTime() {
        return time;
    }

      public Topic setTime(Date time) {
          this.time = time;
          return this;
      }
    
    public Date getUpdateTime() {
        return updateTime;
    }

      public Topic setUpdateTime(Date updateTime) {
          this.updateTime = updateTime;
          return this;
      }
    
    public String getContent() {
        return content;
    }

      public Topic setContent(String content) {
          this.content = content;
          return this;
      }
    
    public String getSummary() {
        return summary;
    }

      public Topic setSummary(String summary) {
          this.summary = summary;
          return this;
      }
    
    public Long getCommentTotal() {
        return commentTotal;
    }

      public Topic setCommentTotal(Long commentTotal) {
          this.commentTotal = commentTotal;
          return this;
      }
    
    public Integer getIstop() {
        return istop;
    }

      public Topic setIstop(Integer istop) {
          this.istop = istop;
          return this;
      }
    
    public Integer getAudit() {
        return audit;
    }

      public Topic setAudit(Integer audit) {
          this.audit = audit;
          return this;
      }

      public static final String ID = "id";

      public static final String L_ID = "l_id";

      public static final String L_NAME = "l_name";

      public static final String UID = "uid";

      public static final String TITLE = "title";

      public static final String TIME = "time";

      public static final String UPDATETIME = "updateTime";

      public static final String CONTENT = "content";

      public static final String SUMMARY = "summary";

      public static final String COMMENTTOTAL = "commentTotal";

      public static final String ISTOP = "istop";

      public static final String AUDIT = "audit";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Topic{" +
              "id=" + id +
                  ", lId=" + lId +
                  ", lName=" + lName +
                  ", uid=" + uid +
                  ", title=" + title +
                  ", time=" + time +
                  ", updateTime=" + updateTime +
                  ", content=" + content +
                  ", summary=" + summary +
                  ", commentTotal=" + commentTotal +
                  ", istop=" + istop +
                  ", audit=" + audit +
              "}";
    }
}
