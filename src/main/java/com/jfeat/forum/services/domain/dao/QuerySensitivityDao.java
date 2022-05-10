package com.jfeat.forum.services.domain.dao;

import com.jfeat.forum.services.domain.model.SensitivityRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.forum.services.gen.persistence.model.Sensitivity;
import com.jfeat.forum.services.gen.crud.model.SensitivityModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-05-10
 */
public interface QuerySensitivityDao extends QueryMasterDao<Sensitivity> {
   /*
    * Query entity list by page
    */
    List<SensitivityRecord> findSensitivityPage(Page<SensitivityRecord> page, @Param("record") SensitivityRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    SensitivityModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<SensitivityModel> queryMasterModelList(@Param("masterId") Object masterId);
}