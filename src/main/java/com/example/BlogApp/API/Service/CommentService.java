package com.example.BlogApp.API.Service;

import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleRequest;
import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleResponse;
import com.example.BlogApp.API.DTOs.CommentDTO.CommentRequest;
import com.example.BlogApp.API.DTOs.CommentDTO.CommentResponse;
import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;
import com.example.BlogApp.API.Entity.ArticlesEntity;
import com.example.BlogApp.API.Entity.CommentsEntity;
import com.example.BlogApp.API.Entity.UsersEntity;
import com.example.BlogApp.API.Repository.CommentRepo;
import org.apache.coyote.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo ;
    @Autowired
    private ArticleService articleService ;
    @Autowired
    private ModelMapper modelMapper ;

    @Autowired
    UserService userService ;

    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<CommentsEntity> comments = new ArrayList<>() ;

        commentRepo.findAll().forEach(comments::add);
        List<CommentResponse> responseDTO = new ArrayList<>() ;
        for(CommentsEntity comment : comments){
            CommentResponse currResponseDTO = modelMapper.map(comment, CommentResponse.class);
            //Set the author
            currResponseDTO.setAuthor(modelMapper.map(comment.getUser(), UserResponse.class));
            //set the articles
            currResponseDTO.setArticle(modelMapper.map(comment.getArticles(), ArticleResponse.class));

            responseDTO.add(currResponseDTO) ;
        }
        return ResponseEntity.ok(responseDTO) ;
    }

    public ResponseEntity<CommentResponse> addNewComment(CommentsEntity newComment){
        CommentsEntity savedComment = commentRepo.save(newComment) ;

        //Map it to the Comment Response DTO
        CommentResponse responseDTO = modelMapper.map(savedComment, CommentResponse.class);

        //Set the Author for the current comment, as comment Entity don't have the Author field it has user[in future we may need to rename it to author in entity]
        String authorId = newComment.getUser().getId() ;
        UserResponse userRes = userService.getUserById(authorId).getBody() ;
        responseDTO.setAuthor(userRes);

        // similarly set the articles for the current comment.
        String articleId = newComment.getArticles().getId() ;
        ArticleResponse artResponse = articleService.getArticleById(articleId).getBody();
        responseDTO.setArticle(artResponse);

        return ResponseEntity.ok(responseDTO) ;
    }

    public ResponseEntity<CommentResponse> getCommentById(String commentId){
        CommentsEntity comment = commentRepo.findById(commentId).get();

        // convert into the response DTO
        CommentResponse responseDTO = modelMapper.map(comment, CommentResponse.class);
        String userId = comment.getUser().getId() ;
        responseDTO.setAuthor(userService.getUserById(userId).getBody());

        // similarly set the articles for the current comment.
        String articleId = comment.getArticles().getId() ;
        ArticleResponse artResponse = articleService.getArticleById(articleId).getBody();
        responseDTO.setArticle(artResponse);

        return ResponseEntity.ok(responseDTO) ;
    }

    public List<CommentResponse> getCommentByIds(List<String> commentIds){
        List<CommentsEntity> allComments = new ArrayList<>() ;
        commentRepo.findAllById(commentIds).forEach(allComments::add);

        // convert into the response DTO
        List<CommentResponse> responseDTO = new ArrayList<>() ;
        for(CommentsEntity currComment : allComments) {
            CommentResponse currResponseDTO = new CommentResponse() ;
            UsersEntity userAuthor = currComment.getUser() ;

            currResponseDTO.setAuthor(modelMapper.map(userAuthor, UserResponse.class));

//            currResponseDTO.se
//            currResponseDTO.setArticleId(currComment.getArticles().getId());

            modelMapper.map(currComment, currResponseDTO);
            responseDTO.add(currResponseDTO) ;
        }
        return responseDTO ;
    }
    public List<CommentsEntity> CommentResponseDTOToEntityMapper(List<CommentResponse> comments){
        List<CommentsEntity> responseDTO = new ArrayList<>() ;
        for(CommentResponse commentRes : comments ){
            responseDTO.add(modelMapper.map(commentRes, CommentsEntity.class)) ;
        }
        return responseDTO ;
    }

}
