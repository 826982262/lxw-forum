package com.jfeat.forum.api.admin;


import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.UserService;
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
 * @Date 2022/3/6 16:31
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class adminUserController {

    @Resource
    UserService userService;
    @RequestMapping("/usermanage")
    public String userManage(ModelMap modelMap){
        modelMap.addAttribute("path","usermanage");

        return "admin/userManage";
    }
    @RequestMapping(value = "/users/list",method = RequestMethod.GET)
    @ResponseBody
    public QueryResult selectUserList(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);

        return userService.selectUserList(pageUtil);
    }

    @RequestMapping("/users/lock/{lockStatus}")
    @ResponseBody
    public ResponseResult lockUser(@PathVariable("lockStatus")Integer lockStatus, @RequestBody List<Long> Ids){
        if (Ids.size()<1&&!(lockStatus.equals(2)||lockStatus.equals(3))){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }


        return userService.updateUsersFlag(Ids,lockStatus);
    }
    @RequestMapping("profile")
    public String profile(ModelMap modelMap){
        modelMap.addAttribute("path","profile");
        return "admin/profile";
    }


}
