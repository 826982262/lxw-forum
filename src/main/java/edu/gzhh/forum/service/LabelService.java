package edu.gzhh.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gzhh.forum.entity.Label;
import edu.gzhh.forum.model.QueryResult;
import edu.gzhh.forum.model.ResponseResult;
import edu.gzhh.forum.util.PageQueryUtil;

import java.util.List;

/**
 * <p>
 *  标签
 * </p>
 *
 * @author lxw
 * @since 2022-02-12
 */
public interface LabelService extends IService<Label> {

    /*查询所有标签*/
    List<Label> getAllLabel();
    /*后台查询label*/
    QueryResult getLabelOrder(PageQueryUtil pageQueryUtil);
    ResponseResult saveLabel(Label label);
    ResponseResult updateLabel(Label label);
    ResponseResult deleteLabel(List<Long> ids);
}
