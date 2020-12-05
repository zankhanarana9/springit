package com.zrana.springit.service;


import com.zrana.springit.domain.Comment;
import com.zrana.springit.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
