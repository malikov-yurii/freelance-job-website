package com.malikov.freelance.service;

import com.malikov.freelance.model.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import static com.malikov.freelance.CommentTestData.*;

public class CommentServiceTest extends AbstractServiceTest {

    @Autowired
    protected CommentService service;

    @Test
    public void testSave() throws Exception {
        Comment newComment = new Comment(2, LocalDateTime.now(), "Yurii Malikov", "New comment text.");
        Comment created = service.save(newComment);
        COMMENT_MATCHER.assertCollectionEquals(
                Arrays.asList(
                        PROJECT2_YURII_COMMENT_1, PROJECT2_ROZA_COMMENT_2, PROJECT6_YURII_COMMENT_3
                        , PROJECT6_DENIS_COMMENT_4, PROJECT7_DENIS_COMMENT_5, PROJECT7_YURII_COMMENT_6, created),
                new ArrayList<>(service.getAll()));
    }

    @Test
    public void testUpdate() throws Exception {
        Comment updated = new Comment(PROJECT2_YURII_COMMENT_1);
        updated.setText("Updated Text");
        service.update(updated);
        COMMENT_MATCHER.assertEquals(updated, service.get(PROJECT2_YURII_COMMENT_1.getId()));
    }

    @Test
    public void testGet() throws Exception {
        Comment user = service.get(PROJECT2_ROZA_COMMENT_2.getId());
        COMMENT_MATCHER.assertEquals(PROJECT2_ROZA_COMMENT_2, user);
    }

    @Test
    public void testGetAll() throws Exception {
        COMMENT_MATCHER.assertCollectionEquals(
                Arrays.asList(
                        PROJECT2_YURII_COMMENT_1, PROJECT2_ROZA_COMMENT_2, PROJECT6_YURII_COMMENT_3
                        , PROJECT6_DENIS_COMMENT_4, PROJECT7_DENIS_COMMENT_5, PROJECT7_YURII_COMMENT_6),
                new ArrayList<>(service.getAll()));

    }

    @Test
    public void testDelete() throws Exception {
        service.delete(PROJECT6_YURII_COMMENT_3.getId());
        COMMENT_MATCHER.assertCollectionEquals(
                Arrays.asList(
                        PROJECT2_YURII_COMMENT_1, PROJECT2_ROZA_COMMENT_2
                        , PROJECT6_DENIS_COMMENT_4, PROJECT7_DENIS_COMMENT_5, PROJECT7_YURII_COMMENT_6),
                new ArrayList<>(service.getAll()));
    }

}
