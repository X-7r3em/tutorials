package org.example.unittestexample.services.junit5;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.repos.UserRepository;
import org.example.unittestexample.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.mockito.BDDMockito.then;

@SpringBootTest
public class SpyBeanTest {

    @Autowired
    private UserService userService;

    @SpyBean
    private UserRepository userRepository;

    @Test
    public void spyBeanExample() {
        User user = new User("Vasko", 15);
        userService.addUser(user);

        User expected = new User("Vasko", 15);

        then(userRepository).should().save(expected);
    }
}
