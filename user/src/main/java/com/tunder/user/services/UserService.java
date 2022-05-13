package com.tunder.user.services;

import java.util.ArrayList;
import java.util.Optional;

import com.tunder.user.models.UserModel;
import com.tunder.user.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    

    public ArrayList<UserModel> getAllUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user) {
        user.encryptPassword();
        return userRepository.save(user);
    }

    public Optional<UserModel> getById(Long id){
        return userRepository.findById(id);
    }
}
