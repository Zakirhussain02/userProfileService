package com.stackroute.controller;

import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    /*
    saving user in db
     */

    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        if (user != null) {
            userService.saveUser(user);
            return new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("User Already Exist", HttpStatus.CONFLICT);
        }
    }
}

