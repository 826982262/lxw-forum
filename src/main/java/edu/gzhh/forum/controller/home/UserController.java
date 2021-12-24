package edu.gzhh.forum.controller.home;


import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.server.HttpServerRequest;
import edu.gzhh.forum.common.exception.ExceptionCast;
import edu.gzhh.forum.controller.home.vo.LoginUserVo;
import edu.gzhh.forum.controller.home.vo.RegisterUserVo;
import edu.gzhh.forum.entity.User;
import edu.gzhh.forum.model.response.CommonCode;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping(value = "/register")
    public ResponseResult register(@RequestBody RegisterUserVo registerUserVo, HttpServletRequest request){
        HttpSession session = request.getSession();
        CircleCaptcha circleCaptcha = (CircleCaptcha)session.getAttribute("Captcha");
        /*验证码错误*/
        if (!circleCaptcha.verify(registerUserVo.getCode())){
            ExceptionCast.cast(CommonCode.CODE_ERROR);
        }
        User user = new User(registerUserVo.getUname(),registerUserVo.getPassword(),
                registerUserVo.getPhone(),registerUserVo.getEmail(),registerUserVo.getSex(),2,"/upload/");
        return userService.saveUser(user);
    }

    @ResponseBody
    @GetMapping(value = "/exitname")
    public boolean exitName(@RequestParam("name") String name){
        return userService.exitByName(name);
    }


    @ResponseBody
    @PostMapping(value = "/login")
    public ResponseResult login(@RequestBody LoginUserVo loginUserVo, HttpServletRequest request){
        String uname = loginUserVo.getUname();
        String password = loginUserVo.getPassword();
        if (StrUtil.isEmpty(uname)||StrUtil.isEmpty(password)){ExceptionCast.cast(CommonCode.ISNOTNULL);}
        HttpSession session = request.getSession();
        CircleCaptcha circleCaptcha = (CircleCaptcha)session.getAttribute("Captcha");
        /*验证码错误*/
        if (!circleCaptcha.verify(loginUserVo.getCode())){
            ExceptionCast.cast(CommonCode.CODE_ERROR);
        }
        return userService.userLogin(uname,password);
    }


}

