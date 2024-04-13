package com.example.BlogApp.API.Entity;

import jakarta.persistence.*;

@Entity(name = "images")
public class ImagesEntity {
    /*
    *  "id"				: "im1"
    "articleId" 		: "art1" -- FK with the article table pk
    "name"				: "cats"
    "url"				: "https://imageurl.com/cats.png"
    * */

    @Id
    String id ;

    String name ;

    @Column(nullable = false)
    String url ;


    //An article can have the multiple images, or multiple image can have in the one articles.
    @ManyToOne
    @JoinColumn(name = "article_id")
    private ArticlesEntity articles ;

}
