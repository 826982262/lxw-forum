package edu.gzhh.forum.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.gzhh.forum.entity.Sensitivity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lxw
 * @since 2022-03-03
 */
@Component
public interface SensitivityMapper extends BaseMapper<Sensitivity> {
    List<Sensitivity> selectOrderByTimeOrAuditByLimit(@Param("start") Integer start, @Param("limit") Integer limit,@Param("audit")Integer audit);
    Integer updateSensitivityAuditByIds(@Param("ids") List<Long> ids,@Param("audit") Integer audit);
    List<Long> selectTypeIdsByTypeAndAudit(@Param("type")String type,@Param("audit")Integer audit);
    Integer updateAuditByCid(@Param("cids") List<Long> cids,@Param("audit")Integer audit);
}
