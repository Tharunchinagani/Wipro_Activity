package com.user.service;

import com.user.model.User;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User saveUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
