package com.jfeat.forum.services.domain.dao;

import com.jfeat.forum.services.domain.model.LabelRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.forum.services.gen.persistence.model.Label;
import com.jfeat.forum.services.gen.crud.model.LabelModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-05-10
 */
public interface QueryLabelDao extends QueryMasterDao<Label> {
   /*
    * Query entity list by page
    */
    List<LabelRecord> findLabelPage(Page<LabelRecord> page, @Param("record") LabelRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    LabelModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<LabelModel> queryMasterModelList(@Param("masterId") Object masterId);
}