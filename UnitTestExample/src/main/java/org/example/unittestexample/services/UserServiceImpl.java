package org.example.unittestexample.services;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.repos.NameGenerator;
import org.example.unittestexample.repos.UserRepository;
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
        nameGenerator.getName();
        return userRepository.save(user);
    }

    @Override
    public User getUserByName(String name) {
        String someName = nameGenerator.getName();
        nameGenerator.echo(name);
        return userRepository.getByName(name);
    }
}

