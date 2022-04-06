package edu.gzhh.forum.mapper;

import edu.gzhh.forum.entity.Reply;
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
public interface ReplyMapper extends BaseMapper<Reply> {
    List<Reply> selectByCommentIdReplies(@Param("commentId") Long commentId);
    Integer updateAudit(@Param("replyIds") List<Long> replyIds,@Param("audit") Integer audit);
}
