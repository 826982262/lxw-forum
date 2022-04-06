package edu.gzhh.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gzhh.forum.entity.Reply;
import edu.gzhh.forum.mapper.ReplyMapper;
import edu.gzhh.forum.model.response.CommonCode;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxw
 * @since 2022-02-19
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;
    @Override
    public ResponseResult addReply(Reply reply) {
        int flag = replyMapper.insert(reply);
        if (flag>0){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    @Override
    public List<Reply> selectNotCheckReply(Integer audit) {
        QueryWrapper<Reply> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit",audit);
        List<Reply> replyLists = replyMapper.selectList(queryWrapper);
        return replyLists;
    }

    @Override
    public Integer updateAuditResult(List<Long> replyIds, Integer audit) {
        return replyMapper.updateAudit(replyIds,audit);
    }
}
