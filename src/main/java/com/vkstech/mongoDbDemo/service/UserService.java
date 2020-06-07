package com.vkstech.mongoDbDemo.service;

import com.vkstech.mongoDbDemo.constants.ResponseMsg;
import com.vkstech.mongoDbDemo.dto.UserDto;
import com.vkstech.mongoDbDemo.dto.ResponseDto;
import com.vkstech.mongoDbDemo.model.User;
import com.vkstech.mongoDbDemo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

    Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<ResponseDto> saveUser(UserDto userdto) {
        LOGGER.info("UserService :: saveUser");
        User user = userRepository.findByEmail(userdto.getEmail());
        if (Objects.nonNull(user))
            return new ResponseEntity<>(new ResponseDto(ResponseMsg.USER_ALREADY_EXISTS), HttpStatus.BAD_REQUEST);

        user = new User(userdto.getName(), userdto.getEmail());
        user.setId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
        BeanUtils.copyProperties(userdto, user);
        userRepository.save(user);
        return new ResponseEntity<>(new ResponseDto(user, ResponseMsg.USER_SAVED), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDto> getAllUsers() {
        LOGGER.info("UserService :: getAllUsers");
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(new ResponseDto(users, ResponseMsg.USER_FETCHED), HttpStatus.OK);
    }
}
