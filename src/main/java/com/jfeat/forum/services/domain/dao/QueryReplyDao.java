package com.jfeat.forum.services.domain.dao;

import com.jfeat.forum.services.domain.model.ReplyRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.forum.services.gen.persistence.model.Reply;
import com.jfeat.forum.services.gen.crud.model.ReplyModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-05-10
 */
public interface QueryReplyDao extends QueryMasterDao<Reply> {
   /*
    * Query entity list by page
    */
    List<ReplyRecord> findReplyPage(Page<ReplyRecord> page, @Param("record") ReplyRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    ReplyModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<ReplyModel> queryMasterModelList(@Param("masterId") Object masterId);
}