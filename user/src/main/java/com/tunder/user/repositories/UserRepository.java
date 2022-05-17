package com.tunder.user.repositories;

import java.util.Optional;

import com.tunder.user.models.UserModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);

}
