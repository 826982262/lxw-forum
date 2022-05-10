package com.jfeat.forum.services.domain.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.ReplyService;
import com.jfeat.forum.services.gen.crud.service.impl.CRUDReplyServiceImpl;
import com.jfeat.forum.services.gen.persistence.dao.ReplyMapper;
import com.jfeat.forum.services.gen.persistence.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("replyService")
public class ReplyServiceImpl extends CRUDReplyServiceImpl implements ReplyService {
    @Resource
    ReplyMapper replyMapper;
    @Override
    protected String entityName() {
        return "Reply";
    }

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
