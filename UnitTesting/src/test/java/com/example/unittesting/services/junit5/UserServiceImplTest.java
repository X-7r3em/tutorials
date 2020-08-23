package com.example.unittesting.services.junit5;

import com.example.unittesting.repos.UserRepository;
import com.example.unittesting.dtos.User;
import com.example.unittesting.services.UserService;
import com.example.unittesting.services.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

// Indicates that the test needs the Spring Context
@SpringBootTest
// Initializes all the Mocks automatically and we do not need @RunWith
public class UserServiceImplTest {
    // We need to mock only the beans that need mocking. The rest are autowired.
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test // Difference in JUnit 5 vs JUnit 4 is the naming of the package.
    public void addUser_whenGivenUser_willAddUser() {
        User expected = new User("Tom", 15);

        // Mocks the repository
        given(userRepository.save(expected))
                .willReturn(expected);

        User actual = userService.addUser(expected);

        assertEquals(expected, actual);

        // Verify will check the number of times the method save() is
        // called and also check if the arguments of the method are equal
        // by calling their equals() method
        then(userRepository)
                .should()
                .save(expected);
    }

    // Example for mocking exceptions and checking after ACT
    @Test
    public void addUser_givenInvalidUser_willThrowException() {
        User expectedUser = new User("Tom", 15);

        willThrow(new UserNotFoundException("My exception message."))
                .given(userRepository)
                .save(expectedUser);

        // This will catch our Exception and we can then continue testing the assertions
        RuntimeException actualException =
                assertThrows(UserNotFoundException.class, () -> userService.addUser(expectedUser));

        assertEquals("My exception message.", actualException.getMessage());

        // Verify will check the number of times the method save() is
        // called and also check if the arguments of the method are equal
        // by calling their equals() method
        then(userRepository)
                .should()
                .save(expectedUser);

        assertEquals(1, 1);
    }
}
