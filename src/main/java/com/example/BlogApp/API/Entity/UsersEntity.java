package com.example.BlogApp.API.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "users")
public class UsersEntity {
    @Id
    String id;
    @Column(nullable = false)
    String name;
    String biography;
    String qualification;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String password;
    // A user can have multiple articles, 1->N
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Here user is the attribute in the Article table.
    private List<ArticlesEntity> articles;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CommentsEntity> comments;
    //A user can have multiple followers(which is a user itself) and can follow(a user) multiple users.
    // Hence having a N-N relationship     (Users/followee->Users/followers)
    @ManyToMany
    @JoinTable(
            name = "followee_followers",
            joinColumns = @JoinColumn(name = "followee", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "follower", nullable = false)
    )
    private List<UsersEntity> followers;
    // for other side of the Users->Users
    @ManyToMany(mappedBy = "followers")
    private List<UsersEntity> followees;

    public UsersEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
