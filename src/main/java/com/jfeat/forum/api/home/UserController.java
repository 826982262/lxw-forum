package com.jfeat.forum.api.home;


import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.codec.Base32;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.jfeat.forum.common.Constants;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.interceptor.userLogin;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.CommentOverModelService;
import com.jfeat.forum.services.domain.service.LabelService;
import com.jfeat.forum.services.domain.service.TopicOverModelService;
import com.jfeat.forum.services.domain.service.UserService;
import com.jfeat.forum.services.gen.crud.model.UserModel;
import com.jfeat.forum.services.gen.persistence.model.Comment;
import com.jfeat.forum.services.gen.persistence.model.Label;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import com.jfeat.forum.services.gen.persistence.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lxw
 * @since 2021-12-16
 */
@Controller
@RequestMapping("/forum")
@Api("forum")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    CommentOverModelService commentService;
    @Resource
    TopicOverModelService topicService;
    @Resource
    LabelService labelService;

    @userLogin
    @ResponseBody
    @PostMapping(value = "/comment/add")
    @ApiOperation(value = "添加文章",response = ResponseResult.class)
    public ResponseResult addComment(@RequestParam("topicId")Long topicId,
                                     @RequestParam("content")String content,
                                    @RequestParam("topicUid")Long topicUid,
                                    HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (ObjectUtil.isNull(user)){
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);}
        Comment comment = new Comment();
        if (ObjectUtil.hasNull(topicId,content,topicUid)){
            ExceptionCast.cast(CommonCode.ISNOTNULL);
        }


        comment.setToUid(topicUid);
        comment.setTopicId(topicId);
        /*过滤xss攻击*/
        comment.setContent(content);
       comment.setUid(user.getId());
        comment.setPostTime(DateUtil.date());
        return commentService.addComment(comment);
    }

    @ResponseBody
    @PostMapping(value = "/register")
    public ResponseResult register(@RequestBody UserModel registerUserVo, HttpServletRequest request) throws UnsupportedEncodingException {
        HttpSession session = request.getSession();
        CircleCaptcha circleCaptcha = (CircleCaptcha)session.getAttribute("Captcha");
        /*验证码错误*/
        if (!circleCaptcha.verify(registerUserVo.getCaptchaValue())){
            ExceptionCast.cast(CommonCode.CODE_ERROR);
        }
        User user = new User();
        user.setAccount(registerUserVo.getAccount());
        user.setUname(URLDecoder.decode(registerUserVo.getUname(),"UTF-8"));
        user.setPassword(registerUserVo.getPassword());
        user.setCreateTime(DateUtil.date());
        user.setFlag(2);
        user.setHeadUrl("/img/photo.jpeg");
        return userService.saveUser(user);
//        return mongoUserService.addUser(user);
    }



    @GetMapping(value = "/exitAccount")
    public boolean exitName(@RequestParam("account") String account){
        return userService.exitByAccount(account);
    }



    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody Map<String,Object> map, HttpServletRequest request){

        HttpSession session = request.getSession();

        String jumpUri = (String) map.get("jumpUri");
        String captchaCode = (String) map.get("captchaValue");
        String type = (String) map.get("type");
        String account =new String();
        if (type ==null ||StrUtil.isEmpty(captchaCode))
        {
            ExceptionCast.cast(CommonCode.ISNOTNULL);
        }else {
            switch (type){
                case "10":account = (String) map.get("account");
                    break;
                case "20": account = (String)map.get("mobile");
                    break;

            }
        }


       String password = (String)map.get("password");

        if (StrUtil.isEmpty(account)||StrUtil.isEmpty(password)){ExceptionCast.cast(CommonCode.ISNOTNULL);}

        CircleCaptcha circleCaptcha = (CircleCaptcha)session.getAttribute("Captcha");
        /*验证码错误*/
        if (!circleCaptcha.verify(captchaCode)){
            ExceptionCast.cast(CommonCode.CODE_ERROR);
        }
        ResponseResult result = userService.userLogin(type,account,password,request);
        System.out.println(jumpUri);
        if (jumpUri!=null){result.setJumpUri(Base32.decodeStr(jumpUri));}
//        ResponseResult result = mongoUserService.userLogin(type,account,password,request);
        return result;

    }
    @userLogin
    @ResponseBody
    @RequestMapping("loginout")
    public ResponseResult loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        Object object = session.getAttribute("user");
        if (ObjectUtil.isNull(object)){
            ExceptionCast.cast(CommonCode.NOTLOGIN);
        }
        session.removeAttribute("user");
        return new ResponseResult(CommonCode.LOGOUTSUCCESS);
    }


    @userLogin
    @RequestMapping("/user/home")
    public String userhome(){
        return "home/home";
    }


    @RequestMapping("/user/editTopic")
    public String editTopic(HttpServletRequest request,@RequestParam("topicId")Long topicId){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (ObjectUtil.isNull(user)){ExceptionCast.cast(CommonCode.UNAUTHENTICATED);}
        if (ObjectUtil.isNull(topicId)){ExceptionCast.cast(CommonCode.ISNOTNULL);}
        Topic topic = topicService.selectTopicByTopicId(topicId);
        if (!topic.getUid().equals(topic.getUid())){ExceptionCast.cast(CommonCode.UNAUTHORISE);}
        List<Label> labelList = labelService.getAllLabel();
        request.setAttribute("topic",topic);
        request.setAttribute("tags",labelList);

        return "reception/editTopic";
    }
    @userLogin
    @ResponseBody
    @PostMapping(value = "/user/doEditTopic")
    public ResponseResult addTopic( @RequestBody Map<String,Object> map,
                                    HttpServletRequest request) throws UnsupportedEncodingException {

//        @RequestParam("tagId") Integer tagId,
//        @RequestParam("tagName") String tagName,
//        @RequestParam("title") String title,
//        @RequestParam("content") String content,

        Long topicId = Long.valueOf((String) map.get("tId"));
        Integer labelId = Integer.parseInt((String) map.get("lId"));
        String labelName = (String) map.get("lName");
        String title =(String) map.get("title");
        String content = (String)map.get("content");
        if (ObjectUtil.hasEmpty(topicId,labelId,labelName,title,content)){
            ExceptionCast.cast(CommonCode.ISNOTNULL);
        }
        HttpSession session = request.getSession();
        /*解码*/
        String tag_Name= URLDecoder.decode(labelName,"UTF-8");
        String Title = URLDecoder.decode(title,"UTF-8");
        String Content = URLDecoder.decode(content,"UTF-8");
        User user = (User) session.getAttribute("user");
        if (ObjectUtil.isNull(user)){
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
        /*过滤文本防止xss*/
//        String xssHtml = "<alert></alert>";
//        HtmlUtil.removeAllHtmlAttr(xssHtml,Content);



        /*去除content内容的html标签*/
        /*获取文本内容摘要前30个字符*/
        String summary = StrUtil.sub(HtmlUtil.cleanHtmlTag(Content),0,200) ;

        Long uid = user.getId();
        Topic topic = new Topic();
        topic.setId(topicId);
        topic.setUid(uid);
        topic.setlId(labelId);
        topic.setlName(tag_Name);
//        topic.setTime(DateUtil.now());
        topic.setTitle(Title);
        topic.setContent(Content);
        topic.setSummary(summary);

        return topicService.updateTopic(topic);
//        return topicCommentservice.addTopic(topic);
    }
    @userLogin
    @GetMapping("/user/mytopic")
    public String mytopic(@RequestParam(value = "page",required=false)Integer page
                             , HttpServletRequest request
                            , ModelMap model){
        if (page == null){
            page=1;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        QueryResult result = topicService.selectTopicByUserId(user.getId(),(page-1)* Constants.TOPIC_NUM, Constants.TOPIC_NUM);
        long total = result.getTotal();
        /*总页数*/
        int totalPage = (int) Math.ceil(1.0*total/Constants.TOPIC_NUM);
        model.addAttribute("topicLists",result.getList());
        request.setAttribute("total",total);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("page",page);
        model.addAttribute("path","mytopic");
        return "reception/mytopic";
    }


}

