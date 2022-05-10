package com.tunder.user.repositories;

import com.tunder.user.models.LoginModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<LoginModel, Long>{
    
}
