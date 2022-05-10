package com.jfeat.forum.services.domain.dao;

import com.jfeat.forum.services.domain.model.CommentRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.forum.services.gen.persistence.model.Comment;
import com.jfeat.forum.services.gen.crud.model.CommentModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-05-10
 */
public interface QueryCommentDao extends QueryMasterDao<Comment> {
   /*
    * Query entity list by page
    */
    List<CommentRecord> findCommentPage(Page<CommentRecord> page, @Param("record") CommentRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    CommentModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<CommentModel> queryMasterModelList(@Param("masterId") Object masterId);
}