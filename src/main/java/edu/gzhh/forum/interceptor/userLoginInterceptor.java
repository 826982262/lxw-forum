package edu.gzhh.forum.interceptor;

import cn.hutool.core.codec.Base32;
import edu.gzhh.forum.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Author 林学文
 * @Date 2022/3/4 16:45
 * @Version 1.0
 */
@Component
public class userLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*不是映射方法直接通过*/
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        userLogin userLogin = method.getAnnotation(edu.gzhh.forum.interceptor.userLogin.class);

        if (userLogin !=null){
            User user = (User) request.getSession().getAttribute("user");
            if (user!=null&&user.getFlag()!=3){
                return true;
            }else if (null==user){
                String uri = request.getRequestURI();
                if (request.getQueryString()!=null&&!"".equals(request.getQueryString())){uri+="?"+request.getQueryString();}

                /*没有登录请求转发到登录页*/
                response.sendRedirect("/login?jumpUri="+ Base32.encode(uri));
//                request.getRequestDispatcher("/login").forward(request,response);
                return false;
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
