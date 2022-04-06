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
 * @since 2022-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Sensitivity implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 类型
     */

    private String type;
    /*
    所属文章id
     */
    @TableField("topicId")
    private Long topicId;
    /**
     * 审核内容的id
     */

    private Long cid;

    /**
     * 审核的内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 敏感内容
     */
    @TableField("senWord")
    private String senWord;

    /**
     * 审核状态
     */
    /*创建时间*/
    @TableField("createTime")
    private String createTime;

    private Integer audit;


}
