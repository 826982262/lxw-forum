package edu.gzhh.forum.model.response;

import java.util.List;

/**
 * @Author 林学文
 * @Date 2021/12/16 15:53
 * @Version 1.0
 */
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
