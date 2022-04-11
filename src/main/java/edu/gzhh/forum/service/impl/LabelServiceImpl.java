package edu.gzhh.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gzhh.forum.entity.Label;
import edu.gzhh.forum.mapper.LabelMapper;
import edu.gzhh.forum.model.CommonCode;
import edu.gzhh.forum.model.QueryResult;
import edu.gzhh.forum.model.ResponseResult;
import edu.gzhh.forum.service.LabelService;
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
 * @since 2022-02-12
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Autowired
    LabelMapper labelMapper;
    @Override
    public List<Label> getAllLabel() {
        QueryWrapper<Label> queryWrapper = new QueryWrapper<>();

        return labelMapper.selectList(queryWrapper);
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
