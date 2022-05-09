package edu.gzhh.forum.controller.home;


import cn.hutool.core.util.ObjectUtil;
import edu.gzhh.forum.common.Constants;
import edu.gzhh.forum.entity.Label;
import edu.gzhh.forum.model.QueryResult;
import edu.gzhh.forum.service.LabelService;
import edu.gzhh.forum.service.TopicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 页面访问控制类
 *
 * @author lxw
 * @since 2021-12-09
 */
@Controller
//@RequestMapping("/")
public class IndexController {
    private static final Logger logger = LogManager.getLogger(IndexController.class);

    @Autowired
    TopicService topicService;

    @Autowired
    LabelService labelService;




    @RequestMapping(value = {"/index","/",""})
    public String indexPage(@RequestParam(value = "tagId",required=false)Integer tagId,
                            @RequestParam(value = "page",required=false)Integer page,
                            @RequestParam(value = "keyword",required=false)String keyword,
                            HttpServletRequest request){
        if (ObjectUtil.isNull(tagId)) {
            tagId = 1;
        }
        if (page == null){
            page=1;
        }
        if (ObjectUtil.isNotNull(keyword)){
            request.setAttribute("keyword",keyword);
        }
//        ThreadUtil.execAsync()
        /*功能块*/
        List<Label> labelList = labelService.getAllLabel();
        /*话题*/
//
        QueryResult topicLists =  topicService.selectTopicByLabelId(tagId,(page-1)*Constants.TOPIC_NUM, Constants.TOPIC_NUM,keyword);
        /*总条数*/
        long total = topicLists.getTotal();
        /*总页数*/
        int totalPage = (int) Math.ceil(1.0*total/Constants.TOPIC_NUM);
        request.setAttribute("topicLists",topicLists.getList());
        request.setAttribute("tags",labelList);
        request.setAttribute("tagId",tagId);
        request.setAttribute("page",page);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("total",total);
        return "home/index";

    }
    @RequestMapping("/register")
    public String register(){

        return "home/register";
    }




    @RequestMapping("/login")
    public String login(){
        return "home/login";
    }


    @RequestMapping("/context")
    public String context(){

        return "home/context";
    }



}

