package com.malikov.freelance.service;

import com.malikov.freelance.model.Comment;
import com.malikov.freelance.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository repository;

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Comment update(Comment Comment) {
        return repository.save(Comment);
    }

    @Override
    public Comment get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Comment> getAll() {
        return repository.getAll();
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
