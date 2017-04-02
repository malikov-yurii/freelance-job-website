package com.malikov.freelance.to;

import com.malikov.freelance.model.BaseEntity;

public class CommentTo extends BaseEntity{

    private String commentText;

    private Boolean blocked;

    public CommentTo(){}

    public CommentTo(String commentText) {
        this.commentText = commentText;
    }

    public CommentTo(Integer id, String commentText, Boolean blocked) {
        super(id);
        this.commentText = commentText;
        this.blocked = blocked;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
