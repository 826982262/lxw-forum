package com.jfeat.forum.api.home;


import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileWriter;
import com.alibaba.fastjson.JSONObject;
import com.jfeat.forum.common.Constants;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.interceptor.userLogin;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.ImageUrl;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.util.ForumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 林学文
 * @Date 2022/2/14 12:41
 * @Version 1.0
 */
@Controller
@RequestMapping("/upload")
public class uploadFile {

    @Autowired
    private StandardServletMultipartResolver standardServletMultipartResolver;

    @userLogin
    @ResponseBody
    @RequestMapping(value = "/topicfile",method = RequestMethod.POST)
    public List<ImageUrl> uploadTopic(HttpServletRequest httpServletRequest){
//        HttpSession session = httpServletRequest.getSession();
//         User user=  (User) session.getAttribute("user");
//         int uid  = user.getUid();
        /*文件数量*/
        List<MultipartFile> multipartFiles = new ArrayList<>(20);
        JSONObject result = new JSONObject();
        List<ImageUrl> urls = new ArrayList<>();

        if (standardServletMultipartResolver.isMultipart(httpServletRequest)){
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iterator = multipartRequest.getFileNames();

            int total = 0;

            while (iterator.hasNext()){
                if (total>10){
                     ExceptionCast.cast(CommonCode.UPLOADFILEFAIL);
                }
                total += 1;
                MultipartFile file = multipartRequest.getFile(iterator.next());
                multipartFiles.add(file);
            }
        }
        if (CollectionUtils.isEmpty(multipartFiles)){ ExceptionCast.cast(CommonCode.INVALID_PARAM);}
        if (multipartFiles != null && multipartFiles.size()>10){
            ExceptionCast.cast(CommonCode.UPLOADFILEFAIL);
        }



        for (int i = 0;i<multipartFiles.size();i++){
            ImageUrl url = new ImageUrl();
            String fileName = multipartFiles.get(i).getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //生成文件名称通用方法
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Random r = new Random();
            StringBuilder tempName = new StringBuilder();
            tempName.append(sdf.format(new Date())).append(r.nextInt(100000000)).append(suffixName);
            String newFileName = tempName.toString();
//            File fileDirectory = new File(Constants.FILE_UPLOAD_DIC+uid+"/");
//            File destFile = new File(Constants.FILE_UPLOAD_DIC+uid+"/"+newFileName);

            File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
            File destFile = new File(Constants.FILE_UPLOAD_DIC+newFileName);

            try {
                if (!fileDirectory.exists()) {
                    if (!fileDirectory.mkdir()) {
                        throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                    }
                }
                multipartFiles.get(i).transferTo(destFile);
                url.setUrl (ForumUtils.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
                urls.add(url);
//                result.put("url",url);

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
               ExceptionCast.cast(CommonCode.UPLOADFAIL);
            }

        }

//        result.put("errno",0);

        return urls;
    }

    @ResponseBody
    @RequestMapping(value = "/txt",method = RequestMethod.POST)
    public ResponseResult uploadTxt(@RequestParam("txt_file") MultipartFile txt_file) throws IOException {


        if (txt_file.isEmpty()) {
            ExceptionCast.cast(CommonCode.UPLOADFAIL);
        }
        //创建输入输出流

        FileWriter fileWriter = new FileWriter("classpath:sent.txt");
        BufferedReader bufferedReader = null;
//        获取文件的输入流
        try {
            bufferedReader = IoUtil.getReader(txt_file.getInputStream(),"UTF-8");
            String str = null;
            while ((str = bufferedReader.readLine())!=null){
                fileWriter.append(str);
                fileWriter.append("\n");
            }
        } catch (IOException e) {
           ExceptionCast.cast(CommonCode.UPLOADFAIL);
        }finally {
            bufferedReader.close();
        }
        return new ResponseResult(CommonCode.UPLOADSUCCESS);
    }


        /*页面*/

}
