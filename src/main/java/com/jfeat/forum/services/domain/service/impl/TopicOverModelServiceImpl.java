package com.jfeat.forum.services.domain.service.impl;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.forum.api.home.po.TopicPo;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.model.TopicCode;
import com.jfeat.forum.services.domain.service.TopicOverModelService;
import com.jfeat.forum.services.gen.crud.model.TopicModel;
import com.jfeat.forum.services.gen.crud.service.impl.CRUDTopicOverModelServiceImpl;
import com.jfeat.forum.services.gen.persistence.dao.CommentMapper;
import com.jfeat.forum.services.gen.persistence.dao.ReplyMapper;
import com.jfeat.forum.services.gen.persistence.dao.TopicMapper;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import com.jfeat.forum.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("topicService")
public class TopicOverModelServiceImpl extends CRUDTopicOverModelServiceImpl implements TopicOverModelService {
    @Resource
    TopicMapper topicMapper;
    @Resource
    CommentMapper commentMapper;
    @Resource
    ReplyMapper replyMapper;
    @Override
    protected String entityName() {
        return "Topic";
    }


    /**
     * 首页话题
     * @param tagId
     * @param start
     * @param limit
     * @return
     */
    @Override
    public QueryResult selectTopicByLabelId(Integer tagId, Integer start, Integer limit, String keyword) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("l_id",tagId);
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.like("content_filter",keyword);
            queryWrapper.or();
            queryWrapper.like("title",keyword);
        }

        queryWrapper.ne("audit",3);
        queryWrapper.ne("audit",4);
        long total =  topicMapper.selectCount(queryWrapper);
        List<TopicPo> topicLists = topicMapper.selectAllIndexTopicByLabelId(tagId,start,limit,keyword);
        QueryResult<TopicPo> queryResult = new QueryResult<>();
        queryResult.setList(topicLists);
        queryResult.setTotal(total);
        return queryResult;
    }

    /**
     * 添加话题文章
     * @param
     * @return
     */
    @Override
    public ResponseResult addTopic(Topic topic) {

        int flag =  topicMapper.insert(topic);
        if (flag > 0) {
            return new ResponseResult(TopicCode.SUBMITTOPICSUCCESS);

        }
        return new ResponseResult(TopicCode.SUBMITTOPICFAIL);

    }

    @Override
    public Topic selectTopicByTopicId(Long topicId) {
        Topic topic = topicMapper.selectTopicByTopicId(topicId);

        if (ObjectUtil.isNull(topic)){
            ExceptionCast.cast(CommonCode.SERVER_ERROR);
        }
        return topic;
    }

    @Override
    public List<Topic> selectNotCheckTopic(Integer audit) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit",audit);

        List<Topic> topicLists = topicMapper.selectList(queryWrapper);
        return topicLists;
    }
    @Override
    public Integer updateAuditResult(List<Long> topicIds, Integer audit){


        return  topicMapper.updateAudit(topicIds,audit);
    }

    @Override
    public QueryResult selectTopicByAudit(PageQueryUtil pageQueryUtil, Integer audit) {

        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        if (audit!=null){
            queryWrapper.eq("audit",audit);

        }
        int total = topicMapper.selectCount(queryWrapper);

        List<Topic> topicList = topicMapper.selectTopicListsByAudit(pageQueryUtil.getStart(),pageQueryUtil.getLimit(),audit);
        QueryResult<Topic> result = new QueryResult<>();
        result.setTotal(total);
        result.setList(topicList);
        result.setCurrPage(pageQueryUtil.getPage());
        result.setPageSize(pageQueryUtil.getLimit());

        return result;
    }

    @Override
    public ResponseResult UpdateAuditByIds(List<Long> Ids, Integer audit) {
        Integer sign = topicMapper.updateTopicAuditByIds(Ids,audit);
        if (sign>0){
            return new ResponseResult(CommonCode.SUCCESS);
        }else {
            return new ResponseResult(CommonCode.FAIL);
        }
    }

    @Override
    public ResponseResult UpdateIsTopByIds(List<Long> Ids, Integer istop) {
        Integer sign = topicMapper.updateTopicIsTopByIds(Ids,istop);
        if (sign>0){
            return new ResponseResult(CommonCode.SUCCESS);
        }else {
            return new ResponseResult(CommonCode.FAIL);
        }
    }

    @Override
    public ResponseResult updateTopic(Topic topic) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("t_id",topic.getId());

        Topic updateTopic = topicMapper.selectOne(queryWrapper);
        if (!updateTopic.getUid().equals(topic.getUid())){
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        updateTopic.setlId(topic.getlId());
        updateTopic.setlId(topic.getlId());
        updateTopic.setUpdateTime(DateUtil.date());
        updateTopic.setSummary(topic.getSummary());
        updateTopic.setContent(topic.getContent());
        updateTopic.setTitle(topic.getTitle());
        updateTopic.setAudit(0);
        Integer flag = topicMapper.update(updateTopic,queryWrapper);
        if (flag>0){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public QueryResult selectTopicByUserId(Long uid,Integer start, Integer limit) {

        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        long total =  topicMapper.selectCount(queryWrapper);
        List<Topic> topicLists = topicMapper.selectTopicByUid(uid,start,limit);
        QueryResult<Topic> queryResult = new QueryResult<>();
        queryResult.setList(topicLists);
        queryResult.setTotal(total);
        return queryResult;


    }

                            }
