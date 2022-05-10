package com.tunder.user.services;

import com.tunder.user.models.LoginModel;
import com.tunder.user.models.UserModel;
import com.tunder.user.repositories.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public LoginModel login(UserModel user){
        //TODO
        return null;
    }

    public boolean isValidToken(LoginModel login){
        //TODO
        return false;
    }

    public LoginModel refeshToken(LoginModel login){
        //TODO
        return null;
    }
    
}
