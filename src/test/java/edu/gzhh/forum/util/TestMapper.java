package edu.gzhh.forum.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import edu.gzhh.forum.common.SensitiveWordFilterInit;
import edu.gzhh.forum.entity.Label;
import edu.gzhh.forum.entity.Sensitivity;
import edu.gzhh.forum.entity.Topic;
import edu.gzhh.forum.mapper.SensitivityMapper;
import edu.gzhh.forum.mapper.TopicMapper;
import edu.gzhh.forum.mapper.UserMapper;
import edu.gzhh.forum.service.LabelService;
import edu.gzhh.forum.service.SensitivityService;
import edu.gzhh.forum.service.TopicService;
import edu.gzhh.forum.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author 林学文
 * @Date 2021/12/17 10:14
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapper {
    @Autowired
    UserService userService;
@Autowired
UserMapper userMapper;
    @Autowired
    LabelService labelService;
    @Autowired
    TopicService topicService;
    @Autowired
    TopicMapper topicMapper;
@Autowired
SensitivityService sensitiveService;
@Autowired
SensitivityMapper sensitiveMapper;
    @Test
    public void testService(){
        System.out.println(userService.exitByAccount("林学文"));
    }

    @Test
    public  void testlabelservice() {
        List<Label> list =  labelService.getAllLabel();
       list.forEach(System.out::println);

    }

    @Test
    public void test(){
//        List<TopicPo> topicList = topicService.selectTopicByLabelId(1);
//        topicList.forEach(System.out::println);
    }
      @Test
    public void test01(){
//    TopicContentPO contentPO =topicMapper.selectTopicByTopicId((long)32);
//            topicService.selectTopicByTopicId();
//        QueryWrapper queryWrapper = new QueryWrapper();

//        List<User> users= userMapper.selectList(queryWrapper);

//       userRepository.saveAll(users);
    }
    @Test
    public void test02() throws Exception {
        SensitiveWordFilterInit.InitSensitive("classpath:sent.txt");
        //正文
        String text = "我有一颗大土豆，刚出锅的";
        String   matchAll = SensitiveWordFilterInit.getSensitiveWords(text);
        System.out.println(matchAll);
//        WordTree tree = new WordTree();
//        tree.addWord("大");
//        tree.addWord("大土豆");
//        tree.addWord("土豆");
//        tree.addWord("刚出锅");
//        tree.addWord("出锅");

        // 匹配到【大】，由于到最长匹配，因此【大土豆】接着被匹配，由于不跳过已经匹配的关键词，土豆继续被匹配
// 【刚出锅】被匹配，由于不跳过已经匹配的词，【出锅】被匹配
//        List<String>   matchAll = tree.matchAll(text, -1, true, true);
//        System.out.println(matchAll);

    }
    @Test
    public void text04(){
      List<Topic> topicList =   topicService.selectNotCheckTopic(0);
//      for (Topic topic : topicList){
//          topic.setCheck(1);
//      }
//      boolean flag = topicService.updateBatchById(topicList);
//        System.out.println(flag);
        System.out.println(topicList);
    }
    @Test
    public void text05(){
        Sensitivity sensitive = new Sensitivity();
//        sensitive.setId(1L);
        sensitive.setCid(12L);
        sensitive.setContent("sdas");
        sensitive.setSenWord("sda");
        sensitive.setType("topic");
        sensitive.setAudit(0);
//        sensitiveService.saveSensitiveList(sensitive);
        sensitiveMapper.insert(sensitive);
    }

    @Test
    public void text06(){
        RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
        System.out.println( "空闲内存"+FileUtil.readableFileSize(runtimeInfo.getFreeMemory()));
        System.out.println("最大内存"+ FileUtil.readableFileSize(runtimeInfo.getMaxMemory()));
        System.out.println("运行时间"+runtimeInfo.getRuntime());
        System.out.println("已用内存"+FileUtil.readableFileSize(runtimeInfo.getTotalMemory()));
        System.out.println("可用内存"+FileUtil.readableFileSize(runtimeInfo.getUsableMemory()));
        System.out.println("操作系统："+SystemUtil.getOsInfo().getName());
        System.out.println(SystemUtil.getHostInfo());

    }
    @Test
    public void text07(){
        boolean flag = "3".equals(3);
        System.out.println(flag);
    }
    @Test
    public void text01(){
        FileWriter fileWriter = new FileWriter("classpath:sent.txt");
        fileWriter.append("11");
    }

}
