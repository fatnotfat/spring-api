package com.fatnotfat.identity_service.service;

import com.fatnotfat.identity_service.dto.request.UserCreationRequest;
import com.fatnotfat.identity_service.dto.request.UserUpdateRequest;
import com.fatnotfat.identity_service.dto.response.APIResponse;
import com.fatnotfat.identity_service.entity.User;
import com.fatnotfat.identity_service.exception.BadRequestException;
import com.fatnotfat.identity_service.exception.ErrorCode;
import com.fatnotfat.identity_service.exception.NotFoundException;
import com.fatnotfat.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUserRequest(UserCreationRequest request){
        User user = new User();

        if(userRepository.existsByUsername(request.getUsername())){
            throw new BadRequestException(ErrorCode.USER_EXISTED);
        }

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        User userCreated = userRepository.save(user);
        return userCreated;
    }


    public void deleteUserRequest(String id){
        User user = getUserById(id);
        userRepository.deleteById(id);
    }


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOTFOUND));
    }


    public User updateUserRequest(String id, UserUpdateRequest request){
        User user = getUserById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }
}
