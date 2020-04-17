package com.example.unittesting.endpoints;

import com.example.unittesting.dtos.User;
import com.example.unittesting.services.UserService;
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

    @GetMapping("/exception")
    public String createException() {
        throw new IllegalArgumentException("This is the message of the Exception");
    }

    @GetMapping("/exception/unhandled")
    public String createUnhandledException() {
        throw new NullPointerException("This is the message of the Mock Exception");
    }
}
