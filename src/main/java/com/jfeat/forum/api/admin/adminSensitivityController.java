package com.jfeat.forum.api.admin;

import cn.hutool.core.util.ObjectUtil;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.interceptor.adminLogin;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.SensitivityService;

import com.jfeat.forum.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author 林学文
 * @Date 2022/3/7 11:41
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin/sensitivity")
public class adminSensitivityController {

    @Resource
    SensitivityService sensitivityService;

    @adminLogin
    @RequestMapping("/updateword")
    public String updateWord(ModelMap modelMap){
        modelMap.addAttribute("path","updateword");
        return "admin/updateword";
    }
    @adminLogin
    @RequestMapping("")
    public String sensitivity(ModelMap modelMap,@RequestParam(value = "audit",required = false)Integer audit){
        modelMap.addAttribute("path","sensitivity");
        return "admin/sensitivityManage";
    }

    @adminLogin
    @ResponseBody
    @RequestMapping("/check/{audit}")
    public ResponseResult checkSensitivity(@PathVariable("audit")Integer audit, @RequestBody List<Long> Ids){

       if (ObjectUtil.hasEmpty(Ids,audit)){
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
       }
        return sensitivityService.UpdateAuditByIds(Ids, audit);
    }

    @adminLogin
    @ResponseBody
    @RequestMapping("/list")
    public QueryResult selectList(@RequestParam Map<String,Object> params,
                                  @RequestParam(value = "audit",required = false) Integer audit){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        if (audit == 4){audit=null;}
        PageQueryUtil pageUtil = new PageQueryUtil(params);


        return sensitivityService.selectSensitiveList(pageUtil,audit);
    }
}
