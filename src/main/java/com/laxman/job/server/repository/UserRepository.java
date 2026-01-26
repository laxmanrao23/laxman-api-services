package com.laxman.job.server.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.laxman.job.server.model.User;

public interface  UserRepository extends  JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
}
