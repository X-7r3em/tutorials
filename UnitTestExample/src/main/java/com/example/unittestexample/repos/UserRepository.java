package com.example.unittestexample.repos;

import com.example.unittestexample.dtos.User;

public interface UserRepository {

    User save(User user);

    User getByName(String name);
}
