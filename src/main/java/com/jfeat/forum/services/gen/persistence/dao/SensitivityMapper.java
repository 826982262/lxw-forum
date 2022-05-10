package com.jfeat.forum.services.gen.persistence.dao;

import com.jfeat.forum.services.gen.persistence.model.Sensitivity;
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
public interface SensitivityMapper extends BaseMapper<Sensitivity> {
    List<Sensitivity> selectOrderByTimeOrAuditByLimit(@Param("start") Integer start, @Param("limit") Integer limit, @Param("audit")Integer audit);
    Integer updateSensitivityAuditByIds(@Param("ids") List<Long> ids,@Param("audit") Integer audit);
    List<Long> selectTypeIdsByTypeAndAudit(@Param("type")String type,@Param("audit")Integer audit);
    Integer updateAuditByCid(@Param("cids") List<Long> cids,@Param("audit")Integer audit);
}
