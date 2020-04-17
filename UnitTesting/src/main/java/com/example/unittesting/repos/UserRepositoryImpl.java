package com.example.unittesting.repos;

import com.example.unittesting.dtos.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final List<User> users;

    static {
        users = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User getByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}

