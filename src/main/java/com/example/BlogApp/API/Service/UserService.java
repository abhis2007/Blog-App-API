package com.example.BlogApp.API.Service;

import com.example.BlogApp.API.DTOs.UserDTO.UserRequest;
import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;
import com.example.BlogApp.API.Entity.UsersEntity;
import com.example.BlogApp.API.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<UsersEntity> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        List<UserResponse> responseDTO = UserEntityToResponseDTOMapper(users);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<UserResponse> addNewUser(UsersEntity user) {
        UsersEntity savedUser = userRepo.save(user);

        //Convert the response into the UserResponse DTO
        UserResponse responseDTO = modelMapper.map(savedUser, UserResponse.class);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<UserResponse> getUserById(String id) {
        UsersEntity response = userRepo.findById(id).get();

        //Convert it into the UserResponse DTO
        UserResponse responseDTO = modelMapper.map(response, UserResponse.class);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<UserResponse> updateUser(UserRequest user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UsersEntity userToUpdate = modelMapper.map(user, UsersEntity.class);
        UsersEntity savedUser = userRepo.save(userToUpdate);

        // map it to the userResponse DTO
        UserResponse responseDTO = modelMapper.map(savedUser, UserResponse.class);
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<UserResponse> deleteUserById(String userId) {
        // get user
        UsersEntity userToDelete = userRepo.findById(userId).get() ;
        userRepo.delete(userToDelete);

        // map it to response
        UserResponse responseDTO = modelMapper.map(userToDelete, UserResponse.class) ;
        return ResponseEntity.ok(responseDTO) ;
    }

    public List<UserResponse> UserEntityToResponseDTOMapper(List<UsersEntity> user) {
        List<UserResponse> responseDTO = new ArrayList<>();
        for (UsersEntity userReq : user) {
            responseDTO.add(modelMapper.map(userReq, UserResponse.class));
        }
        return responseDTO;
    }

}
