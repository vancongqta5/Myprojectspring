package com.example.testjpa.services;

import com.example.testjpa.models.User;
import com.example.testjpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    //find Users By Name
//    @Autowired
//    UserRepository userRepository;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsersByName(String name) {
        return userRepository.findByName(name);
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