package com.malikov.freelance;

import com.malikov.freelance.matcher.ModelMatcher;
import com.malikov.freelance.model.Comment;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentTestData {
    public static final Comment PROJECT2_YURII_COMMENT_1 = new Comment(1, 2, LocalDateTime.of(2016, 9, 13, 18, 9, 3), "Yurii Malikov", "Roza, you must be kidding us?");
    public static final Comment PROJECT2_ROZA_COMMENT_2 = new Comment(2, 2, LocalDateTime.of(2016, 9, 14, 18, 44, 3), "Roza Rozova", "No, Yurii, it is quite promising project. Can you create it?");
    public static final Comment PROJECT6_YURII_COMMENT_3 = new Comment(3, 6, LocalDateTime.of(2016, 9, 15, 9, 22, 55), "Yurii Malikov", "I can complete the task using CMS Joomla");
    public static final Comment PROJECT6_DENIS_COMMENT_4 = new Comment(4, 6, LocalDateTime.of(2016, 9, 16, 10, 7, 7), "Denis Malikov", "I can complete the task using CMS Drupal");
    public static final Comment PROJECT7_DENIS_COMMENT_5 = new Comment(5, 7, LocalDateTime.of(2016, 9, 21, 14, 44, 44), "Denis Malikov", "I can complete the task using HTML, CSS");
    public static final Comment PROJECT7_YURII_COMMENT_6 = new Comment(6, 7, LocalDateTime.of(2016, 9, 22, 16, 55, 55), "Yurii Malikov", "I can complete the task using CMS Wordpress");


    public static final ModelMatcher<Comment> COMMENT_MATCHER = ModelMatcher.of(Comment.class,
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDatePlaced(), actual.getDatePlaced())
                            && Objects.equals(expected.getProjectId(), actual.getProjectId())
                            && Objects.equals(expected.getUserFullName(), actual.getUserFullName())
                            && Objects.equals(expected.getText(), actual.getText())
                    )
    );
}
