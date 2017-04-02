package com.malikov.freelance.repository.jpa;

import com.malikov.freelance.model.Comment;
import com.malikov.freelance.repository.CommentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaCommentRepository implements CommentRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        if (comment.isNew()) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Comment.DELETE)
                .setParameter("id", id).executeUpdate() != 0;
    }

    @Override
    public Comment get(int id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {
        return em.createNamedQuery(Comment.ALL_SORTED, Comment.class)
                .getResultList();
    }

}
