package com.vkstech.mongoDbDemo.repository;

import com.vkstech.mongoDbDemo.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, Long> {

    /** value (default) :
     *The value needs a query in JSON format.
     */
    @Query("{authorName:'?0'}")
    List<Book> findByAuthorName(String author);


    /** $regex :
     * Queries using regular expression
     * The usage is also exactly the same:
     * List<Book> users = bookRepository.findUsersByRegexpName("^A");
     */
    @Query("{ 'bookName' : { $regex: ?0 } }")
    List<Book> findUsersByRegexpName(String regexp);


    /** Using gt (greater than) and lt (lower than)
     * For gt (>) we need to use $gt and for lt (<), we need to use $lt.
     */
    @Query("{noOfPages : {$gt: ?0}}")
    List<Book> findBooksGtThanNoOfPages(int noOfPages);

    @Query("{authorName : ?0, noOfPages : {$lt: ?1}}")
    List<Book> findBooksByWriterAndLtThanNoOfPages(String writer, int noOfPages);


    /**  Operator: AND, OR
     * MongoDB uses $or for OR operator and it uses $and for AND operator
     */
    @Query("{$or : [{authorName: ?0}, {category : ?1}]}")
    List<Book> findBooksByWriterOrCategory(String writer, String category);

    @Query("{$and : [{$or : [{noOfPages: {$gt: 275}}, {noOfPages : {$lt: 200}}]}, {$or : [{id: {$gt: 103}}, {id : {$lt: 102}}]}]}")
    List<Book> findBestBooks();


    /**  fields
     * The fields element of the @Query defines the fields to be returned for the given query.
     * The domain object will keep value only in specified fields and @Id field and rest of the fields will be null.
     * We need to set fields value with 1 to populate its value.
     * The fields of domain which has been set to 0 or not specified will have null value.
     */
    @Query(value = "{authorName : ?0, category : ?1}", fields = "{ 'bookName' : 1, 'authorName' : 1}")
    List<Book> findBooksWithCertainFields(String authorName, String category);

    /**  count
     * The count element of the @Query decides whether the query defined should be executed as count projection.
     * 'count' is assigned as Boolean value.
     * When we specify true, the method returns count of the query result.
     */
    @Query(value = "{category : ?0}", count = true)
    Integer findBookCountByCategory(String category);

    /**  exists
     * The exists element of the @Query decides whether the query defined should be executed as exists projection.
     * 'exists' is assigned as Boolean value.
     * When we specify true, the method returns true/false value.
     * If methods returns false, it means query result count is zero.
     */
    @Query(value = "{authorName : ?0}", exists = true)
    Boolean isBooksAvailableByWriter(String author);

    /**  sort
     * The sort element of the @Query defines the default sort order for the given query.
     * The value -1 is used for descending order and 1 is used for ascending order.
     */
    @Query(value = "{authorName : ?0}", sort = "{bookName : 1}")
    List<Book> findBooksByWriter(String author);

    @Query(value = "{category : ?0}", sort = "{bookName : -1}")
    List<Book> findBooksByCategory(String category);

    /**  delete
     * The delete element of the @Query decides whether the query should delete matching documents.
     * 'delete' is assigned as Boolean value.
     * When we specify true, the method deletes the query matching data and returns count of deleted rows.
     */
    @Query(value = "{category : ?0}", delete = true)
    Long deleteBooksByCategory(String category);

    /** collation
     * The collation element of the @Query defines the collation to apply when executing the query.
     */
    @Query(value = "{category : ?0}", collation = "en_US")
    List<Book> findBooksByCategoryWithCollation(String category);

    @Query(value = "{authorName : ?0}", collation = "{ 'locale' : '?1' }")
    List<Book> findBooksByWriter(String author, String collation);

}
