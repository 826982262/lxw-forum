package edu.gzhh.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gzhh.forum.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxw
 * @since 2021-12-16
 */
@Component
public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserOrderByTime(@Param("start") Integer start, @Param("limit") Integer limit);

    Integer updateUsersFlagByUids(@Param("ids") List<Long> ids,@Param("flag") Integer flag);
}
