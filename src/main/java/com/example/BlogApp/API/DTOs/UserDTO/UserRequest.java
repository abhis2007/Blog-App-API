package com.example.BlogApp.API.DTOs.UserDTO;

public class UserRequest {
    public UserRequest(){}
    private String id ;
    private String name ;
    private String password ;
    private String biography ;
    private String email ;
    private String qualification ;

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
    public void setPassword(String password) {
        this.password = password ;
    }
    public String getPassword() {
        return password ;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
