package com.example.BlogApp.API.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "Articles")
public class ArticlesEntity {
    @Id
    String id;
    String title;
    String subTitle;
    String content;
    long likes;
    long dislikes;
    String createdAt;
    // An article can have the multiple comments - 1:N with Articles -> Comments
    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    List<CommentsEntity> commentsList;
    //Building FK relationship with Users entity.
    //An article can contains multiple images
    @OneToMany(mappedBy = "articles", cascade = CascadeType.ALL)
    List<ImagesEntity> images;
    //An article can have the multiple tag and tag can have the multiple articles.
    @ManyToMany(mappedBy = "articlesList", cascade = CascadeType.ALL)
    List<TagsEntity> tags;
    // user will be referred in the Users Entity to create the FK
    @ManyToOne
    @JoinColumn(name = "author", nullable = false)
    // It says to JPA that we have a column(need to create by JPA) called user_id in Articles Entity which will keep id(pk) of the Users entity.
            UsersEntity user;

    public ArticlesEntity() {
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
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

    public List<CommentsEntity> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<CommentsEntity> commentsList) {
        this.commentsList = commentsList;
    }

    public List<ImagesEntity> getImages() {
        return images;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public List<TagsEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagsEntity> tags) {
        this.tags = tags;
    }
}
