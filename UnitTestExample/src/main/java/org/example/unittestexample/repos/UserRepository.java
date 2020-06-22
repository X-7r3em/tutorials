package org.example.unittestexample.repos;

import org.example.unittestexample.dtos.User;

public interface UserRepository {

    User save(User user);

    User getByName(String name);
}
