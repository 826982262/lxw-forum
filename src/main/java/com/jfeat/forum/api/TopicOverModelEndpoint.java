
package com.jfeat.forum.api;


import com.jfeat.crud.plus.META;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.forum.services.domain.dao.QueryTopicDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.forum.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.forum.services.domain.service.*;
import com.jfeat.forum.services.domain.model.TopicRecord;
import com.jfeat.forum.services.gen.crud.model.TopicModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  api
 * </p>
 *
 * @author Code generator
 * @since 2022-05-10
 */
    @RestController
    @Api("Topic")
            @RequestMapping("/api/crud/topic/topic/topics")
    public class TopicOverModelEndpoint {

    @Resource
    TopicOverModelService topicOverModelService;

    @Resource
    QueryTopicDao queryTopicDao;


    // 要查询[从表]关联数据，取消下行注释
    // @Resource
    // QueryCommentDao queryCommentDao;

    @BusinessLog(name = "Topic", value = "create Topic")
    @Permission(TopicPermission.TOPIC_NEW)
    @PostMapping
    @ApiOperation(value = "新建 Topic",response = TopicModel.class)
    public Tip createTopic(@RequestBody TopicModel entity){
        Integer affected=0;
        try{
                DefaultFilterResult filterResult = new DefaultFilterResult();
            affected= topicOverModelService.createMaster(entity,filterResult,null,null);
            if(affected>0){
               return SuccessTip.create(filterResult.result());
            }
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @BusinessLog(name = "Topic", value = "查看 TopicModel")
    @Permission(TopicPermission.TOPIC_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Topic",response = TopicModel.class)
    public Tip getTopic(@PathVariable Long id){
    CRUDObject<TopicModel> entity = topicOverModelService
            .registerQueryMasterDao(queryTopicDao)
            // 要查询[从表]关联数据，取消下行注释
            //.registerQuerySlaveModelListDao(Comment.class, queryCommentDao)
            .retrieveMaster(id,null,null,null);

            // sample query for registerQueryMasterDao
            // e.g. <select id="queryMasterModel" resultType="PlanModel">
            //       SELECT t_plan_model.*, t_org.name as orgName
            //       FROM t_plan_model
            //       LEFT JOIN t_org ON t_org.id==t_plan_model.org_id
            //       WHERE t_plan_model.id=#{id}
            //       GROUP BY t_plan_model.id
            //    </select>

            if(entity != null) {
                return SuccessTip.create(entity.toJSONObject());
            } else {
                return SuccessTip.create();
            }

            }

    @BusinessLog(name = "Topic", value = "update Topic")
    @Permission(TopicPermission.TOPIC_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Topic",response = TopicModel.class)
    public Tip updateTopic(@PathVariable Long id,@RequestBody TopicModel entity){
        entity.setId(id);
                // use update flags
            int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
            // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);

            return SuccessTip.create(topicOverModelService.updateMaster(entity,null,null,null, newOptions));
            }

    @BusinessLog(name = "Topic", value = "delete Topic")
    @Permission(TopicPermission.TOPIC_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Topic")
    public Tip deleteTopic(@PathVariable Long id){
            return SuccessTip.create(topicOverModelService.deleteMaster(id));
        }

    @Permission(TopicPermission.TOPIC_VIEW)
    @ApiOperation(value = "Topic 列表信息",response = TopicRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                                    @ApiImplicitParam(name = "lId", dataType = "Integer"),
                                                                                            @ApiImplicitParam(name = "lName", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "uid", dataType = "Long"),
                                                                                    @ApiImplicitParam(name = "title", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "time", dataType = "Date"),
                                                                                            @ApiImplicitParam(name = "updateTime", dataType = "Date"),
                                                                                    @ApiImplicitParam(name = "content", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "summary", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "commentTotal", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "istop", dataType = "Integer"),
                                                                                            @ApiImplicitParam(name = "audit", dataType = "Integer") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryTopicPage(Page<TopicRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                        
                                                                                                                                            @RequestParam(name = "lId", required = false) Integer lId,
                    
                                                                                                                                    @RequestParam(name = "lName", required = false) String lName,
                    
                                                                                                                                    @RequestParam(name = "uid", required = false) Long uid,
                    
                                                                                                                            @RequestParam(name = "title", required = false) String title,
                    
                                                                                                            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                                @RequestParam(name = "time", required = false) Date time,
                    
                                                                                                            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                                @RequestParam(name = "updateTime", required = false) Date updateTime,
                    
                                                                                                                            @RequestParam(name = "content", required = false) String content,
                    
                                                                                                                            @RequestParam(name = "summary", required = false) String summary,
                    
                                                                                                                                    @RequestParam(name = "commentTotal", required = false) Long commentTotal,
                    
                                                                                                                                    @RequestParam(name = "istop", required = false) Integer istop,
                    
                                                                                                                                    @RequestParam(name = "audit", required = false) Integer audit,
        @RequestParam(name = "orderBy", required = false) String orderBy,
        @RequestParam(name = "sort", required = false)  String sort) {
                    
            if(orderBy!=null&&orderBy.length()>0){
        if(sort!=null&&sort.length()>0){
        String sortPattern = "(ASC|DESC|asc|desc)";
        if(!sort.matches(sortPattern)){
        throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
        }
        }else{
        sort = "ASC";
        }
        orderBy = "`"+orderBy+"`" +" "+sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

    TopicRecord record = new TopicRecord();
    record.setlId(lId);
    record.setlName(lName);
    record.setUid(uid);
    record.setTitle(title);
    record.setTime(time);
    record.setUpdateTime(updateTime);
    record.setContent(content);
    record.setSummary(summary);
    record.setCommentTotal(commentTotal);
    record.setIstop(istop);
    record.setAudit(audit);
                        
                                

    List<TopicRecord> topicPage = queryTopicDao.findTopicPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(topicPage);

        return SuccessTip.create(page);
    }
}

