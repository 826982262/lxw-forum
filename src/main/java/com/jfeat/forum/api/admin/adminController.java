package com.jfeat.forum.api.admin;

import cn.hutool.core.io.FileUtil;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 林学文
 * @Date 2022/2/24 19:37
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class adminController {
    @RequestMapping("/index")
//    @adminLogin
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

}
