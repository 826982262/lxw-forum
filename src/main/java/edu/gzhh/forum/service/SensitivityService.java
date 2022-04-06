package edu.gzhh.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gzhh.forum.entity.Sensitivity;
import edu.gzhh.forum.model.response.QueryResult;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.util.PageQueryUtil;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxw
 * @since 2022-03-03
 */
public interface SensitivityService extends IService<Sensitivity> {
   void saveSensitiveList(Sensitivity sensitive);
   QueryResult selectSensitiveList(PageQueryUtil pageQueryUtil,Integer audit);
   ResponseResult UpdateAuditByIds(List<Long> Ids,Integer audit);
   List selectByTypeAndAudit(String type,Integer audit);
   Integer updateAuditByCids(List<Long> cids,Integer audit);
}
