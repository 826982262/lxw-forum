
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
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.forum.services.domain.dao.QueryLabelDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.forum.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.forum.services.domain.service.*;
import com.jfeat.forum.services.domain.model.LabelRecord;
import com.jfeat.forum.services.gen.persistence.model.Label;

    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Api("Label")
            @RequestMapping("/api/crud/label/label/labels")
    public class LabelEndpoint {

    @Resource
    LabelService labelService;

    @Resource
    QueryLabelDao queryLabelDao;



    @BusinessLog(name = "Label", value = "create Label")
    @Permission(LabelPermission.LABEL_NEW)
    @PostMapping
    @ApiOperation(value = "新建 Label",response = Label.class)
    public Tip createLabel(@RequestBody Label entity){
        Integer affected=0;
        try{
                affected= labelService.createMaster(entity);
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @Permission(LabelPermission.LABEL_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Label",response = Label.class)
    public Tip getLabel(@PathVariable Long id){
                        return SuccessTip.create(labelService.queryMasterModel(queryLabelDao, id));
            }

    @BusinessLog(name = "Label", value = "update Label")
    @Permission(LabelPermission.LABEL_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Label",response = Label.class)
    public Tip updateLabel(@PathVariable Long id,@RequestBody Label entity){
        entity.setId(id);
                return SuccessTip.create(labelService.updateMaster(entity));
            }

    @BusinessLog(name = "Label", value = "delete Label")
    @Permission(LabelPermission.LABEL_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Label")
    public Tip deleteLabel(@PathVariable Long id){
            return SuccessTip.create(labelService.deleteMaster(id));
        }

    @Permission(LabelPermission.LABEL_VIEW)
    @ApiOperation(value = "Label 列表信息",response = LabelRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "lName", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "ranking", dataType = "Integer") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryLabelPage(Page<LabelRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                        
                                                                                                                                    @RequestParam(name = "lName", required = false) String lName,
                    
                                                                                                                                    @RequestParam(name = "ranking", required = false) Integer ranking,
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

    LabelRecord record = new LabelRecord();
    record.setlName(lName);
                                                                                                                record.setRanking(ranking);
                        
                                

    List<LabelRecord> labelPage = queryLabelDao.findLabelPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(labelPage);

        return SuccessTip.create(page);
    }
}

