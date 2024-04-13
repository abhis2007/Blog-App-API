package com.example.BlogApp.API.Service;

import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleRequest;
import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleResponse;
import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;
import com.example.BlogApp.API.Entity.ArticlesEntity;
import com.example.BlogApp.API.Entity.UsersEntity;
import com.example.BlogApp.API.Repository.ArticleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepo articleRepo ;
    @Autowired
    private ModelMapper modelMapper ;

    public ResponseEntity<List<ArticleResponse>> getAllArticles() {

        List<ArticlesEntity> articles = new ArrayList<>() ;
        articleRepo.findAll().forEach(articles::add);

        //Map it to the article response DTO
        List<ArticleResponse> responseDTO = ArticleEntityToResponseDTOMapper(articles) ;

        return ResponseEntity.ok(responseDTO) ;
    }
    public ResponseEntity<ArticlesEntity> addNewArticle(ArticlesEntity newArticle) {
        System.out.println("adding");
        ArticlesEntity savedArticle = articleRepo.save(newArticle) ;
        System.out.println("added");
        return ResponseEntity.ok(savedArticle) ;
    }
    public ResponseEntity<ArticleResponse> getArticleById(String articleId) {

        ArticlesEntity savedArticle = articleRepo.findById(articleId).get() ;

        // map it to the Article Response DTO
        ArticleResponse articleResponse = modelMapper.map(savedArticle, ArticleResponse.class);
        return ResponseEntity.ok(articleResponse) ;
    }

//    public ResponseEntity<ArticlesEntity> addNewArticle(ArticleRequest newArticle) {
//
//        ArticlesEntity savedArticle = articleRepo.save(newArticle) ;
//
//        return ResponseEntity.ok(savedArticle) ;
//    }

    public List<ArticleResponse> ArticleEntityToResponseDTOMapper(List<ArticlesEntity> articles){
        List<ArticleResponse> responseDTO = new ArrayList<>() ;
        for(ArticlesEntity articleEntity : articles ){
            responseDTO.add(modelMapper.map(articleEntity, ArticleResponse.class)) ;
        }
        return responseDTO ;
    }
    public List<ArticlesEntity> ArticleRequestDTOToEntityMapper(List<ArticleRequest> articles){
        List<ArticlesEntity> responseDTO = new ArrayList<>() ;
        for(ArticleRequest articleReq : articles ){
            responseDTO.add(modelMapper.map(articleReq, ArticlesEntity.class)) ;
        }
        return responseDTO ;
    }
}
