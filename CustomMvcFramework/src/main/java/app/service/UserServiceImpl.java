package app.service;

import app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final List<User> userDb = new ArrayList<>();

    @Override
    public void createUser(String username, String email, String password) {
        userDb.add(new User(String.valueOf(userDb.size()), username, email, password));
    }

    @Override
    public void changePassword(String username, String newPassword) {
        userDb.stream().filter(u -> u.getUsername().equals(username))
                .findFirst().get().setPassword(newPassword);
    }

    @Override
    public String getUser(String username, String password) {
        Optional<User> user = userDb.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst();

        return user.isPresent() ? user.get().getUserId() : null;
    }

    @Override
    public int countUsers() {
        return userDb.size();
    }
}
