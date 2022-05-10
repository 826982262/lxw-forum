package com.jfeat.forum.services.domain.service;

import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.gen.crud.service.CRUDSensitivityService;
import com.jfeat.forum.services.gen.persistence.model.Sensitivity;
import com.jfeat.forum.util.PageQueryUtil;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface SensitivityService extends CRUDSensitivityService{

    void saveSensitiveList(Sensitivity sensitive);

    QueryResult selectSensitiveList(PageQueryUtil pageQueryUtil, Integer audit);
    ResponseResult UpdateAuditByIds(List<Long> Ids, Integer audit);
    List selectByTypeAndAudit(String type,Integer audit);
    Integer updateAuditByCids(List<Long> cids,Integer audit);
}