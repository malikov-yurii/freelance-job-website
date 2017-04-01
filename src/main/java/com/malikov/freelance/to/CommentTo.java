package com.malikov.freelance.to;

import com.malikov.freelance.model.BaseEntity;

public class CommentTo extends BaseEntity{

    public String commentText;

    public CommentTo(){}

    public CommentTo(String commentText) {
        this.commentText = commentText;
    }

    public CommentTo(Integer id, String commentText) {
        super(id);
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
