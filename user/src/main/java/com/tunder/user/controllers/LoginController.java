package com.tunder.user.controllers;

import com.tunder.user.models.LoginModel;
import com.tunder.user.models.TokenModel;
import com.tunder.user.models.UserModel;
import com.tunder.user.services.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping()
    public TokenModel login(@RequestBody UserModel user){
        return loginService.login(user);
    }

    @GetMapping(path = "/token")
    public Boolean isValidToken(@RequestBody TokenModel token){
        return loginService.isValidToken(token);
    }

    @GetMapping(path = "/refresh")
    public TokenModel refreshToken(@RequestBody TokenModel token){
        return loginService.refeshToken(token);
    }
}
