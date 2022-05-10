package com.jfeat.forum.services.domain.service;

import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.gen.crud.service.CRUDLabelService;
import com.jfeat.forum.services.gen.persistence.model.Label;
import com.jfeat.forum.util.PageQueryUtil;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface LabelService extends CRUDLabelService{
    /*查询所有标签*/
    List<Label> getAllLabel();
    /*后台查询label*/
    QueryResult getLabelOrder(PageQueryUtil pageQueryUtil);
    ResponseResult saveLabel(Label label);
    ResponseResult updateLabel(Label label);
    ResponseResult deleteLabel(List<Long> ids);
    }