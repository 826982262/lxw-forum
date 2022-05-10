package com.jfeat.forum.services.domain.service;

import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.gen.crud.service.CRUDCommentOverModelService;
import com.jfeat.forum.services.gen.persistence.model.Comment;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface CommentOverModelService extends CRUDCommentOverModelService{
    ResponseResult addComment(Comment comment);
    QueryResult selectCommentByTopicId(Long topicId, Integer start, Integer limit);
    List<Comment> selectCheckComment(Integer audit);
    Integer updateAuditResult(List<Long> commentIds,Integer audit);
}