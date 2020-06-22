package org.example.unittestexample.services;

import org.example.unittestexample.dtos.User;
import org.example.unittestexample.repos.UserRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

//Indicates that the test needs the Spring Context
@SpringBootTest
//Initializes all the Mocks
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    //We need to mock only the beans that need mocking. The rest are autowired.
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private static int[] before = new int[1];
    private static int[] beforeClass = new int[1];

    //Is done before every test. Equal to @BeforeEach
    @Before
    public void beforeEach() {
        before[0] = 10;
    }

    //Is done once before all the tests. Equal to @BeforeAll
    @BeforeClass
    public static void beforeClass() {
        beforeClass[0] = 10;
    }

    @Test //Difference in JUnit 5 vs JUnit 4 is the naming of the package.
    public void addUser_whenGivenUser_shouldAddUser() {
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

    //Example for mocking exceptions
    @Test(expected = RuntimeException.class)
    public void addUser_throwExample() {
        User expected = new User("Tom", 15);

        willThrow(RuntimeException.class)
                .given(userRepository)
                .save(expected);

        userService.addUser(expected);

        then(userRepository)
                .should()
                .save(expected);
    }

    @Test
    public void beforeFirstTest() {
        before[0] += 100;
        assertEquals(110, before[0]);
    }

    @Test
    public void beforeSecondTest() {
        before[0] += 25;
        assertEquals(35, before[0]);
    }

    @Test
    public void beforeClassFirstTest() {
        beforeClass[0] += 100;
        assertEquals(110, beforeClass[0]);
    }

    @Test
    public void beforeClassClassSecondTest() {
        beforeClass[0] += 25;
        assertEquals(135, beforeClass[0]);
    }

}