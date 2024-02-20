package com.example.eshop.service;

import com.example.eshop.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    User findByEmail(String email);
}
