package com.example.nikson.service;

import com.example.nikson.exception.VerifiedUserNotFoundException;
import com.example.nikson.mapper.UserMapper;
import com.example.nikson.payload.UserLoginRequest;
import com.example.nikson.payload.UserLoginResponse;
import com.example.nikson.payload.UserRegisterResponse;
import com.example.nikson.payload.UserRegisterRequest;
import com.example.nikson.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.mailSender = mailSender;
    }
    @Override
    public UserRegisterResponse save(UserRegisterRequest userRegisterRequest) {
        var user = userMapper.mapFromUserRegisterRequest(userRegisterRequest);
        user = userRepository.save(user);
        return userMapper.mapFromUser(user);
    }

    @Override
    public UserLoginResponse find(UserLoginRequest userLoginRequest) throws VerifiedUserNotFoundException {
        var user = userMapper.mapFromUserLoginRequest(userLoginRequest);
        var fetchedUserName = userRepository.findByEmailAndPassword(user).orElseThrow(VerifiedUserNotFoundException::new);
        return userMapper.mapUsingUserName(fetchedUserName);
    }

    @Override
    public void verify(Integer id) {
        //usermapper.map from id; user.verified = true..
    }

    public void sendVerificationEmail(String toEmail, String subject, String body) {

    }
}
