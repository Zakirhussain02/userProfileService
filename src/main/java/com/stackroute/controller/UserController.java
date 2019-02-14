package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api/v1")
@Api(value="QA", description="Platform for QA")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    PasswordEncoder encoder;

    /*
    saving user in db
     */

    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        if (user != null) {
            User signUp = new User(user.getEmail(),encoder.encode(user.getPassword()),user.getFirstName(),user.getLastName(),user.getInterests());
             userService.saveUser(signUp);
            return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("users")
    public ResponseEntity<?> getUser(){
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<List<User>>(userService.getUser(),HttpStatus.OK);
        return responseEntity;
    }
}

