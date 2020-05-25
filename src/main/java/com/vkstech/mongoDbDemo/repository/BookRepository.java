package com.vkstech.mongoDbDemo.repository;

import com.vkstech.mongoDbDemo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {
}
