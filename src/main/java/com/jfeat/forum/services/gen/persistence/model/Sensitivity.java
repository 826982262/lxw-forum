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
@ApiModel(value="Sensitivity对象", description="")
public class Sensitivity extends Model<Sensitivity> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "所属文章id")
      @TableField("topicId")
    private Long topicId;

      @ApiModelProperty(value = "类型")
      private String type;

      @ApiModelProperty(value = "审核内容的id")
      private Long cid;

      @ApiModelProperty(value = "审核的内容")
      private String content;

    @DateTimeFormat(pattern = "yyyy:MM:dd HH:mm:ss")
      @ApiModelProperty(value = "创建时间")
      @TableField("createTime")
    private Date createTime;

      @ApiModelProperty(value = "敏感内容")
      @TableField("senWord")
    private String senWord;

      @ApiModelProperty(value = "审核状态0为未审核，1为审核通过，2为审核不通过，3为文章发布状态")
      private Integer audit;

    
    public Long getId() {
        return id;
    }

      public Sensitivity setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getTopicId() {
        return topicId;
    }

      public Sensitivity setTopicId(Long topicId) {
          this.topicId = topicId;
          return this;
      }
    
    public String getType() {
        return type;
    }

      public Sensitivity setType(String type) {
          this.type = type;
          return this;
      }
    
    public Long getCid() {
        return cid;
    }

      public Sensitivity setCid(Long cid) {
          this.cid = cid;
          return this;
      }
    
    public String getContent() {
        return content;
    }

      public Sensitivity setContent(String content) {
          this.content = content;
          return this;
      }
    
    public Date getCreateTime() {
        return createTime;
    }

      public Sensitivity setCreateTime(Date createTime) {
          this.createTime = createTime;
          return this;
      }
    
    public String getSenWord() {
        return senWord;
    }

      public Sensitivity setSenWord(String senWord) {
          this.senWord = senWord;
          return this;
      }
    
    public Integer getAudit() {
        return audit;
    }

      public Sensitivity setAudit(Integer audit) {
          this.audit = audit;
          return this;
      }

      public static final String ID = "id";

      public static final String TOPICID = "topicId";

      public static final String TYPE = "type";

      public static final String CID = "cid";

      public static final String CONTENT = "content";

      public static final String CREATETIME = "createTime";

      public static final String SENWORD = "senWord";

      public static final String AUDIT = "audit";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Sensitivity{" +
              "id=" + id +
                  ", topicId=" + topicId +
                  ", type=" + type +
                  ", cid=" + cid +
                  ", content=" + content +
                  ", createTime=" + createTime +
                  ", senWord=" + senWord +
                  ", audit=" + audit +
              "}";
    }
}
