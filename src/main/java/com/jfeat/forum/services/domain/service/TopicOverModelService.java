package com.jfeat.forum.services.domain.service;

import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.gen.crud.service.CRUDTopicOverModelService;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import com.jfeat.forum.util.PageQueryUtil;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface TopicOverModelService extends CRUDTopicOverModelService{
    QueryResult selectTopicByLabelId(Integer tagId, Integer start, Integer limit, String keyword);
    ResponseResult addTopic(Topic topic);
    Topic selectTopicByTopicId(Long topicId);
    List<Topic> selectNotCheckTopic(Integer audit);
    Integer updateAuditResult(List<Long> topicIds,Integer audit);
    ResponseResult UpdateAuditByIds(List<Long> Ids,Integer audit);
    QueryResult selectTopicByAudit(PageQueryUtil pageQueryUtil, Integer audit);
    ResponseResult UpdateIsTopByIds(List<Long> Ids,Integer istop);
    ResponseResult updateTopic(Topic topic);

    QueryResult selectTopicByUserId(Long uid,Integer start, Integer limit);
}