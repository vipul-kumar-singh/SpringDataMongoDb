package com.vkstech.mongoDbDemo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookDto {

    private Long id;
    private String bookName;
    private String authorName;
}
