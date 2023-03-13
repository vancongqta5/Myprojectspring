package com.example.testjpa.services;

import com.example.testjpa.models.User;
import com.example.testjpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    //new

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public User save(User user) {
//        return userRepository.save(user);
//    }
//}