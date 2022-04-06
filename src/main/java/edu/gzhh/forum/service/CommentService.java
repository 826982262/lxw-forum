package edu.gzhh.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gzhh.forum.entity.Comment;
import edu.gzhh.forum.model.response.QueryResult;
import edu.gzhh.forum.model.response.ResponseResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxw
 * @since 2022-02-19
 */
public interface CommentService extends IService<Comment> {
    ResponseResult addComment(Comment comment);
    QueryResult selectCommentByTopicId(Long topicId, Integer start, Integer limit);
    List<Comment> selectCheckComment(Integer audit);
    Integer updateAuditResult(List<Long> commentIds,Integer audit);
}
