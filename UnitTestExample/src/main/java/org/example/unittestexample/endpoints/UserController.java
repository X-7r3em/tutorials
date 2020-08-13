package org.example.unittestexample.endpoints;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/add",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{name}")
    public User createUser(@PathVariable String name) {
        return userService.getUserByName(name);
    }
}
