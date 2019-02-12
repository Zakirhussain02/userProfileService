package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public UserRepository userRepository;

    /*
    Constructor
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    /*
    save user in db
     */
    @Override
    public User saveUser(User user) {

        if(userRepository.existsById(user.getEmail())){
            System.out.println("user already exists");
        }

        User savedUser = userRepository.save(user);

        if(savedUser == null)
            System.out.println("Give a valid Input");

        return savedUser;
    }
}
