package com.example.BlogApp.API.Service;

import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleResponse;
import com.example.BlogApp.API.DTOs.ArticlesDTO.ArticleResponse_Comment;
import com.example.BlogApp.API.DTOs.CommentDTO.CommentRequest;
import com.example.BlogApp.API.DTOs.CommentDTO.CommentResponse;
import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;
import com.example.BlogApp.API.Entity.ArticlesEntity;
import com.example.BlogApp.API.Entity.CommentsEntity;
import com.example.BlogApp.API.Entity.UsersEntity;
import com.example.BlogApp.API.Repository.CommentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    UserService userService;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<CommentsEntity> comments = new ArrayList<>();

        commentRepo.findAll().forEach(comments::add);
        List<CommentResponse> responseDTO = new ArrayList<>();
        for (CommentsEntity comment : comments) {
            CommentResponse currResponseDTO = modelMapper.map(comment, CommentResponse.class);
            //Set the author
            currResponseDTO.setAuthor(modelMapper.map(comment.getUser(), UserResponse.class));

            //set the articles
            ArticleResponse_Comment articleResponse = modelMapper.map(comment.getArticles(), ArticleResponse_Comment.class);
            articleResponse.setAuthorId(comment.getArticles().getUser().getId());
            currResponseDTO.setArticle(articleResponse);

            responseDTO.add(currResponseDTO);
        }
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<CommentResponse> addNewComment(CommentsEntity newComment) {
        CommentsEntity savedComment = commentRepo.save(newComment);

        //Map it to the Comment Response DTO
        CommentResponse responseDTO = modelMapper.map(savedComment, CommentResponse.class);

        //Set the Author for the current comment, as comment Entity don't have the Author field it has user[in future we may need to rename it to author in entity]
        String authorId = newComment.getUser().getId();
        UserResponse userRes = userService.getUserById(authorId).getBody();
        responseDTO.setAuthor(userRes);

        // similarly set the articles for the current comment.
        String articleId = newComment.getArticles().getId();
        ArticleResponse artResponse = articleService.getArticleById(articleId).getBody();

        //We need to map it to the articleResponse_Comment to avoid the comment data again into the article response.
        responseDTO.setArticle(modelMapper.map(artResponse, ArticleResponse_Comment.class));

        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<CommentResponse> getCommentById(String commentId) {
        CommentsEntity comment = commentRepo.findById(commentId).get();

        // convert into the response DTO
        CommentResponse responseDTO = modelMapper.map(comment, CommentResponse.class);
        String userId = comment.getUser().getId();
        responseDTO.setAuthor(userService.getUserById(userId).getBody());

        // similarly set the articles for the current comment.
        String articleId = comment.getArticles().getId();
        ArticleResponse artResponse = articleService.getArticleById(articleId).getBody();

        //We need to map it to the articleResponse_Comment to avoid the comment data again into the article response.
        responseDTO.setArticle(modelMapper.map(artResponse, ArticleResponse_Comment.class));

        return ResponseEntity.ok(responseDTO);
    }

    public List<CommentResponse> getCommentByIds(List<String> commentIds) {
        List<CommentsEntity> allComments = new ArrayList<>();
        commentRepo.findAllById(commentIds).forEach(allComments::add);

        // convert into the response DTO
        List<CommentResponse> responseDTO = new ArrayList<>();
        for (CommentsEntity currComment : allComments) {
            CommentResponse currResponseDTO = new CommentResponse();
            UsersEntity userAuthor = currComment.getUser();

            currResponseDTO.setAuthor(modelMapper.map(userAuthor, UserResponse.class));

            modelMapper.map(currComment, currResponseDTO);
            responseDTO.add(currResponseDTO);
        }
        return responseDTO;
    }

    public ResponseEntity<CommentResponse> updateCommentById(CommentRequest commentToUpdate) {
        CommentsEntity commentUpdated = modelMapper.map(commentToUpdate, CommentsEntity.class);

        UserResponse authorDetails = userService.getUserById(commentToUpdate.getAuthorId()).getBody();
        commentUpdated.setUser(modelMapper.map(authorDetails, UsersEntity.class));

        ArticleResponse articleDetails = articleService.getArticleById(commentToUpdate.getArticleId()).getBody();
        commentUpdated.setArticles(modelMapper.map(articleDetails, ArticlesEntity.class));

        //Save to db
        CommentResponse responseDTO = modelMapper.map(commentRepo.save(commentUpdated), CommentResponse.class);

        authorDetails = userService.getUserById(commentToUpdate.getAuthorId()).getBody();
        responseDTO.setAuthor(authorDetails);

        articleDetails = articleService.getArticleById(commentToUpdate.getArticleId()).getBody();
        responseDTO.setArticle(modelMapper.map(articleDetails, ArticleResponse_Comment.class));

        return ResponseEntity.ok(responseDTO);
    }

    public List<CommentsEntity> CommentResponseDTOToEntityMapper(List<CommentResponse> comments) {
        List<CommentsEntity> responseDTO = new ArrayList<>();
        for (CommentResponse commentRes : comments) {
            responseDTO.add(modelMapper.map(commentRes, CommentsEntity.class));
        }
        return responseDTO;
    }

}
