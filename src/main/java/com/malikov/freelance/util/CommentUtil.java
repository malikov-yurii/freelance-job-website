package com.malikov.freelance.util;

import com.malikov.freelance.model.Comment;
import com.malikov.freelance.to.CommentTo;

public class CommentUtil {

    public static CommentTo asTo(Comment comment){
        return new CommentTo(comment.getId(), comment.getDatePlaced() + " " + comment.getUserFullName() + ": " + comment.getText());
    }
}
