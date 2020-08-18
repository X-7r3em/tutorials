package com.example.unittestexample.services;

import com.example.unittestexample.dtos.User;

public interface UserService {

    User addUser(User user);

    User getUserByName(String name);

}
