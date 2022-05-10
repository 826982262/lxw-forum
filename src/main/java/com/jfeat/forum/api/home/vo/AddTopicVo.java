package com.jfeat.forum.api.home.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author 林学文
 * @Date 2022/2/15 14:36
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AddTopicVo {
    /*标签id*/
    private Integer tagId;
    /*标签名*/
    private String tagName;
    /*标题*/
    private String title;
    /*内容*/
    private String content;
}
