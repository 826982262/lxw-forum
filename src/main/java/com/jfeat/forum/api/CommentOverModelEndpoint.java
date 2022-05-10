
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
import com.jfeat.forum.services.domain.dao.QueryCommentDao;
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
import com.jfeat.forum.services.domain.model.CommentRecord;
import com.jfeat.forum.services.gen.crud.model.CommentModel;

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
    @Api("Comment")
            @RequestMapping("/api/crud/comment/comment/comments")
    public class CommentOverModelEndpoint {

    @Resource
    CommentOverModelService commentOverModelService;

    @Resource
    QueryCommentDao queryCommentDao;


    // 要查询[从表]关联数据，取消下行注释
    // @Resource
    // QueryReplyDao queryReplyDao;

    @BusinessLog(name = "Comment", value = "create Comment")
    @Permission(CommentPermission.COMMENT_NEW)
    @PostMapping
    @ApiOperation(value = "新建 Comment",response = CommentModel.class)
    public Tip createComment(@RequestBody CommentModel entity){
        Integer affected=0;
        try{
                DefaultFilterResult filterResult = new DefaultFilterResult();
            affected= commentOverModelService.createMaster(entity,filterResult,null,null);
            if(affected>0){
               return SuccessTip.create(filterResult.result());
            }
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @BusinessLog(name = "Comment", value = "查看 CommentModel")
    @Permission(CommentPermission.COMMENT_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Comment",response = CommentModel.class)
    public Tip getComment(@PathVariable Long id){
    CRUDObject<CommentModel> entity = commentOverModelService
            .registerQueryMasterDao(queryCommentDao)
            // 要查询[从表]关联数据，取消下行注释
            //.registerQuerySlaveModelListDao(Reply.class, queryReplyDao)
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

    @BusinessLog(name = "Comment", value = "update Comment")
    @Permission(CommentPermission.COMMENT_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Comment",response = CommentModel.class)
    public Tip updateComment(@PathVariable Long id,@RequestBody CommentModel entity){
        entity.setId(id);
                // use update flags
            int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
            // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);

            return SuccessTip.create(commentOverModelService.updateMaster(entity,null,null,null, newOptions));
            }

    @BusinessLog(name = "Comment", value = "delete Comment")
    @Permission(CommentPermission.COMMENT_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Comment")
    public Tip deleteComment(@PathVariable Long id){
            return SuccessTip.create(commentOverModelService.deleteMaster(id));
        }

    @Permission(CommentPermission.COMMENT_VIEW)
    @ApiOperation(value = "Comment 列表信息",response = CommentRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "uid", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "toUid", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "topicId", dataType = "Long"),
                                                                                    @ApiImplicitParam(name = "content", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "postTime", dataType = "Date"),
                                                                                            @ApiImplicitParam(name = "totalReply", dataType = "Integer"),
                                                                                            @ApiImplicitParam(name = "audit", dataType = "Integer") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryCommentPage(Page<CommentRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                        
                                                                                                                                    @RequestParam(name = "uid", required = false) Long uid,
                    
                                                                                                                                    @RequestParam(name = "toUid", required = false) Long toUid,
                    
                                                                                                                                    @RequestParam(name = "topicId", required = false) Long topicId,
                    
                                                                                                                            @RequestParam(name = "content", required = false) String content,
                    
                                                                                                            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                                @RequestParam(name = "postTime", required = false) Date postTime,
                    
                                                                                                                                    @RequestParam(name = "totalReply", required = false) Integer totalReply,
                    
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

    CommentRecord record = new CommentRecord();
                                                                                                                                                                                        record.setUid(uid);
                                                                                                                record.setToUid(toUid);
                                                                                                                record.setTopicId(topicId);
                                                                                                                record.setContent(content);
                                                                                                                record.setPostTime(postTime);
                                                                                                                record.setTotalReply(totalReply);
                                                                                                                record.setAudit(audit);
                        
                                

    List<CommentRecord> commentPage = queryCommentDao.findCommentPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(commentPage);

        return SuccessTip.create(page);
    }
}

