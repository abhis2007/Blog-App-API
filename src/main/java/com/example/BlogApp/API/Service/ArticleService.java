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
    UserService userService;
    @Autowired
    private ArticleRepo articleRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<ArticlesEntity> articles = new ArrayList<>();
        articleRepo.findAll().forEach(articles::add);

        //Map it to the article response DTO
        List<ArticleResponse> responseDTO = ArticleEntityToResponseDTOMapper(articles);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<ArticleResponse> addNewArticle(ArticlesEntity newArticle) {
        ArticlesEntity savedArticle = articleRepo.save(newArticle);

        // map the response into the Article Response DTO
        ArticleResponse responseDTO = modelMapper.map(savedArticle, ArticleResponse.class);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<ArticleResponse> getArticleById(String articleId) {
        ArticlesEntity savedArticle = articleRepo.findById(articleId).get();

        // map it to the Article Response DTO
        ArticleResponse articleResponse = modelMapper.map(savedArticle, ArticleResponse.class);
        articleResponse.setAuthor(modelMapper.map(savedArticle.getUser(), UserResponse.class));
        return ResponseEntity.ok(articleResponse);
    }

    public ResponseEntity<ArticleResponse> updateArticleById(ArticleRequest newArticle) {
        ArticlesEntity existingArticle = articleRepo.findById(newArticle.getId()).get();

        ArticlesEntity articleUpdated = modelMapper.map(newArticle, ArticlesEntity.class);
        UserResponse author = userService.getUserById(newArticle.getAuthorId()).getBody();
        articleUpdated.setUser(modelMapper.map(author, UsersEntity.class));

        //Save the changes
        ArticlesEntity savedArticle = articleRepo.save(articleUpdated);
        ArticleResponse responseDTO = modelMapper.map(savedArticle, ArticleResponse.class);

        // set the author,tag,image in response
        responseDTO.setAuthor(modelMapper.map(savedArticle.getUser(), UserResponse.class));
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<ArticleResponse> deleteArticleById(String articleId){
        //Get the article
        ArticlesEntity articleToDelete = articleRepo.findById(articleId).get() ;// could have used getArticleById but here as response id going to be returned to user hence, we can directly call to repo.
        articleRepo.delete(articleToDelete);

        // return the deleted article in ArticleResponse
        ArticleResponse responseDTO = modelMapper.map(articleToDelete, ArticleResponse.class) ;

        //set author, tag and image
        responseDTO.setAuthor(modelMapper.map(articleToDelete.getUser(), UserResponse.class));

        return ResponseEntity.ok(responseDTO) ;

    }

    public List<ArticleResponse> ArticleEntityToResponseDTOMapper(List<ArticlesEntity> articles) {
        List<ArticleResponse> responseDTO = new ArrayList<>();
        for (ArticlesEntity article : articles) {
            ArticleResponse currArticle = modelMapper.map(article, ArticleResponse.class);
            currArticle.setAuthor(modelMapper.map(article.getUser(), UserResponse.class));
            responseDTO.add(currArticle);
        }
        return responseDTO;
    }

    public List<ArticlesEntity> ArticleRequestDTOToEntityMapper(List<ArticleRequest> articles) {
        List<ArticlesEntity> responseDTO = new ArrayList<>();
        for (ArticleRequest articleReq : articles) {
            responseDTO.add(modelMapper.map(articleReq, ArticlesEntity.class));
        }
        return responseDTO;
    }
}
