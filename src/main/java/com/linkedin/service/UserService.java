package com.linkedin.service;

import com.linkedin.domain.dao.UserDao;
import com.linkedin.domain.model.User;

import java.util.ArrayList;
import java.util.UUID;

public class UserService implements BaseService{
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    public String register(User user) {
        return userRepository.register(user);
    }

    public ArrayList<UserDao> searchByUsername(String username, UUID userID) {
        return userRepository.getUsers(username, userID);
    }

}
