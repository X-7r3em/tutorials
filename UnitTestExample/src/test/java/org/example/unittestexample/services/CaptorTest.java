package org.example.unittestexample.services;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.repos.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
public class CaptorTest {

    @Autowired
    private UserService userService;

    @SpyBean
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<User> argumentCaptor;

    @Test
    public void captorExample() {
        User user = new User("Vasko", 15);
        userService.addUser(user);

        User expected = new User("Vasko", 15);
        Mockito.verify(userRepository).save(argumentCaptor.capture());

        User actual = argumentCaptor.getValue();

        Assertions.assertEquals(expected, actual);
    }
}
