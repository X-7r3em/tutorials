package org.example.unittestexample.services.junit5;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.repos.UserRepository;
import org.example.unittestexample.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

//Indicates that the test needs the Spring Context
@SpringBootTest
//Initializes all the Mocks automatically and we do not need @RunWith
public class UserServiceImplJUnit5Test {
    //We need to mock only the beans that need mocking. The rest are autowired.
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void addUser_throwExample() {
        User expected = new User("Tom", 15);

        willThrow(RuntimeException.class)
                .given(userRepository)
                .save(expected);

        // We check that the exception is thrown this way, so that we can continue with the asserts bellow.
        // This is an improvement compared to JUnit4.
        Assertions.assertThrows(RuntimeException.class, () -> userService.addUser(expected));

        then(userRepository)
                .should()
                .save(expected);

        assertEquals(1, 1);
    }
}
