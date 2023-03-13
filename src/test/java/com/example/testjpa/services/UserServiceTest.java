//package com.example.testjpa.services;
//
//import com.example.testjpa.models.User;
//import com.example.testjpa.repositories.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@SpringBootTest
//public class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private UserService userService;
//    @Test
//    void getAllUsersTest() {
//        // tạo một danh sách các user giả lập
//        List<User> users = new ArrayList<>();
//        users.add(new User(1L, "Alice"));
//        users.add(new User(2L, "Bob"));
//
//        // giả lập hành vi của userRepository.findAll()
//        when(userRepository.findAll()).thenReturn(users);
//
//        // gọi phương thức cần test và kiểm tra kết quả
//        List<User> result = userService.getAllUsers();
//        assertEquals(users, result);
//
//        // kiểm tra xem userRepository.findAll() đã được gọi đúng số lần chưa
//        verify(userRepository, times(1)).findAll();
//    }
//}
