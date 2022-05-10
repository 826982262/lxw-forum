package com.jfeat.forum.services.gen.crud.model;
// this is serviceModel.java.vm




import java.util.List;
import com.jfeat.forum.services.gen.persistence.model.Reply;
import com.jfeat.forum.services.gen.persistence.model.Comment;

/**
 * Created by Code generator on 2022-05-10
 *  * slaves.size() : 1
 *  * modelpack : import com.jfeat.module.comment.services.gen.crud.model.CommentModel;
 */
public class CommentModel extends Comment{

    // reply
    // Reply
    // reply
    private List<Reply> items;

    public List<Reply> getItems() {
        return this.items;
    }

    public void setItems(List<Reply> items) {
        this.items = items;
    }
}
