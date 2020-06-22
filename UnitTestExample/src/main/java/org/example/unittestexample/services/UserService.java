package org.example.unittestexample.services;

import org.example.unittestexample.dtos.User;

public interface UserService {

    User addUser(User user);

    User getUserByName(String name);

}
