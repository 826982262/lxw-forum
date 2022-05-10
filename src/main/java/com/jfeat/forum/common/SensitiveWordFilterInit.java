package com.jfeat.forum.common;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.dfa.WordTree;

import java.util.List;

/**
 * @Author 林学文
 * @Date 2022/2/24 20:46
 * @Version 1.0
 */

public class SensitiveWordFilterInit {

    private final static WordTree wordTree= new WordTree();;


        /*初始化敏感词库*/
       public static void InitSensitive(String FilePath) throws Exception {
           try {
               /*"classpath:sent.txt"*/
               FileReader reader = new FileReader(FilePath);
               /*读取敏感词*/
               List<String> sensitive = reader.readLines();
               wordTree.addWords(sensitive);
           }catch (Exception e){
              throw new Exception("文件读取失败!!");
           }
       }


    /*获取匹配*/
    public static String getSensitiveWords(String content){
        List<String>   matchAll = wordTree.matchAll(content, -1, true, true);
        if (matchAll.isEmpty()){return null;}
        String result = String.join(",",matchAll);
        return result;
    }

}
