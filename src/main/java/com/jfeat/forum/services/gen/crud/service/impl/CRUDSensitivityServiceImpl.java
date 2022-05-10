package com.jfeat.forum.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.forum.services.gen.persistence.model.Sensitivity;
import com.jfeat.forum.services.gen.persistence.dao.SensitivityMapper;
import com.jfeat.forum.services.gen.crud.service.CRUDSensitivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDSensitivityService
 * @author Code generator
 * @since 2022-05-10
 */

@Service
public class CRUDSensitivityServiceImpl  extends CRUDServiceOnlyImpl<Sensitivity> implements CRUDSensitivityService {





        @Resource
        protected SensitivityMapper sensitivityMapper;

        @Override
        protected BaseMapper<Sensitivity> getMasterMapper() {
                return sensitivityMapper;
        }







}


