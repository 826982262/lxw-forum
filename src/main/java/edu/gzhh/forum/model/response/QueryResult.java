package edu.gzhh.forum.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author 林学文
 * @Date 2021/12/16 15:53
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QueryResult<T> {
    //数据列表
    private List<T> list;
    //数据总数
    private long total;
    //当前页数
    private Integer currPage;
    //每页记录数
    private Integer pageSize;
    //总页数
    private Integer totalPage;

    public QueryResult(List<T> list, long total, Integer currPage, int pageSize) {
        this.list = list;
        this.total = total;
        this.currPage = currPage;
        this.pageSize = pageSize;
        this.totalPage = (int) Math.ceil(1.0* total/pageSize);
    }

    public QueryResult() {
    }
}
