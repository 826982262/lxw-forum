package com.jfeat.forum.services.gen.persistence.dao;

import com.jfeat.forum.services.gen.crud.model.CommentModel;
import com.jfeat.forum.services.gen.persistence.model.Comment;
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
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> selectByTopicId(@Param("topicId")Long topicId, @Param("start")Integer start, @Param("limit")Integer limit);

    Integer updateAudit(@Param("commentIds")List<Long> commentIds, @Param("audit")Integer audit);
    List<CommentModel> selectCommentByUid(@Param("uid")Long uid,
                                          @Param("start") Integer start,
                                          @Param("limit") Integer limit);
}
