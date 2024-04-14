package com.example.BlogApp.API.Controller;

import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleResponse;
import com.example.BlogApp.API.DTOs.CommentDTO.CommentRequest;
import com.example.BlogApp.API.DTOs.CommentDTO.CommentResponse;
import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;
import com.example.BlogApp.API.Entity.ArticlesEntity;
import com.example.BlogApp.API.Entity.CommentsEntity;
import com.example.BlogApp.API.Entity.UsersEntity;
import com.example.BlogApp.API.Service.ArticleService;
import com.example.BlogApp.API.Service.CommentService;
import com.example.BlogApp.API.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<List<CommentResponse>> getCommentById(@PathVariable String commentId) {
        return commentService.getAllComments();
    }

    @PostMapping("/")
    public ResponseEntity<CommentResponse> addNewComment(@RequestBody CommentRequest comment) {

        CommentsEntity commentsEntity = modelMapper.map(comment, CommentsEntity.class);
        //set the user in the comment entity details using userId
        String userId = comment.getAuthorId();
        UserResponse userRes = userService.getUserById(userId).getBody();
        UsersEntity usersEntity = modelMapper.map(userRes, UsersEntity.class);
        commentsEntity.setUser(usersEntity);

        //set the article in the comment entity
        String articleId = comment.getArticleId();
        ArticleResponse artResponse = articleService.getArticleById(articleId).getBody();
        ArticlesEntity articlesEntity = modelMapper.map(artResponse, ArticlesEntity.class);
        commentsEntity.setArticles(articlesEntity);

        return commentService.addNewComment(commentsEntity);
    }

    @PatchMapping("/")
    public ResponseEntity<CommentResponse> getCommentById(@RequestBody CommentRequest commentToUpdate) {

        return commentService.updateCommentById(commentToUpdate);
    }
}
