package com.jfeat.forum.services.gen.persistence.dao;

import com.jfeat.forum.services.gen.persistence.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Code generator
 * @since 2022-05-10
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserOrderByTime(@Param("start") Integer start, @Param("limit") Integer limit);

    Integer updateUsersFlagByUids(@Param("ids") List<Long> ids,@Param("flag") Integer flag);

    User selectUserById(@Param("id")Long id);

    Integer updateUserHeadUrlById(@Param("id")Long id,@Param("url")String url);

    Integer updateUserById(@Param("id")Long id, @Param("uname")String uname, @Param("password")String password);
}
