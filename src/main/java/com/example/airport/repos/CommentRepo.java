package com.example.airport.repos;

import com.example.airport.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepo extends CrudRepository<Comment, Long> {
    public Iterable<Comment> findAllByOrderByCreationDateDesc();
}
