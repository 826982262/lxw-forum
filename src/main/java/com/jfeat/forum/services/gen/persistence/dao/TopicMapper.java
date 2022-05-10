package com.jfeat.forum.services.gen.persistence.dao;

import com.jfeat.forum.api.home.po.TopicPo;
import com.jfeat.forum.services.gen.crud.model.TopicModel;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Code generator
 * @since 2022-05-10
 */
public interface TopicMapper extends BaseMapper<Topic> {
    List<TopicPo> selectAllIndexTopicByLabelId(@Param("tagId") Integer tagId,
                                               @Param("start") Integer start,
                                               @Param("limit") Integer limit,
                                               @Param("keyword") String keyword
    );
    Topic selectTopicByTopicId(@Param("topicId")Long topicId);
    List<Topic> selectCheckTopic(@Param("check") boolean chack);

    Integer updateAudit(@Param("topicIds") List<Long> topicIds,@Param("audit") Integer audit);

    List<Topic> selectTopicListsByAudit(@Param("start")Integer start,@Param("limit")Integer limit,
                                        @Param("audit")Integer audit);
    Integer updateTopicAuditByIds(@Param("ids") List<Long> ids,@Param("audit") Integer audit);
    Integer updateTopicIsTopByIds(@Param("ids") List<Long> ids,@Param("istop") Integer istop);

    List<Topic> selectTopicByUid(@Param("uid")Long uid,
                                 @Param("start") Integer start,
                                 @Param("limit") Integer limit);
}
