package com.example.nikson.mapper;

import com.example.nikson.config.PasswordEncryptor;
import com.example.nikson.entity.User;
import com.example.nikson.payload.UserLoginRequest;
import com.example.nikson.payload.UserLoginResponse;
import com.example.nikson.payload.UserRegisterRequest;
import com.example.nikson.payload.UserRegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final PasswordEncryptor passwordEncryptor;

    public UserMapper(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public User mapFromUserRegisterRequest(UserRegisterRequest userRegisterRequest) {
        var user = new User();
        user.setName(userRegisterRequest.getName());
        user.setEmail(userRegisterRequest.getEmail());
        user.setPassword(passwordEncryptor.encodePassword(userRegisterRequest.getPassword()));
        user.setVerified(false);
        return user;
    }

    public UserRegisterResponse mapFromUser(User user) {
        var userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setId(user.getId());
        return userRegisterResponse;
    }

    //Return user for comparison to DB
    public User mapFromUserLoginRequest(UserLoginRequest userLoginRequest) {
        var user = new User();
        user.setEmail(userLoginRequest.getEmail());
        user.setPassword(passwordEncryptor.encodePassword(userLoginRequest.getPassword()));
        return user;
    }

    public UserLoginResponse mapUsingUserName(String name) {
        var userLoginResponse = new UserLoginResponse();
        userLoginResponse.setName(name);
        return userLoginResponse;
    }
}
