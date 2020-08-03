package com.vkstech.mongoDbDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "SchoolBook")
public class SchoolBooks {

    @Id
    private Long id;
    private String bookName;
    private String authorName;
    private Integer noOfPages;
    private String category;

}
