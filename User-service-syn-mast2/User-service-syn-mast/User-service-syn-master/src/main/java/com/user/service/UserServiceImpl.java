package com.user.service;

import com.user.model.User;
import com.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        //Address
        if (user.getAddress() != null) {
            user.getAddress().forEach(address -> address.setUser(user));
        }
        //contact
        if (user.getContact() != null) {
            user.getContact().forEach(contact -> contact.setUser(user));
        }

        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        user.setUserId(id);

        if (user.getAddress() != null) {
            user.getAddress().forEach(address -> address.setUser(user));
        }

        if (user.getContact() != null) {
            user.getContact().forEach(contact -> contact.setUser(user));
        }

        return userRepository.save(user);
    }


    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

