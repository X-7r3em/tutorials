package org.example.unittestexample.endpoints.junit4;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javaws.exceptions.InvalidArgumentException;
import org.example.unittestexample.dtos.User;
import org.example.unittestexample.endpoints.UserController;
import org.example.unittestexample.services.UserService;

import org.example.unittestexample.services.exception.UserNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.mockito.BDDMockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Gives me the MVC testing capabilities
@WebMvcTest(UserController.class)
// This initializes all mocks
@RunWith(SpringRunner.class)
public class UserControllerTest {

    // Gives me the MVC object
    @Autowired
    private MockMvc mockMvc;

    // I need to mock all the dependencies of the said controller
    @MockBean
    private UserService userService;

    @Test
    public void addUser_givenUser_willCreateUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("Vasko", 15);
        String requestContent = mapper.writeValueAsString(user);
        String responseContent = mapper.writeValueAsString(user);

        given(userService.addUser(user))
                .willReturn(user);

        mockMvc.perform(post("/add")
                .header("mock-header", "mock-value")
                .contentType(APPLICATION_JSON)
                .content(requestContent))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(content().string(responseContent));

        // This verifies that the userService was called with the proper method the needed
        // amount of times and the arguments were equal.
        then(userService)
                .should()
                .addUser(user);
    }

    @Test
    public void addUser_givenInvalidUser_willThrowException() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User("Vasko", 15);
        String requestContent = mapper.writeValueAsString(user);

        willThrow(UserNotFoundException.class)
                .given(userService)
                .addUser(user);

        mockMvc.perform(post("/add")
                .header("mock-header", "mock-value")
                .contentType(APPLICATION_JSON)
                .content(requestContent))
                .andExpect(status().isOk());

        // This verifies that the userService was called with the proper method the needed
        // amount of times and the arguments were equal.
        then(userService)
                .should()
                .addUser(user);
    }
}