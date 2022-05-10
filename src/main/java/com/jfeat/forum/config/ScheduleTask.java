package com.jfeat.forum.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.jfeat.forum.common.SensitiveWordFilterInit;
import com.jfeat.forum.services.domain.service.CommentOverModelService;
import com.jfeat.forum.services.domain.service.ReplyService;
import com.jfeat.forum.services.domain.service.SensitivityService;
import com.jfeat.forum.services.domain.service.TopicOverModelService;
import com.jfeat.forum.services.gen.persistence.model.Comment;
import com.jfeat.forum.services.gen.persistence.model.Reply;
import com.jfeat.forum.services.gen.persistence.model.Sensitivity;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 林学文
 * @Date 2022/2/28 21:35
 * @Version 1.0
 */
@Component
@Configuration
@EnableScheduling
@EnableAsync//开启异步支持
public class ScheduleTask {


    @Resource
    TopicOverModelService topicService;

    @Resource
    CommentOverModelService commentService;
    @Resource
    ReplyService replyService;
    @Resource
    SensitivityService sensitiveService;
    /*15分钟执行一次*/
    @Scheduled(fixedRate = 1000*60*15)
    private void checkTopicSensitiveScheduleTask(){
        System.out.println("开始自动审核文章定时任务");
        List<Topic> topicList =   topicService.selectNotCheckTopic(0);
        /*敏感文章集合*/
        List<Sensitivity> sensitiveList = new ArrayList<>();
        /*存在敏感的topic*/
        List<Long> sensitiveTopicIds = new ArrayList<>();
        /*不存在敏感的topic*/
        List<Long> noSensitiveTopicIds = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(topicList)){
            try {
                SensitiveWordFilterInit.InitSensitive("classpath:sent.txt");
            }catch (Exception e){
                System.out.println(e);
            }

            for (Topic topic :topicList){
              String content =   HtmlUtil.cleanHtmlTag(topic.getContent());
                String result = SensitiveWordFilterInit.getSensitiveWords(content);

                if (StrUtil.isNotEmpty(result)){

                    Sensitivity sensitive = new Sensitivity();
                    sensitive.setCid(topic.getId());
                    sensitive.setType("topic");
                    sensitive.setTopicId(topic.getId());
                    sensitive.setContent(content);
                    sensitive.setCreateTime(DateUtil.date());
                    sensitive.setSenWord(result);
                    sensitive.setAudit(0);

                    /*有敏感字符存入审核表 设置topic的audit为人工审核状态值1*/
//                    sensitiveService.saveSensitiveList(sensitive);
                    sensitiveTopicIds.add(topic.getId());
                    sensitiveList.add(sensitive);
                }else {
                    noSensitiveTopicIds.add(topic.getId());
                }
                        }

            if (sensitiveList.isEmpty()){
                System.out.println("无敏感文章");
            }else {
                sensitiveService.bulkAppendMasterList(sensitiveList);
                /*有敏感字符存入审核表 设置topic的audit为人工审核状态值1*/
                topicService.updateAuditResult(sensitiveTopicIds,1);
            }
            topicService.updateAuditResult(noSensitiveTopicIds,2);

                        }else {System.out.println("无需要审核文章");}
//                        /*查询查询未审核的topic*/

                        System.out.println("审核结束"+ DateUtil.now());
                        }
/*2分钟*/
@Scheduled(fixedRate = 1000*60*10)
private void checkCommentSensitiveScheduleTask(){
    System.out.println("开始自动审核评论定时任务");
    List<Comment> commentLists =   commentService.selectCheckComment(0);
    /*敏感Comment集合*/
    List<Sensitivity> sensitiveList = new ArrayList<>();
    /*存在敏感的Comment*/
    List<Long> sensitiveCommentIds = new ArrayList<>();
    /*不存在敏感的Comment*/
    List<Long> noSensitiveCommentIds = new ArrayList<>();
    if (ObjectUtil.isNotEmpty(commentLists)){
        try {
            SensitiveWordFilterInit.InitSensitive("classpath:sent.txt");
        }catch (Exception e){
            System.out.println(e);
        }

        for (Comment comment :commentLists){
            String content =   HtmlUtil.cleanHtmlTag(comment.getContent());
            String result = SensitiveWordFilterInit.getSensitiveWords(content);

            if (StrUtil.isNotEmpty(result)){

                Sensitivity sensitive = new Sensitivity();
                sensitive.setCid(comment.getId());
                sensitive.setType("comment");
                sensitive.setContent(content);
                sensitive.setTopicId(comment.getTopicId());
                sensitive.setCreateTime(DateUtil.date());
                sensitive.setSenWord(result);
                sensitive.setAudit(0);

                /*有敏感字符存入审核表 设置Comment的audit为人工审核状态值1*/
//                    sensitiveService.saveSensitiveList(sensitive);
                sensitiveCommentIds.add(comment.getId());
                sensitiveList.add(sensitive);
            }else {
                noSensitiveCommentIds.add(comment.getId());
            }
        }

        if (sensitiveList.isEmpty()){
            System.out.println("无敏感评论");
        }else {
            sensitiveService.bulkAppendMasterList(sensitiveList);
            /*有敏感字符存入审核表 设置Comment的audit为人工审核状态值1*/
            commentService.updateAuditResult(sensitiveCommentIds,1);
        }
        commentService.updateAuditResult(noSensitiveCommentIds,2);

    }else {System.out.println("无需要审核的评论");}
//                        /*查询查询未审核的Comment*/

    System.out.println("审核结束"+ DateUtil.now());
        }
/*2分钟*/
    @Scheduled(fixedRate = 1000*60*5)
    private void checkReplySensitiveScheduleTask(){
    System.out.println("开始自动审核回复定时任务");
    List<Reply> replyList =   replyService.selectNotCheckReply(0);
    /*敏感回复集合*/
    List<Sensitivity> sensitiveList = new ArrayList<>();
    /*存在敏感的回复*/
    List<Long> sensitiveReplyIds = new ArrayList<>();
    /*不存在敏感的回复*/
    List<Long> noSensitiveReplyIds = new ArrayList<>();
    if (ObjectUtil.isNotEmpty(replyList)){
        try {
            SensitiveWordFilterInit.InitSensitive("classpath:sent.txt");
        }catch (Exception e){
            System.out.println(e);
        }

        for (Reply reply :replyList){
            String content =  reply.getContent();
            String result = SensitiveWordFilterInit.getSensitiveWords(content);

            if (StrUtil.isNotEmpty(result)){

                Sensitivity sensitive = new Sensitivity();
                sensitive.setCid(reply.getReplyId());
                sensitive.setType("reply");
                sensitive.setTopicId(reply.getTopicId());
                sensitive.setContent(content);
                sensitive.setCreateTime(DateUtil.date());
                sensitive.setSenWord(result);
                sensitive.setAudit(0);

                /*有敏感字符存入审核表 设置回复的audit为人工审核状态值1*/
//                    sensitiveService.saveSensitiveList(sensitive);
                sensitiveReplyIds.add(reply.getReplyId());
                sensitiveList.add(sensitive);
            }else {
                noSensitiveReplyIds.add(reply.getReplyId());
            }
        }

        if (sensitiveList.isEmpty()){
            System.out.println("无敏感回复");
        }else {
            sensitiveService.bulkAppendMasterList(sensitiveList);
            /*有敏感字符存入审核表 设置回复的audit为人工审核状态值1*/
           replyService.updateAuditResult(sensitiveReplyIds,1);
        }
        if (!noSensitiveReplyIds.isEmpty()){ replyService.updateAuditResult(noSensitiveReplyIds,2);}


    }else {System.out.println("无需要审核回复");}
//                        /*查询查询未审核的回复*/

    System.out.println("审核结束"+ DateUtil.now());
        }

        /*
        定时任务
        自动根据敏感审核表的更改topic的文章状态audit
         */
        @Scheduled(fixedRate = 1000*60*5)
        private void checkReplyBySensitivity(){
            /*审核通过*/
            List<Long> SuccessReplyIds =  sensitiveService.selectByTypeAndAudit("reply",1);
        /*审核不通过*/
            List<Long> FailReplyIds = sensitiveService.selectByTypeAndAudit("reply",2);
            if (!SuccessReplyIds.isEmpty()){
                /*0为未审核，1为待人工审核，2为审核通过，3为不通过*/
                Integer flag = replyService.updateAuditResult(SuccessReplyIds,2);
                /*审核状态0为未审核，1为审核通过，2为审核不通过，3为文章发布状态*/
                if (flag>0){sensitiveService.updateAuditByCids(SuccessReplyIds,3);}
            }
            if (!FailReplyIds.isEmpty()){replyService.updateAuditResult(FailReplyIds,3);}
        }
         /*
        定时任务
        自动根据敏感审核表的更改comment的文章状态audit
         */
         @Scheduled(fixedRate = 1000*60*5)
         private void checkCommentBySensitivity(){
             /*审核通过*/
            List<Long> successCommentIds =  sensitiveService.selectByTypeAndAudit("comment",1);
             /*审核不通过*/
             List<Long> failCommentIds = sensitiveService.selectByTypeAndAudit("comment",2);

             if (!successCommentIds.isEmpty()){
                 /*0为未审核，1为待人工审核，2为审核通过，3为不通过*/
                 Integer flag = commentService.updateAuditResult(successCommentIds,2);
                 /*审核状态0为未审核，1为审核通过，2为审核不通过，3为文章发布状态*/
                 if (flag>0){sensitiveService.updateAuditByCids(successCommentIds,3);}
             }
             if (!failCommentIds.isEmpty()){
                 /*0为未审核，1为待人工审核，2为审核通过，3为不通过*/
                 commentService.updateAuditResult(failCommentIds,3);

             }

         }
          /*
        定时任务
        自动根据敏感审核表的更改reply的文章状态audit
         */
          @Scheduled(fixedRate = 1000*60*5)
          private void checkTopicBySensitivity(){
              /*审核通过*/
              List<Long> successTopicIds = sensitiveService.selectByTypeAndAudit("topic",1);
              /*审核不通过*/
              List<Long> failTopicIds = sensitiveService.selectByTypeAndAudit("topic",2);
              /*0为未审核，1为待人工审核，2为审核通过，3为不通过*/
              if (!successTopicIds.isEmpty()){
                  Integer flag = topicService.updateAuditResult(successTopicIds,2);
                  /*审核状态0为未审核，1为审核通过，2为审核不通过，3为文章发布状态*/
                  if (flag>0){sensitiveService.updateAuditByCids(successTopicIds,3);}
              }
              if (!failTopicIds.isEmpty()){topicService.updateAuditResult(failTopicIds,3);}
          }

        }
