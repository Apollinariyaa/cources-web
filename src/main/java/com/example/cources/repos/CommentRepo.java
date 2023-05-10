package com.example.cources.repos;

import com.example.cources.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Long> {
    public Iterable<Comment> findAllByOrderByCreationDateDesc();
}
