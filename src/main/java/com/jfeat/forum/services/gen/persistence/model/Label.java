package com.jfeat.forum.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@ApiModel(value="Label对象", description="")
public class Label extends Model<Label> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @TableField("l_name")
      @ApiModelProperty(value = "标签名")
      private String lName;

     @TableField("ranking")
      @ApiModelProperty(value = "排序值")
      private Integer ranking;

    
    public Long getId() {
        return id;
    }

      public Label setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getlName() {
        return lName;
    }

      public Label setlName(String lName) {
          this.lName = lName;
          return this;
      }
    
    public Integer getRanking() {
        return ranking;
    }

      public Label setRanking(Integer ranking) {
          this.ranking = ranking;
          return this;
      }

      public static final String ID = "id";

      public static final String L_NAME = "l_name";

      public static final String RANKING = "ranking";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Label{" +
              "id=" + id +
                  ", lName=" + lName +
                  ", ranking=" + ranking +
              "}";
    }
}
