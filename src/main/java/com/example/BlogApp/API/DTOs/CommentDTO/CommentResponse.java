package com.example.BlogApp.API.DTOs.CommentDTO;

import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleResponse;
import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;

public class CommentResponse {
    public CommentResponse() {}
    String id ;
    String content ;
    UserResponse author ;

    public ArticleResponse getArticle() {
        return article;
    }

    public void setArticle(ArticleResponse article) {
        this.article = article;
    }

    ArticleResponse article ;
    long likes;
    long dislikes ;
    String createdAt ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserResponse getAuthor() {
        return author;
    }

    public void setAuthor(UserResponse author) {
        this.author = author;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }

    public long getDislikes() {
        return dislikes;
    }

    public void setDislikes(long dislikes) {
        this.dislikes = dislikes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
