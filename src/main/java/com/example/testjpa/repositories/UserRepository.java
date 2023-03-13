package com.example.testjpa.repositories;

import com.example.testjpa.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //new
    List<User> findByName(String name);
}