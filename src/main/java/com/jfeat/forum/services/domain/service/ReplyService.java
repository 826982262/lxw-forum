package com.jfeat.forum.services.domain.service;

import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.gen.crud.service.CRUDReplyService;
import com.jfeat.forum.services.gen.persistence.model.Reply;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface ReplyService extends CRUDReplyService{
    ResponseResult addReply(Reply reply);
    List<Reply> selectNotCheckReply(Integer audit);
    Integer updateAuditResult(List<Long> topicIds, Integer audit);

    QueryResult selectReplyByUserId(Long uid, Integer start, Integer limit);
}