package com.example.BlogApp.API.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tags")
public class TagsEntity {
    /*
    * "id"				: "tag1"
    "name"				: "java"
    * */

    @Id
    String id;
    String name;

    // a tag can have multiple articles, and articles can have the multiple tags
    // Hence have the Many to Many relationship.

    @ManyToMany
    @JoinTable(
            name = "article_tag",
            joinColumns = @JoinColumn(name = "tag_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "article_id", nullable = false) //Inverse refer the PK of the right side table(tag->article)
    )
    private List<ArticlesEntity> articlesList;
}
