package com.satyadara.springdemo.service;

import com.satyadara.springdemo.model.User;
import com.satyadara.springdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)    {
        this.userRepository = userRepository;
    }

    public List<User> getUserList() {

        List<User> userList = userRepository.findAll();

        return userList;
    }

    public User getAUser(Long id)    {

        User user = userRepository.findOne(id);

        return user;
    }

    public User postAUser(User user)    {
        User user1 = userRepository.save(user);
        return user1;
    }
}
