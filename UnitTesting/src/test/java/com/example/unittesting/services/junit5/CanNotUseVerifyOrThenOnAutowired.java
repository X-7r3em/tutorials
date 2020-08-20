package com.example.unittesting.services.junit5;

import com.example.unittesting.repos.NameGeneratorImpl;
import com.example.unittesting.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.misusing.NotAMockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willThrow;

// Indicates that the test needs the Spring Context
@SpringBootTest
// Initializes all the Mocks automatically and we do not need @RunWith
public class CanNotUseVerifyOrThenOnAutowired {
    @Autowired
    private UserService userService;

    @Autowired
    private NameGeneratorImpl nameGenerator;

    @Test
    public void addUser_whenNameGeneratorIsAutowiredAndNotAMock_willThrowNotAMockException() {
        userService.getUserByName("Vasko");

        NotAMockException exception = assertThrows(NotAMockException.class, () ->
                then(nameGenerator)
                        .should()
                        .getName());

        assertEquals("\n" +
                "Argument passed to verify() is of type NameGeneratorImpl and is not a mock!\n" +
                "Make sure you place the parenthesis correctly!\n" +
                "See the examples of correct verifications:\n" +
                "    verify(mock).someMethod();\n" +
                "    verify(mock, times(10)).someMethod();\n" +
                "    verify(mock, atLeastOnce()).someMethod();", exception.getMessage());
    }
}
