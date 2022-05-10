package com.jfeat.forum.api.home;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.interceptor.userLogin;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.ReplyService;
import com.jfeat.forum.services.gen.persistence.model.Reply;
import com.jfeat.forum.services.gen.persistence.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    @Resource
    ReplyService replyService;

    @userLogin
    @PostMapping(value = "/add")
    @ResponseBody
    public ResponseResult addReply(
            @RequestBody Reply reply,
//            @RequestParam("commentId")Long commentId,
//                                   @RequestParam("topicId")Long topicId,
//                                   @RequestParam("commentuid")Long commentuId,
//                                   @RequestParam("content")String content,
                                   HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (ObjectUtil.isNull(user)){
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        if (ObjectUtil.hasNull(reply.getTopicId(),reply.getCommentId(),reply.getCommentuId(),reply.getContent())){ExceptionCast.cast(CommonCode.ISNOTNULL);}

//        if (ObjectUtil.hasNull(commentId,topicId,commentuId,content)){ExceptionCast.cast(CommonCode.ISNOTNULL);}
//        Reply reply = new Reply();
//        reply.setCommentId(commentId);
//        reply.setCommentuId(commentuId);
//        reply.setContent(content);
//        reply.setTopicId(topicId);
        reply.setUid(user.getId());
        reply.setTime(DateUtil.date());
        return replyService.addReply(reply);
    }
}

