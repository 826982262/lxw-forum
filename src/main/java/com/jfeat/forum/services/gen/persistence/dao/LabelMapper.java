package com.jfeat.forum.services.gen.persistence.dao;

import com.jfeat.forum.services.gen.persistence.model.Label;
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
public interface LabelMapper extends BaseMapper<Label> {
    List<Label> selectOrderByRank(@Param("start") Integer start, @Param("limit") Integer limit);

    List<Label> selectAllLabel();
}
