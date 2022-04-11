package edu.gzhh.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gzhh.forum.entity.Topic;
import edu.gzhh.forum.model.QueryResult;
import edu.gzhh.forum.model.ResponseResult;
import edu.gzhh.forum.util.PageQueryUtil;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxw
 * @since 2022-02-12
 */
public interface TopicService extends IService<Topic> {
    QueryResult selectTopicByLabelId(Integer tagId, Integer start, Integer limit);
    ResponseResult addTopic(Topic topic);
    Topic selectTopicByTopicId(Long topicId);
    List<Topic> selectNotCheckTopic(Integer audit);
    Integer updateAuditResult(List<Long> topicIds,Integer audit);
    ResponseResult UpdateAuditByIds(List<Long> Ids,Integer audit);
    QueryResult selectTopicByAudit(PageQueryUtil pageQueryUtil,Integer audit);
    ResponseResult UpdateIsTopByIds(List<Long> Ids,Integer istop);
    ResponseResult updateTopic(Topic topic);
}
