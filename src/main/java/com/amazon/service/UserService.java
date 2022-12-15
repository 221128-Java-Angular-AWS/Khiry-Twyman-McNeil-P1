package com.amazon.service;

import com.amazon.persistence.UserDao;
import com.amazon.pojos.User;

import java.util.Set;

public class UserService {
    private UserDao dao;

    public UserService(UserDao dao){
        this.dao = dao;
    }

    public void registerNewUser(User user) {
        dao.create(user);
    }

    public Set<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public void updateUser(User user) {
        dao.updateUser(user);
    }

    public void deleteUser(Integer userId) {
        dao.deleteUser(userId);
    }
}
