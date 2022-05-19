package com.jfeat.forum.services.gen.persistence.dao;

import com.jfeat.forum.services.gen.crud.model.ReplyModel;
import com.jfeat.forum.services.gen.persistence.model.Reply;
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
public interface ReplyMapper extends BaseMapper<Reply> {

    List<Reply> selectByCommentIdReplies(Long id);
    Integer updateAudit(@Param("replyIds") List<Long> replyIds, @Param("audit") Integer audit);

    List<ReplyModel> selectReplyByUid(@Param("uid")Long uid,
                                      @Param("start") Integer start,
                                      @Param("limit") Integer limit);
}
