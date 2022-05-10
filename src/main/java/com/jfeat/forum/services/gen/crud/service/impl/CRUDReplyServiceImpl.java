package com.jfeat.forum.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.forum.services.gen.persistence.model.Reply;
import com.jfeat.forum.services.gen.persistence.dao.ReplyMapper;
import com.jfeat.forum.services.gen.crud.service.CRUDReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDReplyService
 * @author Code generator
 * @since 2022-05-10
 */

@Service
public class CRUDReplyServiceImpl  extends CRUDServiceOnlyImpl<Reply> implements CRUDReplyService {





        @Resource
        protected ReplyMapper replyMapper;

        @Override
        protected BaseMapper<Reply> getMasterMapper() {
                return replyMapper;
        }







}


