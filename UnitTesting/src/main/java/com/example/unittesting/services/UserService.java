package com.example.unittesting.services;

import com.example.unittesting.dtos.User;

public interface UserService {

    User addUser(User user);

    User getUserByName(String name);

}
