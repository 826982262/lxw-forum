package com.jfeat.forum.services.gen.crud.model;
// this is serviceModel.java.vm




import java.util.List;
import com.jfeat.forum.services.gen.persistence.model.Comment;
import com.jfeat.forum.services.gen.persistence.model.Topic;

/**
 * Created by Code generator on 2022-05-10
 *  * slaves.size() : 1
 *  * modelpack : import com.jfeat.module.topic.services.gen.crud.model.TopicModel;
 */
public class TopicModel extends Topic{

    // comment
    // Comment
    // comment
    private List<Comment> items;

    public List<Comment> getItems() {
        return this.items;
    }

    public void setItems(List<Comment> items) {
        this.items = items;
    }
}
