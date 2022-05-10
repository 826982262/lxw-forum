package com.jfeat.forum.model;

import lombok.Data;
import lombok.ToString;

/**
 * @Author 林学文
 * @Date 2021/12/16 15:52
 * @Version 1.0
 */
@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult{
    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode, QueryResult<T> queryResult) {
        super(resultCode);
        this.queryResult = queryResult;
    }
}
