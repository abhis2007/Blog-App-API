package com.example.BlogApp.API.Controller;

import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleRequest;
import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleResponse;
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
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<List<ArticleResponse>> getArticles() {
        return articleService.getAllArticles();
    }

    //    @PostMapping("/")
//    public ResponseEntity<ArticlesEntity> getArticles(@RequestBody ArticlesEntity newArticle) {
//        return articleService.addNewArticle(newArticle) ;
//    }
    @PostMapping("/")
    public ResponseEntity<ArticlesEntity> addNewArticle(@RequestBody ArticleRequest newArticle) {
        // mapping it into the article entity from article Request
        ArticlesEntity articleEntity = modelMapper.map(newArticle, ArticlesEntity.class);

        // Add all the comments with this articles
        List<String> allCommentIds = newArticle.getCommentId();
        List<CommentResponse> commentResponseDTO = commentService.getCommentByIds(allCommentIds);

        // map the comment response with the comment entity.
        List<CommentsEntity> allComments = commentService.CommentResponseDTOToEntityMapper(commentResponseDTO);
        articleEntity.setCommentsList(allComments);

//        List<CommentsEntity> commentsEntities = new ArrayList<>() ;
//        for(CommentResponse cres : commentResponseDTO){
//            System.out.println("Comment " + cres.getContent()+" Article: " + cres.getArticleId() + "by " + cres.getAuthorId());
//            //build article entity
//            CommentsEntity comment = new CommentsEntity() ;
//            modelMapper.map(cres, comment);
//            //UserRequest
////            UserRequest userRequestDTO = new UserRequest() ;
////            userRequestDTO.setId(cres.getAuthorId());
//            UserResponse userResponse = userService.getUserById(cres.getAuthorId()).getBody();
//            UsersEntity usersEntity = new UsersEntity() ;
//            modelMapper.map(userResponse, usersEntity);
//            comment.setUser(usersEntity);
//
//            commentsEntities.add(comment) ;
//        }

//        UserRequest requestDTO = new UserRequest() ;
//        requestDTO.setId(newArticle.getAuthorId());
//        UserResponse responseDTO = userService.getUserById(newArticle.getAuthorId()).getBody() ;

        // Build article Entity
//        ArticlesEntity article = new ArticlesEntity() ;
//        modelMapper.map(newArticle, article);

        // Add the article author details

        UserResponse userResponseDTO = userService.getUserById(newArticle.getAuthorId()).getBody();
        UsersEntity articleAuthorDetails = modelMapper.map(userResponseDTO, UsersEntity.class);
//        UsersEntity user = new UsersEntity() ;
//        modelMapper.map(responseDTO, user);
//        article.setUser(user);
        articleEntity.setUser(articleAuthorDetails);
        System.out.println("Id: " + articleEntity.getUser().getId() + " name: " + articleEntity.getUser().getName());

        //Comments
//      article.setCommentsList(commentsEntities);

        return articleService.addNewArticle(articleEntity);
    }

//    @PatchMapping("/")
//    public void updateAnArticle(@RequestBody Articl){
//
//    }
}
