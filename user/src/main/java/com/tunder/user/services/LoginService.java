package com.tunder.user.services;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;

import com.tunder.user.models.LoginModel;
import com.tunder.user.models.UserModel;
import com.tunder.user.repositories.LoginRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public LoginModel login(UserModel user){
        byte[] randomBytes = new byte[64];
        secureRandom.nextBytes(randomBytes);
        LoginModel login = new LoginModel();
        login.setToken(base64Encoder.encodeToString(randomBytes));
        login.setUserID(user.getId());
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        login.setAccessDate(timestamp);
        return loginRepository.save(login);
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
