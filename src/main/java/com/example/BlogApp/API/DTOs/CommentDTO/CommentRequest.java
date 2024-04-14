package com.example.BlogApp.API.DTOs.CommentDTO;

public class CommentRequest {
    String id;
    String content;
    long likes;
    long dislikes;
    String authorId;
    String articleId;

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

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String author) {
        this.authorId = author;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String article) {
        this.articleId = article;
    }
}
