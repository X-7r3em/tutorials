package com.example.unittesting.repos;

import com.example.unittesting.dtos.User;

public interface UserRepository {

    User save(User user);

    User getByName(String name);
}
