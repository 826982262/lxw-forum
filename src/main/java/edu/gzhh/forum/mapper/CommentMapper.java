package edu.gzhh.forum.mapper;

import edu.gzhh.forum.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxw
 * @since 2022-02-19
 */
@Component
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> selectByTopicId(@Param("topicId") Long topicId,
                                  @Param("start") Integer start,
                                  @Param("limit") Integer limit);
    Integer updateAudit(@Param("commentIds") List<Long> commentIds,@Param("audit") Integer audit);
}
