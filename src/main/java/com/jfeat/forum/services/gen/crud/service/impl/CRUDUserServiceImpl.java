package com.jfeat.forum.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.forum.services.gen.persistence.model.User;
import com.jfeat.forum.services.gen.persistence.dao.UserMapper;
import com.jfeat.forum.services.gen.crud.service.CRUDUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDUserService
 * @author Code generator
 * @since 2022-05-10
 */

@Service
public class CRUDUserServiceImpl  extends CRUDServiceOnlyImpl<User> implements CRUDUserService {





        @Resource
        protected UserMapper userMapper;

        @Override
        protected BaseMapper<User> getMasterMapper() {
                return userMapper;
        }







}


