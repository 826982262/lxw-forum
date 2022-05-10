
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
import com.jfeat.forum.services.domain.dao.QuerySensitivityDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.forum.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.forum.services.domain.service.*;
import com.jfeat.forum.services.domain.model.SensitivityRecord;
import com.jfeat.forum.services.gen.persistence.model.Sensitivity;

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
    @Api("Sensitivity")
            @RequestMapping("/api/crud/sensitivity/sensitivity/sensitivityies")
    public class SensitivityEndpoint {

    @Resource
    SensitivityService sensitivityService;

    @Resource
    QuerySensitivityDao querySensitivityDao;



    @BusinessLog(name = "Sensitivity", value = "create Sensitivity")
    @Permission(SensitivityPermission.SENSITIVITY_NEW)
    @PostMapping
    @ApiOperation(value = "新建 Sensitivity",response = Sensitivity.class)
    public Tip createSensitivity(@RequestBody Sensitivity entity){
        Integer affected=0;
        try{
                affected= sensitivityService.createMaster(entity);
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @Permission(SensitivityPermission.SENSITIVITY_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Sensitivity",response = Sensitivity.class)
    public Tip getSensitivity(@PathVariable Long id){
                        return SuccessTip.create(sensitivityService.queryMasterModel(querySensitivityDao, id));
            }

    @BusinessLog(name = "Sensitivity", value = "update Sensitivity")
    @Permission(SensitivityPermission.SENSITIVITY_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Sensitivity",response = Sensitivity.class)
    public Tip updateSensitivity(@PathVariable Long id,@RequestBody Sensitivity entity){
        entity.setId(id);
                return SuccessTip.create(sensitivityService.updateMaster(entity));
            }

    @BusinessLog(name = "Sensitivity", value = "delete Sensitivity")
    @Permission(SensitivityPermission.SENSITIVITY_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Sensitivity")
    public Tip deleteSensitivity(@PathVariable Long id){
            return SuccessTip.create(sensitivityService.deleteMaster(id));
        }

    @Permission(SensitivityPermission.SENSITIVITY_VIEW)
    @ApiOperation(value = "Sensitivity 列表信息",response = SensitivityRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "topicId", dataType = "Long"),
                                                                                    @ApiImplicitParam(name = "type", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "cid", dataType = "Long"),
                                                                                    @ApiImplicitParam(name = "content", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "createTime", dataType = "Date"),
                                                                                    @ApiImplicitParam(name = "senWord", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "audit", dataType = "Integer") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip querySensitivityPage(Page<SensitivityRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                        
                                                                                                                                    @RequestParam(name = "topicId", required = false) Long topicId,
                    
                                                                                                                            @RequestParam(name = "type", required = false) String type,
                    
                                                                                                                                    @RequestParam(name = "cid", required = false) Long cid,
                    
                                                                                                                            @RequestParam(name = "content", required = false) String content,
                    
                                                                                                            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                                @RequestParam(name = "createTime", required = false) Date createTime,
                    
                                                                                                                            @RequestParam(name = "senWord", required = false) String senWord,
                    
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

    SensitivityRecord record = new SensitivityRecord();
                                                                                                                                                                                        record.setTopicId(topicId);
                                                                                                                record.setType(type);
                                                                                                                record.setCid(cid);
                                                                                                                record.setContent(content);
                                                                                                                record.setCreateTime(createTime);
                                                                                                                record.setSenWord(senWord);
                                                                                                                record.setAudit(audit);
                        
                                

    List<SensitivityRecord> sensitivityPage = querySensitivityDao.findSensitivityPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(sensitivityPage);

        return SuccessTip.create(page);
    }
}

