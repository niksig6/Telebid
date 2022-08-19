package com.example.nikson.service;

import com.example.nikson.exception.VerifiedUserNotFoundException;
import com.example.nikson.payload.UserLoginRequest;
import com.example.nikson.payload.UserLoginResponse;
import com.example.nikson.payload.UserRegisterRequest;
import com.example.nikson.payload.UserRegisterResponse;


public interface UserService {

    UserRegisterResponse save(UserRegisterRequest userRegisterRequest);

    UserLoginResponse find(UserLoginRequest userLoginRequest) throws VerifiedUserNotFoundException;

    void verify(Integer id);
}
