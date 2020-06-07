package com.vkstech.mongoDbDemo.repository;

import com.vkstech.mongoDbDemo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

    User findByEmail(String email);

}
