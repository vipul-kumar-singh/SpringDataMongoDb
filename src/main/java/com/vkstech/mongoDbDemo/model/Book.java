package com.vkstech.mongoDbDemo.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "Book")
public class Book {

    @Id
    private Long id;
    private String bookName;
    private String authorName;

}
