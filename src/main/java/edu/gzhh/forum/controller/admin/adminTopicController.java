package edu.gzhh.forum.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import edu.gzhh.forum.common.exception.ExceptionCast;
import edu.gzhh.forum.model.response.CommonCode;
import edu.gzhh.forum.model.response.QueryResult;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.service.TopicService;
import edu.gzhh.forum.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author 林学文
 * @Date 2022/3/5 19:39
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/topicmanage")
public class adminTopicController {

    @Autowired
    TopicService topicService;

    @RequestMapping("")
    public String topicManage(ModelMap modelMap){
        modelMap.addAttribute("path","topicmanage");
        return "/admin/topicManage";
    }
    @ResponseBody
    @RequestMapping("/list")
    public QueryResult selectList(@RequestParam Map<String,Object> params
                                  ,@RequestParam(value = "audit",required = false) Integer audit){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        if (audit==4){audit=null;}
//        return topicService.list();
        return topicService.selectTopicByAudit(pageUtil,audit);
    }

    @ResponseBody
    @RequestMapping("/check/{audit}")
    public ResponseResult checkSensitivity(@PathVariable("audit")Integer audit, @RequestBody List<Long> Ids){

        if (ObjectUtil.hasEmpty(Ids,audit)){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return topicService.UpdateAuditByIds(Ids, audit);
    }

    @ResponseBody
    @RequestMapping("/doTop/{istop}")
    public ResponseResult doTop(@PathVariable("istop")Integer istop, @RequestBody List<Long> Ids){

        if (ObjectUtil.hasEmpty(Ids,istop)){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        return topicService.UpdateIsTopByIds(Ids, istop);
    }

}
