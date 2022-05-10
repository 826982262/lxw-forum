package com.jfeat.forum.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.forum.services.gen.persistence.model.Label;
import com.jfeat.forum.services.gen.persistence.dao.LabelMapper;
import com.jfeat.forum.services.gen.crud.service.CRUDLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDLabelService
 * @author Code generator
 * @since 2022-05-10
 */

@Service
public class CRUDLabelServiceImpl  extends CRUDServiceOnlyImpl<Label> implements CRUDLabelService {





        @Resource
        protected LabelMapper labelMapper;

        @Override
        protected BaseMapper<Label> getMasterMapper() {
                return labelMapper;
        }







}


