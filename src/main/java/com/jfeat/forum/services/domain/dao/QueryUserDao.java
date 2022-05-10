package com.jfeat.forum.services.domain.dao;

import com.jfeat.forum.services.domain.model.UserRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.forum.services.gen.persistence.model.User;
import com.jfeat.forum.services.gen.crud.model.UserModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-05-10
 */
public interface QueryUserDao extends QueryMasterDao<User> {
   /*
    * Query entity list by page
    */
    List<UserRecord> findUserPage(Page<UserRecord> page, @Param("record") UserRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    UserModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<UserModel> queryMasterModelList(@Param("masterId") Object masterId);
}