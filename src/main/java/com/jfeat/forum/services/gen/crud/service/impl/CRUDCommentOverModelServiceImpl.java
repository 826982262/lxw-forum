package com.jfeat.forum.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.forum.services.gen.persistence.model.Comment;
import com.jfeat.forum.services.gen.persistence.dao.CommentMapper;
import com.jfeat.forum.services.gen.persistence.dao.ReplyMapper;
import com.jfeat.forum.services.gen.persistence.model.Reply;
    
    import com.jfeat.forum.services.gen.crud.service.CRUDCommentOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.forum.services.gen.crud.model.CommentModel;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDCommentOverModelService
 * @author Code generator
 * @since 2022-05-10
 */

@Service
public class CRUDCommentOverModelServiceImpl  extends CRUDServiceOverModelImpl<Comment,CommentModel> implements CRUDCommentOverModelService {









    @Resource
    protected CommentMapper commentMapper;

    
    @Override
    protected BaseMapper<Comment> getMasterMapper() {
        return commentMapper;
    }

    @Override
    protected Class<Comment> masterClassName() {
        return Comment.class;
    }

    @Override
    protected Class<CommentModel> modelClassName() {
        return CommentModel.class;
    }



    
    @Resource
    private ReplyMapper replyMapper;

                        private final static String replyFieldName = "commentId";
    
        private final static String replyKeyName = "items";
    
                        
    

    
    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                                             replyKeyName
                                             };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {

        
                                                
            if (field.compareTo(replyKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(replyFieldName);
            _field.setItemClassName(Reply.class);
            _field.setItemMapper(replyMapper);
            
            return _field;
        }


            
        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
                                };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {
                
        throw new BusinessException(BusinessCode.BadRequest);
    }




}


