package edu.gzhh.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gzhh.forum.controller.home.po.TopicPo;
import edu.gzhh.forum.entity.Topic;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxw
 * @since 2022-02-12
 */
@Component
public interface TopicMapper extends BaseMapper<Topic> {
    List<TopicPo> selectAllIndexTopicByLabelId(@Param("tagId") Integer tagId,
                                               @Param("start") Integer start,
                                               @Param("limit") Integer limit,
                                               @Param("keyword") String keyword
                                               );
    Topic selectTopicByTopicId(Long topicId);
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
