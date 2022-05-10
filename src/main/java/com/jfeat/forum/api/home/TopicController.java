package com.jfeat.forum.api.home;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.jfeat.forum.api.home.vo.AddTopicVo;
import com.jfeat.forum.common.Constants;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.interceptor.userLogin;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.CommentOverModelService;
import com.jfeat.forum.services.domain.service.TopicOverModelService;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import com.jfeat.forum.services.gen.persistence.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxw
 * @since 2022-02-12
 */
@Controller
//@RequestMapping("/topic")
public class TopicController {

    @Resource
    TopicOverModelService topicService;
    @Resource
    CommentOverModelService commentService;
    @userLogin
    @ResponseBody
    @PostMapping(value = "/topic/add")
    public ResponseResult addTopic(@RequestBody AddTopicVo topic,
                                   HttpServletRequest request)
            throws UnsupportedEncodingException {
        Integer tagId = topic.getTagId();
        String tagName = topic.getTagName();
        String title = topic.getTitle();
        String content = topic.getContent();
        /*判断参数非空*/
        if (ObjectUtil.hasEmpty(tagId,tagName,title,content)){
            ExceptionCast.cast(CommonCode.ISNOTNULL);
        }
        HttpSession session = request.getSession();
        /*解码*/
        String tag_Name= URLDecoder.decode(tagName,"UTF-8");
        String Title = URLDecoder.decode(title,"UTF-8");
        String Content = URLDecoder.decode(content,"UTF-8");
        User user = (User) session.getAttribute("user");
        if (ObjectUtil.isNull(user)){
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }

        /*过滤xss攻击*/
//        String ContentResult = HtmlUtil.filter(Content);
        /*去除content内容的html标签*/
        /*获取文本内容摘要前30个字符*/
        String summary = StrUtil.sub(HtmlUtil.cleanHtmlTag(Content),0,200) ;
        Long uid = user.getId();
        Topic topics = new Topic();
        topics.setContentFilter(HtmlUtil.cleanHtmlTag(Content));
        topics.setUid(uid);
        topics.setlId(tagId);
        topics.setlName(tag_Name);
        topics.setTime(DateUtil.date());
        topics.setTitle(Title);

        topics.setContent(Content);
        topics.setSummary(summary);
        return topicService.addTopic(topics);

    }


    @RequestMapping("/thread")
    public String topicContent(@RequestParam("topicId") Long topicId,
                               @RequestParam(value = "page",required=false)Integer page,
                               HttpServletRequest request){
        if (page == null){
            page = 1;
        }
        if (topicId ==null){ExceptionCast.cast(CommonCode.ISNOTNULL);}
//        TopicContentPO topic = topicCommentservice.findTopicById(topicId);
        if (page == 1) {
            Topic topic = topicService.selectTopicByTopicId(topicId);
            request.setAttribute("topic", topic);
        }
        QueryResult result = commentService.selectCommentByTopicId(topicId,(page-1)* Constants.TOPIC_NUM,Constants.TOPIC_NUM);
        /*总条数*/

        long total = result.getTotal();
        /*总页数*/
        int totalPage = (int) Math.ceil(1.0*total/Constants.TOPIC_NUM);
        request.setAttribute("comments",result.getList());
        request.setAttribute("total",total);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("page",page);
        return "reception/topicContent";
    }
}

