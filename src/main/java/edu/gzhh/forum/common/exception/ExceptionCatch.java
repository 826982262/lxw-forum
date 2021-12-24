package edu.gzhh.forum.common.exception;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.ImmutableMap;
import edu.gzhh.forum.model.response.CommonCode;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 林学文
 * @Date 2021/12/17 11:48
 * @Version 1.0
 */
/*统一异常捕获类*/
@RestControllerAdvice
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    /*定义map，配置异常类型所对应的错误代码*/
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    /*定义map的builder对象，去构建ImmutableMap*/
    protected static ImmutableMap.Builder<Class<?extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    /*捕获CustomException异常*/
    @ExceptionHandler(CustomException.class)
    public Object customException(CustomException customException, HttpServletRequest request){
        //记录日志
        LOGGER.error("catch exception:{}",customException.getMessage());
        ResultCode resultCode = customException.getResultCode();
        //检查请求是否为ajax, 如果是 ajax 请求则返回 Result json串, 如果不是 ajax 请求则返回 error 视图
        String contentTypeHeader = request.getHeader("Content-Type");
        String acceptHeader = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        if (!ObjectUtil.hasEmpty(contentTypeHeader,acceptHeader) && contentTypeHeader.contains("application/json")
                && acceptHeader.contains("application/json")
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)
        ) {
            return new ResponseResult(resultCode);
        } else {
            ModelAndView modelAndView = new ModelAndView();

            modelAndView.addObject("url",request.getRequestURI());
            modelAndView.addObject("resultCode", resultCode);
            modelAndView.addObject("message",customException.getMessage());
            modelAndView.setViewName("error/error");
            return modelAndView;
        }
    }

    //捕获Exception此类异常
    @ExceptionHandler(Exception.class)
    public Object exception(Exception exception ,HttpServletRequest request){
        //记录日志
        LOGGER.error("catch exception:{}",exception.getMessage());
        //检查请求是否为ajax, 如果是 ajax 请求则返回 Result json串, 如果不是 ajax 请求则返回 error 视图
        String contentTypeHeader = request.getHeader("Content-Type");
        String acceptHeader = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        if(EXCEPTIONS == null){
            EXCEPTIONS = builder.build();//EXCEPTIONS构建成功
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());


        if (!ObjectUtil.hasEmpty(contentTypeHeader,acceptHeader) && contentTypeHeader.contains("application/json")
                && acceptHeader.contains("application/json")
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith)
        ) {
            if(resultCode !=null){
                return new ResponseResult(resultCode);
            }else{
                //返回99999异常
                return new ResponseResult(CommonCode.SERVER_ERROR);
            }
        } else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("url",request.getRequestURI());
            if (resultCode !=null){modelAndView.addObject("resultCode", resultCode);}else {
                modelAndView.addObject("resultCode",CommonCode.SERVER_ERROR);
            }

            modelAndView.addObject("message",exception.getMessage());
            modelAndView.setViewName("error/error");
            return modelAndView;

        }


        }





}
