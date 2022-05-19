package com.jfeat.forum.api.admin;

import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.interceptor.adminLogin;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author 林学文
 * @Date 2022/2/24 19:37
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class adminController {

    @Resource
    UserService userService;
    @RequestMapping("/index")
    @adminLogin
    public String adminIndex(ModelMap model, HttpServletRequest request){
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        /*操作系统*/

        String os = SystemUtil.getOsInfo().getName();
        /*最大内存*/
        String maxMemory = FileUtil.readableFileSize(runtimeInfo.getMaxMemory());
        /*空闲内存*/
        String freeMemory=  FileUtil.readableFileSize(runtimeInfo.getFreeMemory());
        /*已用内存*/
        String totalMemory =  FileUtil.readableFileSize(runtimeInfo.getTotalMemory());
        /*可用内存*/
        String usableMemory = FileUtil.readableFileSize(runtimeInfo.getUsableMemory());
        model.addAttribute("os",os);
        model.addAttribute("maxMemory",maxMemory);
        model.addAttribute("freeMemory",freeMemory);
        model.addAttribute("totalMemory",totalMemory);
        model.addAttribute("usableMemory",usableMemory);
        model.addAttribute("path","index");
       

        return "admin/index";
    }
    @RequestMapping("/login")
    public String login(){
        return "admin/login";
    }
    @ResponseBody
    @PostMapping("/login.do")
    public ResponseResult loginDo(@RequestBody Map<String,Object> map, HttpServletRequest request){
        HttpSession session = request.getSession();

        String captchaCode = (String) map.get("verifyCode");
        String account = (String) map.get("account");;
        String password = (String)map.get("password");

        if (StrUtil.isEmpty(account)||StrUtil.isEmpty(password)){
            ExceptionCast.cast(CommonCode.ISNOTNULL);}

        CircleCaptcha circleCaptcha = (CircleCaptcha)session.getAttribute("Captcha");
        /*验证码错误*/
        if (!circleCaptcha.verify(captchaCode)){
            ExceptionCast.cast(CommonCode.CODE_ERROR);
        }
        return userService.adminLogin(account,password,request);
    }

}
