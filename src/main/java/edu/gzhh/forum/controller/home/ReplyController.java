package edu.gzhh.forum.controller.home;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import edu.gzhh.forum.common.exception.ExceptionCast;
import edu.gzhh.forum.entity.Reply;
import edu.gzhh.forum.entity.User;
import edu.gzhh.forum.interceptor.userLogin;
import edu.gzhh.forum.model.response.CommonCode;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxw
 * @since 2022-02-12
 */
@Controller
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    ReplyService replyService;
    @userLogin
    @RequestMapping("/add")
    @ResponseBody
    public ResponseResult addReply(@RequestParam("commentId")Long commentId,
                                   @RequestParam("topicId")Long topicId,
                                   @RequestParam("commentuid")Long commentuId,
                                   @RequestParam("content")String content,
                                   HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (ObjectUtil.isNull(user)){
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        if (ObjectUtil.hasNull(commentId,topicId,commentuId,content)){ExceptionCast.cast(CommonCode.ISNOTNULL);}
        Reply reply = new Reply();
        reply.setCommentId(commentId);
        reply.setCommentuId(commentuId);
        reply.setContent(content);
        reply.setTopicId(topicId);
        reply.setUid(user.getUid());
        reply.setTime(DateUtil.now());
        return replyService.addReply(reply);
    }
}

