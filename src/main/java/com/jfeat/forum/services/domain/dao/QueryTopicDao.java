package com.jfeat.forum.services.domain.dao;

import com.jfeat.forum.services.domain.model.TopicRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import com.jfeat.forum.services.gen.crud.model.TopicModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-05-10
 */
public interface QueryTopicDao extends QueryMasterDao<Topic> {
   /*
    * Query entity list by page
    */
    List<TopicRecord> findTopicPage(Page<TopicRecord> page, @Param("record") TopicRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    TopicModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<TopicModel> queryMasterModelList(@Param("masterId") Object masterId);
}