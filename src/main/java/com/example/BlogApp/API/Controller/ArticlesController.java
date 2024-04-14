package com.example.BlogApp.API.Controller;

import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleRequest;
import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleResponse;
import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;
import com.example.BlogApp.API.Entity.ArticlesEntity;
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

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> getArticleById(@PathVariable String articleId) {
        return articleService.getArticleById(articleId);
    }

    @PostMapping("/")
    public ResponseEntity<ArticleResponse> addNewArticle(@RequestBody ArticleRequest newArticle) {
        // mapping it into the article entity from article Request
        ArticlesEntity articleEntity = modelMapper.map(newArticle, ArticlesEntity.class);

        // Add the article author/user details
        UserResponse userResponseDTO = userService.getUserById(newArticle.getAuthorId()).getBody();
        UsersEntity articleAuthorDetails = modelMapper.map(userResponseDTO, UsersEntity.class);
        articleEntity.setUser(articleAuthorDetails);

        ArticleResponse responseDTO = articleService.addNewArticle(articleEntity).getBody();
        responseDTO.setAuthor(modelMapper.map(articleEntity.getUser(), UserResponse.class));

        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/")
    public ResponseEntity<ArticleResponse> updateAnArticle(@RequestBody ArticleRequest articleToUpdate) {
        return articleService.updateArticleById(articleToUpdate);
    }
}
