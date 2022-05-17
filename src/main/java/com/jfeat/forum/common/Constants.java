package com.jfeat.forum.common;

/**
 * 常量配置
 * @Author 林学文
 * @Date 2022/2/14 14:08
 * @Version 1.0
 */
public class Constants {
    public final static String FILE_UPLOAD_DIC = "/mydata/app/admin/upload/";//上传文件的默认url前缀，根据部署设置自行修改
    public final static String FILE_UPLOAD_HEAD = "/mydata/app/admin/upload/avatar/";//上传文件的默认url前缀，根据部署设置自行修改
//    public final static String FILE_UPLOAD_HEAD = "E:/upload//avatar/";
//        public final static String FILE_UPLOAD_DIC = "E:/upload/";//上传文件的默认url前缀，根据部署设置自行修改
    public final static Integer TOPIC_NUM = 10;
    public final static Integer TOPIC_NOT_CHECK = 0;
    public final static Integer TOPIC_PASS = 1;
    public final static Integer PEOPLE_CHECK = 2;

    public final static Integer TOPIC_NOT_PASS = 3;
    public final static Integer TOPIC_UNHIDE = 4;

}
