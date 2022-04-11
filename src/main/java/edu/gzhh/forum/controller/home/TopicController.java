package edu.gzhh.forum.controller.home;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import edu.gzhh.forum.common.Constants;
import edu.gzhh.forum.common.exception.ExceptionCast;
import edu.gzhh.forum.controller.home.vo.AddTopicVo;
import edu.gzhh.forum.entity.Topic;
import edu.gzhh.forum.entity.User;
import edu.gzhh.forum.interceptor.userLogin;
import edu.gzhh.forum.model.CommonCode;
import edu.gzhh.forum.model.QueryResult;
import edu.gzhh.forum.model.ResponseResult;
import edu.gzhh.forum.service.CommentService;
import edu.gzhh.forum.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    TopicService topicService;
    @Autowired
    CommentService commentService;
    @userLogin
    @ResponseBody
    @PostMapping(value = "/topic/add")
    public ResponseResult addTopic( @RequestBody AddTopicVo topicVo,
                                   HttpServletRequest request)
            throws UnsupportedEncodingException {
        Integer tagId = topicVo.getTagId();
        String tagName = topicVo.getTagName();
        String title = topicVo.getTitle();
        String content = topicVo.getContent();
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
        String ContentResult = HtmlUtil.filter(Content);
        /*去除content内容的html标签*/
        /*获取文本内容摘要前30个字符*/
        String summary = StrUtil.sub(HtmlUtil.cleanHtmlTag(Content),0,200) ;
        Long uid = user.getUid();
        Topic topic = new Topic();
        topic.setUid(uid);
        topic.setLId(tagId);
        topic.setLName(tag_Name);
        topic.setTime(DateUtil.now());
        topic.setTitle(Title);
        topic.setContent(ContentResult);
        topic.setSummary(summary);
        return topicService.addTopic(topic);

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
        return "home/topicContent";
    }
}

