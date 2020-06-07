package com.vkstech.mongoDbDemo.controller;

import com.vkstech.mongoDbDemo.dto.UserDto;
import com.vkstech.mongoDbDemo.dto.ResponseDto;
import com.vkstech.mongoDbDemo.service.UserService;
import com.vkstech.mongoDbDemo.service.QueryBuilderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private QueryBuilderService queryBuilderService;

    @PostMapping("add")
    public ResponseEntity<ResponseDto> saveUser(@RequestBody UserDto userDto) {
        LOGGER.info("UserController :: saveUser");
        return userService.saveUser(userDto);
    }

    @GetMapping("all")
    public ResponseEntity<ResponseDto> getAllUsers() {
        LOGGER.info("UserController :: getAllUsers");
        return userService.getAllUsers();
    }
}
