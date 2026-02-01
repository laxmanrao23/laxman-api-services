package com.laxman.job.server.repository;


import java.util.Optional;
//import org.springframework.data.jpa.repository.JpaRepository;
import com.laxman.job.server.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

//public interface  UserRepository extends  JpaRepository<User, Long> {
//    Optional<User> findByUsername(String username);
//
//    void deleteByUsername(String username);
//}

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void deleteByUsername(String username);
}