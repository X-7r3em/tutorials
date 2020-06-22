package org.example.unittestexample.services;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.repos.NameGenerator;
import org.example.unittestexample.repos.UserRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

//Initializes all the Mocks
@RunWith(SpringRunner.class)
public class UserServiceImplPureUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private NameGenerator nameGenerator;

    @Test //Difference in JUnit 5 vs JUnit 4 is the naming of the package.
    public void addUser_whenGivenUser_shouldAddUser() {
        UserService userService = new UserServiceImpl(userRepository, nameGenerator);

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

}