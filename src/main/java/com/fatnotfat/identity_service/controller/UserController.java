package com.fatnotfat.identity_service.controller;

import com.fatnotfat.identity_service.dto.request.UserCreationRequest;
import com.fatnotfat.identity_service.dto.request.UserUpdateRequest;
import com.fatnotfat.identity_service.dto.response.APIResponse;
import com.fatnotfat.identity_service.dto.response.UserResponse;
import com.fatnotfat.identity_service.entity.User;
import com.fatnotfat.identity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;


    @GetMapping()
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping({"/{userId}"})
    public APIResponse<UserResponse> getUserById(@PathVariable("userId") String userId) {
        APIResponse<UserResponse> response = new APIResponse<>();
        response.setCode(200);
        response.setMessage("Get successfully!");
        response.setResult(userService.getUserById(userId));
        return response;
    }

    @PostMapping()
    public APIResponse<User> createUser(@RequestBody @Valid UserCreationRequest user) {
        APIResponse<User> response = new APIResponse<>();
        response.setCode(200);
        response.setMessage("Create successfully!");
        response.setResult(userService.createUserRequest(user));
        return response;
    }

    @PutMapping({"/{userId}"})
    public APIResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request, @PathVariable("userId") String userId) {
        APIResponse<UserResponse> response = new APIResponse<>();
        response.setCode(200);
        response.setMessage("Update successfully!");
        response.setResult(userService.updateUserRequest(userId, request));
        return response;
    }

    @DeleteMapping("/{userId}")
    public APIResponse<User> deleteUser(@PathVariable("userId") String id) {
        APIResponse<User> response = new APIResponse<>();
        response.setCode(200);
        response.setMessage("Delete successfully!");
        userService.deleteUserRequest(id);
        return response;
    }

}
