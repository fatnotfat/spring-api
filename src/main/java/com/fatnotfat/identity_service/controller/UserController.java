package com.fatnotfat.identity_service.controller;

import com.fatnotfat.identity_service.dto.request.UserCreationRequest;
import com.fatnotfat.identity_service.dto.request.UserUpdateRequest;
import com.fatnotfat.identity_service.entity.User;
import com.fatnotfat.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping({"/{userId}"})
    public User getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }

    @PostMapping()
    public User createUser(@RequestBody @Valid UserCreationRequest user) {
        return userService.createUserRequest(user);
    }

    @PutMapping({"/{userId}"})
    public User updateUser(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId) {
        return userService.updateUserRequest(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String id) {
        userService.deleteUserRequest(id);
        return "User deleted";
    }

}
