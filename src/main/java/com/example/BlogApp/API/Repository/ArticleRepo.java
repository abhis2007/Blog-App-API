package com.example.BlogApp.API.Repository;

import com.example.BlogApp.API.Entity.ArticlesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepo extends CrudRepository<ArticlesEntity, String> {
}
