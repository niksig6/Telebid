package com.example.nikson.controller;

import com.example.nikson.exception.VerifiedUserNotFoundException;
import com.example.nikson.payload.*;
import com.example.nikson.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// TODO: research about methods

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserRegisterResponse save(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userService.save(userRegisterRequest);
    }

    @PostMapping("users/confirm/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void verify(@PathVariable Integer id) {
        userService.verify(id);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserLoginResponse load(@RequestBody UserLoginRequest userLoginRequest) throws VerifiedUserNotFoundException {
        return userService.find(userLoginRequest);
    }

    @ExceptionHandler(VerifiedUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(Exception exc) {
        return new ErrorResponse(exc.getMessage());
    }
}
