package org.example.unittestexample.services.junit5;

import org.example.unittestexample.repos.NameGeneratorImpl;
import org.example.unittestexample.repos.UserRepository;
import org.example.unittestexample.services.UserService;
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
public class CanNotUseVerifyOrThenOnAutowired {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private NameGeneratorImpl nameGenerator;

    @Test
    public void addUser_whenNameGeneratorIsAutowiredAndNotAMock_willNotBeAbleToUseThenOrVerify() {
        userService.getUserByName("Vasko");

        then(nameGenerator)
                .should()
                .getName();

        assertEquals(1, 1);
    }
}
