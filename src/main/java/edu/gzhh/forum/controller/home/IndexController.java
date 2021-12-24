package edu.gzhh.forum.controller.home;


import edu.gzhh.forum.model.response.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * 页面访问控制类
 *
 * @author lxw
 * @since 2021-12-09
 */
@Controller
//@RequestMapping("/")
public class IndexController {

//    @PostMapping("/regist")
//    public ResponseResult regist(){
//
//    }
    @RequestMapping("/")
    public String hello(){
        return "home/register";
    }

    @RequestMapping("/index")
    public String index(){
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
}

