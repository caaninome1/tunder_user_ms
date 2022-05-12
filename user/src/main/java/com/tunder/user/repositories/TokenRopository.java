package com.tunder.user.repositories;

import java.util.Optional;

import com.tunder.user.models.TokenModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRopository extends CrudRepository <TokenModel, Long>{
    
    Optional<TokenModel> findByuserID (Long ID);
}
