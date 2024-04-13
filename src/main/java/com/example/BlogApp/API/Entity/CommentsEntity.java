package com.example.BlogApp.API.Entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity(name = "comments")
public class CommentsEntity {
    @Id
    String id ;
    @Column(nullable = false)
    String content ;
    long likes;
    long dislikes;
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

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public ArticlesEntity getArticles() {
        return articles;
    }

    public void setArticles(ArticlesEntity articles) {
        this.articles = articles;
    }

    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    UsersEntity user ;

    @ManyToOne
    @JoinColumn(name = "article_id")
    ArticlesEntity articles ;
}
