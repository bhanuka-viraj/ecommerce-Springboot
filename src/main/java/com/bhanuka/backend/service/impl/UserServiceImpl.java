package com.bhanuka.backend.service.impl;

import com.bhanuka.backend.entity.User;
import com.bhanuka.backend.reository.UserRepository;
import com.bhanuka.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(User user) {

//        System.out.println("***********************************************"+user+"******************************************");
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
