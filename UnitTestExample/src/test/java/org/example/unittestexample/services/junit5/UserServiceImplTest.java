package org.example.unittestexample.services.junit5;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.repos.UserRepository;
import org.example.unittestexample.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

//Indicates that the test needs the Spring Context
@SpringBootTest
//Initializes all the Mocks automatically and we do not need @RunWith
public class UserServiceImplTest {
    //We need to mock only the beans that need mocking. The rest are autowired.
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test //Difference in JUnit 5 vs JUnit 4 is the naming of the package.
    public void addUser_whenGivenUser_willAddUser() {
        User expected = new User("Tom", 15);

        //Mocks the repository
        given(userRepository.save(expected))
                .willReturn(expected);

        User actual = userService.addUser(expected);

        assertEquals(expected, actual);

        //Checks if the method was called
        then(userRepository)
                .should()
                .save(expected);
    }

    //Example for mocking exceptions and checking after ACT
    @Test
    public void addUser_givenInvalidUser_willThrowException() {
        User expectedUser = new User("Tom", 15);

        willThrow(new RuntimeException("My exception message."))
                .given(userRepository)
                .save(expectedUser);

        // This will catch our Exception and we can then continue testing the assertions

        RuntimeException actualException =
                assertThrows(RuntimeException.class, () -> userService.addUser(expectedUser));

        assertEquals("My exception message.", actualException.getMessage());

        // These will all be checked
        then(userRepository)
                .should()
                .save(expectedUser);

        assertEquals(1, 1);
    }
}
