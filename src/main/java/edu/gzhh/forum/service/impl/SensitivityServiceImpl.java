package edu.gzhh.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gzhh.forum.entity.Sensitivity;
import edu.gzhh.forum.mapper.SensitivityMapper;
import edu.gzhh.forum.model.response.CommonCode;
import edu.gzhh.forum.model.response.QueryResult;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.service.SensitivityService;
import edu.gzhh.forum.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxw
 * @since 2022-03-03
 */
@Service
public class SensitivityServiceImpl extends ServiceImpl<SensitivityMapper, Sensitivity> implements SensitivityService {
@Autowired
SensitivityMapper sensitiveMapper;
    public void saveSensitiveList(Sensitivity sensitive){
        sensitiveMapper.insert(sensitive);
    }

    @Override
    public QueryResult selectSensitiveList(PageQueryUtil pageQueryUtil,Integer audit) {
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
