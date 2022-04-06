package edu.gzhh.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gzhh.forum.entity.Reply;
import edu.gzhh.forum.model.response.ResponseResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxw
 * @since 2022-02-19
 */
public interface ReplyService extends IService<Reply> {
    ResponseResult addReply(Reply reply);
    List<Reply> selectNotCheckReply(Integer audit);
    Integer updateAuditResult(List<Long> topicIds, Integer audit);
}
