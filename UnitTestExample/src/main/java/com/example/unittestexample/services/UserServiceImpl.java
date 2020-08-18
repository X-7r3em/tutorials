package com.example.unittestexample.services;

import com.example.unittestexample.dtos.User;
import com.example.unittestexample.repos.NameGenerator;
import com.example.unittestexample.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final NameGenerator nameGenerator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, NameGenerator nameGenerator) {
        this.userRepository = userRepository;
        this.nameGenerator = nameGenerator;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.getByName(name);
    }
}

