package edu.gzhh.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gzhh.forum.entity.Comment;
import edu.gzhh.forum.entity.Reply;
import edu.gzhh.forum.mapper.CommentMapper;
import edu.gzhh.forum.mapper.ReplyMapper;
import edu.gzhh.forum.model.response.CommonCode;
import edu.gzhh.forum.model.response.QueryResult;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ReplyMapper replyMapper;
    @Override
    public ResponseResult addComment(Comment comment) {
        Integer flag = commentMapper.insert(comment);
        if (flag>0){
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
    @Override
    public QueryResult selectCommentByTopicId(Long topicId,Integer start, Integer limit){
        /*查询主贴下面的评论*/
        List<Comment> comment = commentMapper.selectByTopicId(topicId,start,limit);
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("topicId",topicId);
        queryWrapper.ne("audit",3);
        /*查询评论总数*/
        long total = commentMapper.selectCount(queryWrapper);
        QueryResult<Comment> result = new QueryResult<>();

        result.setTotal(total);
        /*遍历评论，查询底下是否存在回复*/
        if (!comment.isEmpty()){
            for (Comment comment1 : comment){
                List<Reply> reply = replyMapper.selectByCommentIdReplies(comment1.getCommentId());
                if (!reply.isEmpty()) {
                    /*存在回复，把回复集合放入评论的回复集合属性中*/
                    comment1.setReplyList(reply);
                }}
            result.setList(comment);
            /*返回查询结果*/
           return result;
        }
        return result;
    }

    @Override
    public List<Comment> selectCheckComment(Integer audit) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audit",audit);
        List<Comment> commentLists = commentMapper.selectList(queryWrapper);
        return commentLists;
    }

    @Override
    public Integer updateAuditResult(List<Long> commentIds, Integer audit) {
        return commentMapper.updateAudit(commentIds,audit);
    }
}
