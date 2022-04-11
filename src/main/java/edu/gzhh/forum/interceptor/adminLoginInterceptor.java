package edu.gzhh.forum.interceptor;

import edu.gzhh.forum.common.exception.ExceptionCast;
import edu.gzhh.forum.entity.User;
import edu.gzhh.forum.model.CommonCode;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author 林学文
 * @Date 2022/3/4 12:34
 * @Version 1.0
 */
@Component
public class adminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*不是映射方法直接通过*/
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        adminLogin adminLogin = method.getAnnotation(edu.gzhh.forum.interceptor.adminLogin.class);

        if (adminLogin !=null){
            User user = (User) request.getSession().getAttribute("user");
            if (user!=null&&user.getFlag()==1){
            return true;
            }else {
                ExceptionCast.cast(CommonCode.UNAUTHORISE);
            }
        }
        return true;


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
