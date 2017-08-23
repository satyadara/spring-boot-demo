package com.satyadara.service;

import com.satyadara.model.User;
import com.satyadara.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository)    {
        this.userRepository = userRepository;
    }

    public List<User> getUserList() {

        List<User> userList = userRepository.findAll();

        return userList;
    }

    public User getAUser(Long id)    {

        User user = userRepository.findById(id);

        return user;
    }

    public User postAUser(User user)    {
        User user1 = userRepository.save(user);
        return user1;
    }
}
