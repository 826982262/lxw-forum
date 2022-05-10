package com.jfeat.forum.api.home;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 验证码
 * @Author 林学文
 * @Date 2021/12/20 11:22
 * @Version 1.0
 */
@RestController
public class CodeController {



    @GetMapping(value = "/getcode")
    public Map<String,String> getCode(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session.getAttribute("Captcha")!=null){
            session.removeAttribute("Captcha");
        }
        /*生成验证码图片*/
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(135, 45, 4, 20);

        /*保存在session中*/
        session.setAttribute("Captcha",circleCaptcha);
        /*设置定时过期3分钟*/
        this.removeCode(session,"Captcha");
        /*返回base64流*/
        Map<String,String> map = new HashMap<>(10);
        map.put("data",circleCaptcha.getImageBase64());
//        return circleCaptcha.getImageBase64();
        return map;
    }


    /*设置验证码过期时间3分钟*/
    private void removeCode(final HttpSession session,final String attrName){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                session.removeAttribute(attrName);
                timer.cancel();
            }
        },1000*60*3);
    }

    @GetMapping(value = "captchaVerification")
    public boolean captchaVerifica(@RequestParam("captchaValue") String captchaCode, HttpServletRequest request){
        HttpSession session = request.getSession();
        boolean flag = false;
        CircleCaptcha circleCaptcha = (CircleCaptcha)session.getAttribute("Captcha");
        /*验证码错误*/
        if (circleCaptcha.verify(captchaCode)){
           flag = true;
        }
        return flag;
    }
}
