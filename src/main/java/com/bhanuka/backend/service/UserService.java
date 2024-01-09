package com.bhanuka.backend.service;

import com.bhanuka.backend.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();

}
