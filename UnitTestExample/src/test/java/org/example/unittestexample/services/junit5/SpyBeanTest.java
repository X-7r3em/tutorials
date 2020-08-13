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
    // A mock that has the same functionality as the original. Can be used to verify
    // and capture arguments
    private UserRepository userRepository;

    @Test
    public void spyBeanExample() {
        User user = new User("Vasko", 15);
        User expected = new User("Vasko", 15);

        userService.addUser(user);

        // Here we can verify the Spy, but we do not need to give it any behaviour
        then(userRepository)
                .should()
                .save(expected);
    }
}
