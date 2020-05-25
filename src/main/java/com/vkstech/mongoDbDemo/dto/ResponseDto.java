package com.vkstech.mongoDbDemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResponseDto implements Serializable {

    private Object data;
    private String message;
    private Instant timestamp;

    public ResponseDto() {
        this.timestamp = Instant.now();
    }

    public ResponseDto(Object data, String message) {
        this();
        this.data = data;
        this.message = message;
    }

    public ResponseDto(String message) {
        this();
        this.message = message;
    }

}
