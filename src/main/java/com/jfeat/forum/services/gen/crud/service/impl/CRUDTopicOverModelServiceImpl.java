package com.jfeat.forum.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import com.jfeat.forum.services.gen.persistence.dao.TopicMapper;
import com.jfeat.forum.services.gen.persistence.dao.CommentMapper;
import com.jfeat.forum.services.gen.persistence.model.Comment;
    
    import com.jfeat.forum.services.gen.crud.service.CRUDTopicOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.forum.services.gen.crud.model.TopicModel;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDTopicOverModelService
 * @author Code generator
 * @since 2022-05-10
 */

@Service
public class CRUDTopicOverModelServiceImpl  extends CRUDServiceOverModelImpl<Topic,TopicModel> implements CRUDTopicOverModelService {









    @Resource
    protected TopicMapper topicMapper;

    
    @Override
    protected BaseMapper<Topic> getMasterMapper() {
        return topicMapper;
    }

    @Override
    protected Class<Topic> masterClassName() {
        return Topic.class;
    }

    @Override
    protected Class<TopicModel> modelClassName() {
        return TopicModel.class;
    }



    
    @Resource
    private CommentMapper commentMapper;

                        private final static String commentFieldName = "topicId";
    
        private final static String commentKeyName = "items";
    
                        
    

    
    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                                             commentKeyName
                                             };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {

        
                                                
            if (field.compareTo(commentKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(commentFieldName);
            _field.setItemClassName(Comment.class);
            _field.setItemMapper(commentMapper);
            
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


