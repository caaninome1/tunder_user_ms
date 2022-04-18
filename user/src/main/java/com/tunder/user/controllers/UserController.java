package com.tunder.user.controllers;

import java.util.ArrayList;
import java.util.Optional;

import com.tunder.user.models.UserModel;
import com.tunder.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public ArrayList<UserModel> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public UserModel saveUser(@RequestBody UserModel user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public Optional<UserModel> getById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }
    
}
