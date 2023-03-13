package com.example.testjpa.controllers;

import com.example.testjpa.models.User;
import com.example.testjpa.repositories.UserRepository;
import com.example.testjpa.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        UserController userController = new UserController(userRepository, userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> userList = Arrays.asList(
                new User(1L, "user1", "user1@example.com"),
                new User(2L, "user2", "user2@example.com")
        );
        when(userRepository.findAll()).thenReturn(userList);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("user1"))
                .andExpect(jsonPath("$[1].name").value("user2"));
    }

    @Test
    void testGetUsersByName() throws Exception {
        List<User> userList = Arrays.asList(
                new User(1L, "user1", "user1@example.com"),
                new User(2L, "user1", "user2@example.com")
        );
        when(userService.findUsersByName(anyString())).thenReturn(userList);
        mockMvc.perform(get("/name/user1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[1].email").value("user2@example.com"));
    }

    @Test
    void testCreateUser() throws Exception {
        User newUser = new User(1L, "newuser", "newuser@example.com");
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"newuser\",\"email\":\"newuser@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("newuser"))
                .andExpect(jsonPath("$.email").value("newuser@example.com"));
    }
    @Test
    void deleteUser() throws Exception {
        User user = new User(1L,"test", "test@test.com");
        userRepository.save(user);

        mockMvc.perform(delete("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}