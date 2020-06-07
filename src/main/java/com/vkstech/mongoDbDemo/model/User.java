package com.vkstech.mongoDbDemo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
 
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
 
    @Id
    private Long id;

    private String name;

    @Indexed(unique = true)
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}