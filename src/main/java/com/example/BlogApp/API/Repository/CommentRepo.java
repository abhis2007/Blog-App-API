package com.example.BlogApp.API.Repository;

import com.example.BlogApp.API.Entity.CommentsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends CrudRepository<CommentsEntity, String> {
}
