package com.jfeat.forum.services.domain.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.LabelService;
import com.jfeat.forum.services.gen.crud.service.impl.CRUDLabelServiceImpl;
import com.jfeat.forum.services.gen.persistence.dao.LabelMapper;
import com.jfeat.forum.services.gen.persistence.model.Label;
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

@Service("labelService")
public class LabelServiceImpl extends CRUDLabelServiceImpl implements LabelService {

    @Override
    protected String entityName() {
        return "Label";
    }
    @Resource
    LabelMapper labelMapper;
    @Override
    public List<Label> getAllLabel() {

        return labelMapper.selectAllLabel();
    }

    @Override
    public QueryResult getLabelOrder(PageQueryUtil pageQueryUtil) {
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();

        int total = labelMapper.selectCount(queryWrapper);

        List<Label> labelList = labelMapper.selectOrderByRank((pageQueryUtil.getPage()-1)*pageQueryUtil.getLimit(),pageQueryUtil.getLimit());
        QueryResult<Label> queryResult = new QueryResult<>(labelList,total,pageQueryUtil.getPage(),pageQueryUtil.getLimit());
//        PageResult result = new PageResult(labelList,total,pageQueryUtil.getLimit(),pageQueryUtil.getPage());
        return queryResult;
    }

    @Override
    public ResponseResult saveLabel(Label label) {
        int flag = labelMapper.insert(label);
        if (flag>0){return new ResponseResult(CommonCode.SUCCESS);}else {return new ResponseResult(CommonCode.FAIL);}

    }

    @Override
    public ResponseResult updateLabel(Label label) {
        int flag = labelMapper.updateById(label);
        if (flag>0){return new ResponseResult(CommonCode.SUCCESS);}else {return new ResponseResult(CommonCode.FAIL);}
    }

    @Override
    public ResponseResult deleteLabel(List<Long> ids) {
        int flag = labelMapper.deleteBatchIds(ids);
        if (flag == ids.size()){
            return new ResponseResult(CommonCode.SUCCESS);
        }else {
            return new ResponseResult(CommonCode.FAIL);
        }
    }

                            }
