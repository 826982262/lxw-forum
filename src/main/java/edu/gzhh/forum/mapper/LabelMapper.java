package edu.gzhh.forum.mapper;

import edu.gzhh.forum.entity.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxw
 * @since 2022-02-12
 */
@Component
public interface LabelMapper extends BaseMapper<Label> {
    List<Label> selectOrderByRank(@Param("start") Integer start, @Param("limit") Integer limit);


}
