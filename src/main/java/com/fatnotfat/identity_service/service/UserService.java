package com.fatnotfat.identity_service.service;

import com.fatnotfat.identity_service.dto.request.UserCreationRequest;
import com.fatnotfat.identity_service.dto.request.UserUpdateRequest;
import com.fatnotfat.identity_service.dto.response.APIResponse;
import com.fatnotfat.identity_service.dto.response.UserResponse;
import com.fatnotfat.identity_service.entity.User;
import com.fatnotfat.identity_service.exception.BadRequestException;
import com.fatnotfat.identity_service.exception.ErrorCode;
import com.fatnotfat.identity_service.exception.NotFoundException;
import com.fatnotfat.identity_service.mapper.UserMapper;
import com.fatnotfat.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.BeanMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;


    public User createUserRequest(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new BadRequestException(ErrorCode.USER_EXISTED);
        }
        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }


    public void deleteUserRequest(String id){
        UserResponse user = getUserById(id);
        userRepository.deleteById(id);
    }


    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public UserResponse getUserById(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOTFOUND)));
    }


    public UserResponse updateUserRequest(String id, UserUpdateRequest request){
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOTFOUND));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }
}
