package org.example.unittestexample.services.junit5;

import org.example.unittestexample.repos.NameGenerator;
import org.example.unittestexample.repos.UserRepository;
import org.example.unittestexample.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;

//Indicates that the test needs the Spring Context
@SpringBootTest
//Initializes all the Mocks automatically and we do not need @RunWith
public class SpyExample {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    // A mock that has all the methods mocked with the default behaviour
    // and is registered in the application context.
    private NameGenerator nameGenerator;

    @Test
    public void addUser_whenNameGeneratorIsAutowiredAndNotAMock_willNotBeAbleToUseThenOrVerify() {
        userService.getUserByName("Vasko");

        then(nameGenerator)
                .should()
                .getName();

        then(nameGenerator)
                .should()
                .echo("Vasko");

        assertEquals(1, 1);
    }
}
