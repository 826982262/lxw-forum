package com.jfeat.forum.services.domain.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.SensitivityService;
import com.jfeat.forum.services.gen.crud.service.impl.CRUDSensitivityServiceImpl;
import com.jfeat.forum.services.gen.persistence.dao.SensitivityMapper;
import com.jfeat.forum.services.gen.persistence.model.Sensitivity;
import com.jfeat.forum.util.PageQueryUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("sensitivityService")
public class SensitivityServiceImpl extends CRUDSensitivityServiceImpl implements SensitivityService {

    @Override
    protected String entityName() {
        return "Sensitivity";
    }
    @Resource
    SensitivityMapper sensitiveMapper;

    @Override
    public void saveSensitiveList(Sensitivity sensitive){
        sensitiveMapper.insert(sensitive);
    }

    @Override
    public QueryResult selectSensitiveList(PageQueryUtil pageQueryUtil, Integer audit) {
        QueryWrapper<Sensitivity> queryWrapper = new QueryWrapper<>();
        if (audit!=null){
            queryWrapper.eq("audit",audit);
        }
        int total = sensitiveMapper.selectCount(queryWrapper);


        List<Sensitivity> sensitivities = sensitiveMapper
                .selectOrderByTimeOrAuditByLimit(pageQueryUtil.getStart(),pageQueryUtil.getLimit(),audit);
        QueryResult<Sensitivity> result = new QueryResult<>();
        result.setTotal(total);
        result.setList(sensitivities);
        result.setCurrPage(pageQueryUtil.getPage());
        result.setPageSize(pageQueryUtil.getLimit());
        result.setTotalPage(total,pageQueryUtil.getLimit());
        return result;
    }

    @Override
    public ResponseResult UpdateAuditByIds(List<Long> Ids, Integer audit) {

        Integer sign = sensitiveMapper.updateSensitivityAuditByIds(Ids,audit);
        if (sign>0){
            return new ResponseResult(CommonCode.SUCCESS);
        }else {
            return new ResponseResult(CommonCode.FAIL);
        }
    }

    @Override
    public List<Long> selectByTypeAndAudit(String type,Integer audit) {

//        QueryWrapper<Sensitivity> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("type",type);
//        queryWrapper.eq("audit",audit);
//        queryWrapper.select("cid");
//
//        return sensitiveMapper.selectObjs(queryWrapper);
        return sensitiveMapper.selectTypeIdsByTypeAndAudit(type,audit);
    }

    @Override
    public Integer updateAuditByCids(List<Long> cids, Integer audit) {
        return sensitiveMapper.updateAuditByCid(cids,audit);
    }

                            }
