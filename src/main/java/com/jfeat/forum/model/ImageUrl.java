package com.jfeat.forum.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author 林学文
 * @Date 2022/2/14 20:51
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ImageUrl {
    /*图片地址*/
    private String url;

}
