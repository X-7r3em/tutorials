package org.example.unittestexample.services.junit4;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.repos.UserRepository;
import org.example.unittestexample.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

// Indicates that the test needs the Spring Context
@SpringBootTest
// Initializes all the Mocks
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    // We need to mock only the beans that need mocking. The rest are autowired.
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test // Difference in JUnit 5 vs JUnit 4 is the naming of the package.
    public void addUser_whenGivenUser_shouldAddUser() {
        User expected = new User("Tom", 15);

        // Mocks the repository
        given(userRepository.save(expected))
                .willReturn(expected);

        User actual = userService.addUser(expected);

        assertEquals(expected, actual);

        // Checks if the method was called
        then(userRepository)
                .should()
                .save(expected);
    }

    // Example for mocking exceptions
    @Test(expected = RuntimeException.class)
    public void addUser_throwExample() {
        User expected = new User("Tom", 15);

        willThrow(RuntimeException.class)
                .given(userRepository)
                .save(expected);

        // The test creates a try-catch for this method. Once it throws, the try-catch will catch it.
        // It will mark the test as passed and it will not check any further asserts.
        userService.addUser(expected);

        // This will not be checked as the test will never come here.
        then(userRepository)
                .should()
                .save(expected);

        assertEquals(1, 0);
    }

    // Example for mocking exceptions and checking after ACT
    @Test
    public void addUser_throwExampleWithAsserts() {
        User expectedUser = new User("Tom", 15);

        willThrow(new RuntimeException("My exception message."))
                .given(userRepository)
                .save(expectedUser);

        // This will catch our Exception and we can then continue testing the assertions
        try {
            userService.addUser(expectedUser);
            fail("Expected to throw Runtime Exception, but it did not fail.");
        } catch (RuntimeException ex) {
            assertEquals("My exception message.", ex.getMessage());
        }

        // These will all be checked
        then(userRepository)
                .should()
                .save(expectedUser);

        assertEquals(1, 1);
    }
}