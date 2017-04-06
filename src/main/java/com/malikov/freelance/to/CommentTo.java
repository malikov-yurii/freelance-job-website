package com.malikov.freelance.to;

import com.malikov.freelance.model.BaseEntity;

import java.time.LocalDateTime;

public class CommentTo extends BaseEntity{

    private String userFullName;

    private LocalDateTime dateTimePlaced;

    private String commentText;

    private Boolean blocked;

    public CommentTo(){}

    public CommentTo(Integer id, String userFullName, LocalDateTime dateTimePlaced, String commentText, Boolean blocked) {
        super(id);
        this.userFullName = userFullName;
        this.dateTimePlaced = dateTimePlaced;
        this.commentText = commentText;
        this.blocked = blocked;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public LocalDateTime getDateTimePlaced() {
        return dateTimePlaced;
    }

    public void setDateTimePlaced(LocalDateTime dateTimePlaced) {
        this.dateTimePlaced = dateTimePlaced;
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
