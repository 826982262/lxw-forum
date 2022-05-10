
package com.jfeat.forum.api;


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
import com.jfeat.forum.services.domain.dao.QueryReplyDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.forum.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.forum.services.domain.service.*;
import com.jfeat.forum.services.domain.model.ReplyRecord;
import com.jfeat.forum.services.gen.persistence.model.Reply;

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
    @Api("Reply")
            @RequestMapping("/api/crud/reply/reply/replyies")
    public class ReplyEndpoint {

    @Resource
    ReplyService replyService;

    @Resource
    QueryReplyDao queryReplyDao;



    @BusinessLog(name = "Reply", value = "create Reply")
    @Permission(ReplyPermission.REPLY_NEW)
    @PostMapping
    @ApiOperation(value = "新建 Reply",response = Reply.class)
    public Tip createReply(@RequestBody Reply entity){
        Integer affected=0;
        try{
                affected= replyService.createMaster(entity);
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @Permission(ReplyPermission.REPLY_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Reply",response = Reply.class)
    public Tip getReply(@PathVariable Long id){
                        return SuccessTip.create(replyService.queryMasterModel(queryReplyDao, id));
            }

    @BusinessLog(name = "Reply", value = "update Reply")
    @Permission(ReplyPermission.REPLY_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Reply",response = Reply.class)
    public Tip updateReply(@PathVariable Long id,@RequestBody Reply entity){
        entity.setReplyId(id);
                return SuccessTip.create(replyService.updateMaster(entity));
            }

    @BusinessLog(name = "Reply", value = "delete Reply")
    @Permission(ReplyPermission.REPLY_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Reply")
    public Tip deleteReply(@PathVariable Long id){
            return SuccessTip.create(replyService.deleteMaster(id));
        }

    @Permission(ReplyPermission.REPLY_VIEW)
    @ApiOperation(value = "Reply 列表信息",response = ReplyRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                                @ApiImplicitParam(name = "replyId", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "commentuId", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "commentId", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "uid", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "topicId", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "time", dataType = "Date"),
                                                                                    @ApiImplicitParam(name = "content", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "audit", dataType = "Integer") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryReplyPage(Page<ReplyRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                                @RequestParam(name = "replyId", required = false) Long replyId,
                    
                                                                                                                                    @RequestParam(name = "commentuId", required = false) Long commentuId,
                    
                                                                                                                                    @RequestParam(name = "commentId", required = false) Long commentId,
                    
                                                                                                                                    @RequestParam(name = "uid", required = false) Long uid,
                    
                                                                                                                                    @RequestParam(name = "topicId", required = false) Long topicId,
                    
                                                                                                            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                                @RequestParam(name = "time", required = false) Date time,
                    
                                                                                                                            @RequestParam(name = "content", required = false) String content,
                    
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

    ReplyRecord record = new ReplyRecord();
                                                                                                        record.setReplyId(replyId);
                                                                                                                record.setCommentuId(commentuId);
                                                                                                                record.setCommentId(commentId);
                                                                                                                record.setUid(uid);
                                                                                                                record.setTopicId(topicId);
                                                                                                                record.setTime(time);
                                                                                                                record.setContent(content);
                                                                                                                record.setAudit(audit);
                        
                                

    List<ReplyRecord> replyPage = queryReplyDao.findReplyPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(replyPage);

        return SuccessTip.create(page);
    }
}

