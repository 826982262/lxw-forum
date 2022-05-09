package edu.gzhh.forum.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gzhh.forum.common.exception.ExceptionCast;
import edu.gzhh.forum.controller.home.po.TopicPo;
import edu.gzhh.forum.entity.Topic;
import edu.gzhh.forum.mapper.CommentMapper;
import edu.gzhh.forum.mapper.ReplyMapper;
import edu.gzhh.forum.mapper.TopicMapper;
import edu.gzhh.forum.model.CommonCode;
import edu.gzhh.forum.model.QueryResult;
import edu.gzhh.forum.model.ResponseResult;
import edu.gzhh.forum.model.TopicCode;
import edu.gzhh.forum.service.TopicService;
import edu.gzhh.forum.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxw
 * @since 2022-02-12
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

    @Autowired
    TopicMapper topicMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ReplyMapper replyMapper;
    /**
     * 首页话题
     * @param tagId
     * @param start
     * @param limit
     * @return
     */
    @Override
    public QueryResult selectTopicByLabelId(Integer tagId, Integer start, Integer limit,String keyword) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("l_id",tagId);

        queryWrapper.ne("audit",3);
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
       queryWrapper.eq("t_id",topic.getTId());

        Topic updateTopic = topicMapper.selectOne(queryWrapper);
        if (!updateTopic.getUid().equals(topic.getUid())){
            ExceptionCast.cast(CommonCode.UNAUTHORISE);
        }
        updateTopic.setLName(topic.getLName());
        updateTopic.setLId(topic.getLId());
        updateTopic.setUpdateTime(DateUtil.now());
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
