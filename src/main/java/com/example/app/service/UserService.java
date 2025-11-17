package com.example.app.service;

import com.example.app.dao.UserDao;
import com.example.app.dao.impl.UserDaoJdbc;
import com.example.app.model.User;

import java.util.List;

public class UserService {
    private final UserDao userDao = new UserDaoJdbc();

    public User save(String name) {
        return userDao.save(name);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }
}
