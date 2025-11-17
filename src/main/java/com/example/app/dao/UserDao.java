package com.example.app.dao;

import com.example.app.model.User;

import java.util.List;

public interface UserDao {
    User save(String name);

    List<User> findAll();
}
