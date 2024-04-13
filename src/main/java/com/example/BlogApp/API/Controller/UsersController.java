package com.example.BlogApp.API.Controller;

import com.example.BlogApp.API.DTOs.UserDTO.UserRequest;
import com.example.BlogApp.API.DTOs.UserDTO.UserResponse;
import com.example.BlogApp.API.Entity.UsersEntity;
import com.example.BlogApp.API.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/")
    public ResponseEntity<UserResponse> addNewUser(@RequestBody UserRequest newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        //Need to convert back into the User Entity
        UsersEntity user = new UsersEntity();
        modelMapper.map(newUser, user);

        return userService.addNewUser(user);
    }

    @GetMapping("/{UserId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String UserId) {
        //Map it into the user request DTO.
        UserRequest request = new UserRequest();
        request.setId(UserId);

        return userService.getUserById(request.getId());
    }

    @PatchMapping("/")
    public ResponseEntity<UserResponse> updateUserById(@RequestBody UserRequest userToUpdate) {
        return userService.updateUser(userToUpdate);
    }
}
