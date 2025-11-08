package com.achamigos.backend.repository;

import com.achamigos.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {


    User findByEmail(String email);


    User findByUserLogin(String userLogin);


    Optional<User> findById(String id);
}
