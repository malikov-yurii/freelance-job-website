package com.malikov.freelance.web.comment;

import com.malikov.freelance.model.Comment;
import com.malikov.freelance.model.User;
import com.malikov.freelance.service.CommentService;
import com.malikov.freelance.service.UserService;
import com.malikov.freelance.to.CommentTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class AbstractCommentController {
//    private static final Logger LOG = LoggerFactory.getLogger(AbstractCommentController.class);

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;


//    public List<Comment> getAll() {
//        return commentService.getAll();
//    }

    public void delete(int id) {
        commentService.delete(id);
    }

//    public void create(CommentTo commentTo) {
//        commentService.save(CommentUtil.newFromTo(commentTo));
//    }

    public void update(CommentTo commentTo) {
        Comment comment = commentService.get(commentTo.getId());
        comment.setText(commentTo.getCommentText());
        commentService.save(comment);
    }

    public void create(CommentTo commentTo, int projectId) {
        User user = userService.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        commentService.save(new Comment(projectId, LocalDateTime.now(), user.getFirstName() + " " + user.getLastName(), commentTo.getCommentText()));
    }

}
