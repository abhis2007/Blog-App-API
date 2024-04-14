package com.example.BlogApp.API.DTOs.ArticlesDTO;

import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;

import java.util.List;

public class ArticleResponse {
    private String id;
    private String title;
    private String subtitle;
    private String content;
    private UserResponse author;
    //    private List<String> commentId ;
    private List<String> tagIds;
    private List<String> imageIds;
    private long likes;
    private long dislikes;
    private String createdAt;
    public ArticleResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

//    public List<String> getCommentId() {
//        return commentId;
//    }
//
//    public void setCommentId(List<String> commentId) {
//        this.commentId = commentId;
//    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public List<String> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<String> imageIds) {
        this.imageIds = imageIds;
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
