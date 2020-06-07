package com.vkstech.mongoDbDemo.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.vkstech.mongoDbDemo.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryBuilderService {

    Logger LOGGER = LoggerFactory.getLogger(QueryBuilderService.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    //'is' Query
    public void isQuery() {
        LOGGER.info("QueryBuilderService :: isQuery");
        Query query = new Query();
        query.addCriteria(Criteria.where("bookName").is("Harry Potter"));
        List<Book> books = mongoTemplate.find(query, Book.class);
        books.forEach(System.out::println);
    }

    //'regex' Query
    public void regexQuery() {
        LOGGER.info("QueryBuilderService :: regexQuery");
        Query query = new Query(Criteria.where("bookName").regex("^H"));
        List<Book> books = mongoTemplate.find(query, Book.class);
        books.forEach(System.out::println);
    }

    //'lt', 'gt', 'lte' and 'gte' query
    public void ltAndGtQuery() {
        LOGGER.info("QueryBuilderService :: ltAndGtQuery");
        Query query = new Query();
        query.addCriteria(Criteria.where("id").gt(0).lt(100));
        List<Book> books = mongoTemplate.find(query, Book.class);
        books.forEach(System.out::println);
    }

    //sort
    public void sortQuery() {
        LOGGER.info("QueryBuilderService :: sortQuery");
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "bookName", "authorName"));
        List<Book> books = mongoTemplate.find(query, Book.class);
        books.forEach(System.out::println);
    }

    //pageable
    public void pageableQuery() {
        LOGGER.info("QueryBuilderService :: pageableQuery");
        Query query = new Query();
        query
                .with(Sort.by(Sort.Direction.ASC, "id"))
                .with(PageRequest.of(0, 2));
        List<Book> books = mongoTemplate.find(query, Book.class);
        books.forEach(System.out::println);
    }

    //update
    public void updateQuery() {
        LOGGER.info("QueryBuilderService :: updateQuery");

        Query query = new Query(Criteria.where("id").is(2L));

        Update update = new Update();
        update.set("bookName", "Harry Potter : Chamber of Secrets");

        UpdateResult result = mongoTemplate.updateFirst(query, update, Book.class);
        System.out.println("Updation Count : " +  result.getModifiedCount());

        Book book = mongoTemplate.findOne(query, Book.class);
        System.out.println(book);
    }

    //delete
    public void deleteQuery() {
        LOGGER.info("QueryBuilderService :: deleteQuery");

        Query query = new Query(Criteria.where("id").is(105L));

        DeleteResult deleteResult = mongoTemplate.remove(query,Book.class);
        System.out.println("Deleted Count : " +  deleteResult.getDeletedCount());
    }
}
