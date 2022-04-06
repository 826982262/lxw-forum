package edu.gzhh.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * @since 2022-02-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Label implements Serializable {

    private static final long serialVersionUID=1L;

    @JsonProperty(value = "lId")
    @TableId(value = "l_id", type = IdType.AUTO)
    private Integer lId;

    @JsonProperty(value = "lName")
    private String lName;
    @TableField("ranking")
    @JsonProperty(value = "ranking")
    private Integer ranking;
}
